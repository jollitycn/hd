package com.insigma.ordercenter.quartz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insigma.ordercenter.constant.OrderStrategyConstant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.*;
import com.insigma.ordercenter.utils.JsonUtil;
import com.insigma.ordercenter.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author youwk
 * @ClassName 原始订单处理定时器
 * @description TODO
 * @date 2020/7/29 13:38
 * @Version 1.0
 */
@Configuration
@Slf4j
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
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional(rollbackFor = RuntimeException.class)
    public void originOrderDeal() {
        log.info("================= 原始订单处理任务 START ==============");
        log.info("================= 预约商品规则 ===========");
        //获取最近2小时原始订单表
        List<OriginalOrder> list = originalOrderService.list(Wrappers.<OriginalOrder>lambdaQuery()
                .eq(OriginalOrder::getOrderStatus, 0).ge(OriginalOrder::getCreateTime,LocalDateTime.now().minusHours(40L)));
        //生成订单实例
        List<Order> orderList = new ArrayList<>();
        for (OriginalOrder originalOrder : list) {
            Order order = new Order();
            order.setConsumerName(originalOrder.getConsumerName());
            order.setCreateTime(LocalDateTime.now());
            order.setIsPeriod(0);
            order.setMobilePhone(originalOrder.getMobilePhone());
            order.setOrderNo(originalOrder.getOrderNo());
            order.setOrderStatus(0);
            order.setShopId(originalOrder.getShopId());
            order.setTotalPrice(originalOrder.getTotalPrice());
            orderList.add(order);
            orderService.save(order);
            originalOrder.setOrderId(order.getOrderId());
            originalOrderService.updateById(originalOrder);
            List<OriginalOrderDetail> orderDetailList = originalOrderDetailService.list(Wrappers.<OriginalOrderDetail>lambdaQuery()
                    .eq(OriginalOrderDetail::getOriginalOrderId, originalOrder.getOriginalOrderId()));
            List<OrderDetail> detailList = new ArrayList<>();
            for (OriginalOrderDetail detail : orderDetailList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setAmount(detail.getQuantity());
                BeanUtils.copyProperties(orderDetail,detail);
                detailList.add(orderDetail);
            }
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
//                log.info("i={} ,j ={} ,orderId1 = {} ,orderId2 = {}",i,j,list.get(i).getOriginalOrderId(),list.get(j).getOriginalOrderId());
                    }
                }
            }
            log.info(JsonUtil.beanToJson(combinedMap));
            combinedMap.forEach((mobile,idSet) -> {
                Order order =  new Order();
                BigDecimal totalPrice = new BigDecimal("0.00");
                Long orderId = IdWorker.getId();
                order.setOrderId(orderId);
                for (Long originOrderId : idSet) {
                    List<OriginalOrder> collect = list.stream().filter(originalOrder -> originalOrder.getOriginalOrderId().equals(originOrderId)).collect(Collectors.toList());
                    OriginalOrder originalOrder = collect.get(0);
                    orderDetailService.remove(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, originalOrder.getOrderId()));
                    orderService.remove(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId,originalOrder.getOrderId()));
                    order.setConsumerName(originalOrder.getConsumerName());
                    order.setCreateTime(LocalDateTime.now());
                    order.setIsPeriod(0);
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
                        BeanUtils.copyProperties(detail,orderDetail);
                        orderDetail.setOrderId(orderId);
                        orderDetail.setAmount(detail.getQuantity());
                        detailList.add(orderDetail);
                    }
                    orderDetailService.saveBatch(detailList);
                    originalOrder.setOrderId(orderId);
                    originalOrderService.updateById(originalOrder);
                }
                order.setTotalPrice(totalPrice);
                orderService.save(order);
            });
            log.info("================= 合单策略 END ==============");
        }

        log.info("================= 换货策略 START ============");

        log.info("================= 换货策略 END   ============");

        log.info("================= 赠品规则 START=============");
        log.info("================= 赠品规则 END  =============");

        Strategy accountStrategy = strategyList.get(OrderStrategyConstant.BLACKLIST_ACCOUNT - 1);
        if (accountStrategy.getIsStop() == 0) {
            log.info("================= 店铺订单拦截   =============");
            List<AccountBlacklistStrategy> blackList = absService.list();
            for (AccountBlacklistStrategy black : blackList) {

            }
        }


    }
}
