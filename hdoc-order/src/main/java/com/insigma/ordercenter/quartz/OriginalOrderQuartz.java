package com.insigma.ordercenter.quartz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.constant.OrderStrategyConstant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.*;
import com.insigma.ordercenter.utils.JsonUtil;
import com.insigma.ordercenter.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author youwk
 * @ClassName 原始订单处理定时器
 * @description TODO
 * @date 2020/7/29 13:38
 * @Version 1.0
 */
@Configuration
@Slf4j
@RestController
public class OriginalOrderQuartz {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOriginalOrderService originalOrderService;

    @Autowired
    private IOriginalOrderDetailService originalOrderDetailService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IAccountBlacklistStrategyService absService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ISendReceiveInfoService sendReceiveInfoService;

    @Autowired
    private IOrderProcessService processService;

    @Autowired
    private IOrderPayService orderPayService;

    @Autowired
    private RedisUtil redisUtil;

//    @Scheduled(fixedDelay = 2*60*1000)
    @GetMapping("/ood")
    @Transactional(rollbackFor = RuntimeException.class)
    public void originOrderDeal() {
        Long batchNo = IdWorker.getId();
        log.info("================= 原始订单处理任务 START ==============");
        log.info("================= 预约商品规则 ===========");
        //获取最近2小时原始订单表
        List<OriginalOrder> list = originalOrderService.list(Wrappers.<OriginalOrder>lambdaQuery()
                .eq(OriginalOrder::getOrderStatus, 0).ge(OriginalOrder::getOrderTime,LocalDateTime.now().minusDays(5)));
        //生成订单实例
        for (OriginalOrder originalOrder : list) {
            Order order = new Order();
            order.setConsumerName(originalOrder.getConsumerName());
            order.setCreateTime(LocalDateTime.now());
            order.setBatchNo(batchNo);
            order.setIsPeriod(0);
            order.setMobilePhone(originalOrder.getMobilePhone());
            order.setOrderNo(originalOrder.getOrderNo());
            order.setOrderStatus(2);
            order.setShopId(originalOrder.getShopId());
            order.setTotalPrice(originalOrder.getTotalPrice());
            order.setOriginOrderNo(originalOrder.getOriginalOrderNo());
            //保存下单人 收货人信息
            SendReceiveInfo sendReceiveInfo = new SendReceiveInfo();
            sendReceiveInfo.setAddress(originalOrder.getAddress());
            sendReceiveInfo.setProvince(originalOrder.getProvince());
            sendReceiveInfo.setLocationCity(originalOrder.getCity());
            sendReceiveInfo.setDistrict(originalOrder.getDistrict());
            sendReceiveInfo.setBuyerNickname(originalOrder.getBuyerNickname());
            sendReceiveInfo.setMobilePhone(originalOrder.getMobilePhone());
            sendReceiveInfo.setOrderTime(originalOrder.getOrderTime());
            sendReceiveInfo.setPayType(1);
            sendReceiveInfo.setReceiveName(originalOrder.getConsumerName());
            sendReceiveInfo.setSendRemark(originalOrder.getRemark());
            //保存订单
            orderService.save(order);
            sendReceiveInfo.setOrderId(order.getOrderId());
            sendReceiveInfoService.save(sendReceiveInfo);
            //更新原始订单中的订单字段 关联到订单
            originalOrder.setOrderId(order.getOrderId());
            originalOrderService.updateById(originalOrder);
            //获取原始订单详情
            List<OriginalOrderDetail> orderDetailList = originalOrderDetailService.list(Wrappers.<OriginalOrderDetail>lambdaQuery()
                    .eq(OriginalOrderDetail::getOriginalOrderId, originalOrder.getOriginalOrderId()));
            List<OrderDetail> detailList = new ArrayList<>();
            for (OriginalOrderDetail detail : orderDetailList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setAmount(detail.getQuantity());
                BeanUtils.copyProperties(detail,orderDetail);
                detailList.add(orderDetail);
            }
            //保存原始订单详情
            orderDetailService.saveBatch(detailList);
        }
        Object redisStrategy = redisUtil.get("strategyList");
        List<Strategy> strategyList = JsonUtil.jsonToList(JsonUtil.beanToJson(redisStrategy),Strategy.class);
        if (null == strategyList ||strategyList.size() == 0) {
            log.error("未获取到策略，无法进行处理");
            return ;
        }
        Strategy strategy = strategyList.get(OrderStrategyConstant.COMBINED_ORDER-1);
        if (strategy.getIsStop() == 0) {
            log.info("================= 合单策略 START ==============");
            Map<String,Set<Long>> combinedMap = new HashMap<>(16);
            //双重for循环比较，将手机号和地址完全一致的分为一组，为合单做准备
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (i != j) {
                        OriginalOrder o1 = list.get(i);
                        OriginalOrder o2 = list.get(j);
                        String mobile1 = o1.getMobilePhone();
                        String mobile2 = o2.getMobilePhone();
                        if (mobile1.equals(mobile2) && o1.getAddress().equals(o2.getAddress())) {
                            log.info("id1 = {} ,id2 = {}",o1.getOriginalOrderId(),o2.getOriginalOrderId());
                            if(combinedMap.containsKey(mobile1)) {
                                Set<Long> idSet = combinedMap.get(mobile1);
                                idSet.add(o2.getOriginalOrderId());
                                combinedMap.put(mobile1,idSet);
                            } else {
                                HashSet<Long> idSet = new HashSet<>();
                                idSet.add(o1.getOriginalOrderId());
                                idSet.add(o2.getOriginalOrderId());
                                combinedMap.put(mobile1,idSet);
                            }
                        }
                    }
                }
            }
            log.info(JsonUtil.beanToJson(combinedMap));
            for (Map.Entry<String, Set<Long>> entry : combinedMap.entrySet()) {
                Set<Long> idSet = entry.getValue();
                Order order = new Order();
                BigDecimal totalPrice = new BigDecimal("0.00");
                Long orderId = IdWorker.getId();
                order.setOrderId(orderId);
                SendReceiveInfo sendReceiveInfo = new SendReceiveInfo();
                for (Long originOrderId : idSet) {
                    //获取原始订单
                    OriginalOrder originalOrder = originalOrderService.getById(originOrderId);
                    //根据原始订单编号订单，订单详情，收货人信息
                    orderDetailService.remove(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, originalOrder.getOrderId()));
                    orderService.remove(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, originalOrder.getOrderId()));
                    sendReceiveInfoService.remove(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, originalOrder.getOrderId()));
                    order.setConsumerName(originalOrder.getConsumerName());
                    order.setCreateTime(LocalDateTime.now());
                    order.setIsPeriod(0);
                    order.setBatchNo(batchNo);
                    order.setMobilePhone(originalOrder.getMobilePhone());
                    order.setOrderNo(originalOrder.getOrderNo());
                    order.setOrderStatus(0);
                    totalPrice = totalPrice.add(originalOrder.getTotalPrice());
                    order.setShopId(originalOrder.getShopId());
                    List<OriginalOrderDetail> orderDetailList = originalOrderDetailService.list(Wrappers.<OriginalOrderDetail>lambdaQuery()
                            .eq(OriginalOrderDetail::getOriginalOrderId, originalOrder.getOriginalOrderId()));
                    List<OrderDetail> detailList = new ArrayList<>();
                    for (OriginalOrderDetail detail : orderDetailList) {
                        OrderDetail orderDetail = new OrderDetail();
                        BeanUtils.copyProperties(detail, orderDetail);
                        orderDetail.setOrderId(orderId);
                        orderDetail.setAmount(detail.getQuantity());
                        detailList.add(orderDetail);
                    }
                    orderDetailService.saveBatch(detailList);
                    originalOrder.setOrderId(orderId);
                    originalOrderService.updateById(originalOrder);
                    sendReceiveInfo.setAddress(originalOrder.getAddress());
                    sendReceiveInfo.setProvince(originalOrder.getProvince());
                    sendReceiveInfo.setOrderId(orderId);
                    sendReceiveInfo.setLocationCity(originalOrder.getCity());
                    sendReceiveInfo.setDistrict(originalOrder.getDistrict());
                    sendReceiveInfo.setBuyerNickname(originalOrder.getBuyerNickname());
                    sendReceiveInfo.setMobilePhone(originalOrder.getMobilePhone());
                    sendReceiveInfo.setOrderTime(originalOrder.getOrderTime());
                    sendReceiveInfo.setPayType(1);
                    sendReceiveInfo.setReceiveName(originalOrder.getConsumerName());
                    sendReceiveInfo.setSendRemark(originalOrder.getRemark());
                }
                sendReceiveInfoService.save(sendReceiveInfo);
                order.setTotalPrice(totalPrice);
                orderService.save(order);
            }
            log.info("================= 合单策略 END ==============");
        }
        Strategy changeProductStrategy = strategyList.get(OrderStrategyConstant.CHANGE_PRODUCT - 1);
        if (changeProductStrategy.getIsStop() == 0) {
            log.info("================= 换货策略 START ============");
            processService.exchangeProductStrategy(batchNo);
            log.info("================= 换货策略 END   ============");
        }
        Strategy giftStrategy = strategyList.get(OrderStrategyConstant.GIFT - 1);
        if (giftStrategy.getIsStop() == 0) {
            log.info("================= 赠品规则 START=============");
            processService.giftProductStrategy(batchNo);
            log.info("================= 赠品规则 END  =============");
        }
        //店铺账号订单拦截
        Strategy accountStrategy = strategyList.get(OrderStrategyConstant.BLACKLIST_ACCOUNT - 1);
        if (accountStrategy.getIsStop() == 0) {
            log.info("================= 店铺账号订单拦截  START =============");
            processService.shopBlackStrategy(batchNo);
            log.info("================= 店铺账号订单拦截  END =============");
        }
        //手机号拦截
        Strategy mobileStrategy = strategyList.get(OrderStrategyConstant.BLACKLIST_MOBILE - 1);
        if (mobileStrategy.getIsStop() == 1) {
            log.info("================= 手机号订单拦截  START =============");
            processService.mobileStrategy(batchNo);
            log.info("================= 手机号订单拦截  END =============");
        }
        //地区拦截规则
        Strategy districtStrategy = strategyList.get(OrderStrategyConstant.BLACKLIST_DISTRICT - 1);
        if (districtStrategy.getIsStop() == 0) {
            log.info("================= 地区订单拦截  START =============");
            processService.districtStrategy(batchNo);
            log.info("================= 地区订单拦截  END =============");
        }

        //地址无法解析
        Strategy parseStrategy = strategyList.get(OrderStrategyConstant.ADDRESS_PARSE_ERROR- 1);
        if (parseStrategy.getIsStop() == 0) {
            log.info("================= 地址无法解析拦截  START =============");
            List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));

            for (Order order : orderList) {
                SendReceiveInfo sendReceive = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
                String province = sendReceive.getProvince();
                String city = sendReceive.getLocationCity();
                String district  = sendReceive.getDistrict();
                String address = sendReceive.getAddress();
                boolean lackParam = StringUtils.isBlank(province)||StringUtils.isBlank(city)||StringUtils.isBlank(district)||StringUtils.isBlank(address);
                SysRegion provinceRegion = regionService.name(province);
                SysRegion cityRegion = regionService.name(city);
                SysRegion districtRegion = regionService.name(district);
                boolean paramIsIllegal = null == provinceRegion || null == cityRegion || null == districtRegion;
                if(lackParam || paramIsIllegal) {
                    order.setOrderStatus(1);
                    order.setErrorReason(CodeMsg.STRATEGY_ADDRESS_PARSE_ERROR.getMessage());
                    orderService.updateById(order);
                }
            }
            log.info("================= 地址无法解析拦截  END =============");
        }
    }
}
