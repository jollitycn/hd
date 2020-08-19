package com.insigma.ordercenter.quartz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.OrderStatus;
import com.insigma.ordercenter.constant.OrderStrategyConstant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.AddShippingOrderDTO;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.*;
import com.insigma.ordercenter.utils.JsonUtil;
import com.insigma.ordercenter.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author youwk
 * @ClassName 自动审单处理
 * @description TODO
 * @date 2020/8/4 21:12
 * @Version 1.0
 */
@Configuration
@Slf4j
@RestController
public class ShippingStrategyQuartz {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IShopWarehouseService shopWarehouseService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IProductTypeStrategyService productTypeStrategyService;

    @Autowired
    private ISendReceiveInfoService sendReceiveInfoService;

    @Autowired
    private IShopProductService shopProductService;

    @Autowired
    private IOrderDetailService detailService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IShippingOrderService shippingOrderService;

    @Autowired
    private IDetailShippingOrderRelationService detailShippingService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IWarehouseRegionService warehouseRegionService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private IWarehouseProductRelationService wprService;

    @Autowired
    private IStrategyProductTypeService sptService;

    @Autowired
    private IStrategyRegionRelationService srrService;

    @Autowired
    private IStrategyWarehouseRelationService swrService;

    @Autowired
    private DefaultMQProducer mqProducer;

    //    @Scheduled(fixedDelay = 2*60*1000)
    @GetMapping("shippingQuartz")
    public Result<?> shippingOrderDeal() {
        //从缓存获取策略
        Object redisStrategy = redisUtil.get("strategyList");
        List<Strategy> strategyList = JsonUtil.jsonToList(JsonUtil.beanToJson(redisStrategy), Strategy.class);
        if (null == strategyList || strategyList.size() == 0) {
            log.error("未获取到策略，无法进行处理");
            return Result.success();
        }
        Strategy strategy = strategyList.get(OrderStrategyConstant.AUTO_SHIPPING - 1);
        Strategy remarkStrategy = strategyList.get(OrderStrategyConstant.REMARK - 1);
        //判断自动审单是否开启
        if (strategy.getIsStop() == 0) {
            Integer autoAuditTime = strategy.getAutoAuditTime();
//            Integer autoAuditTime = 4;
            //查询最近策略中配置的分钟数以外的订单
            List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery()
                    .le(Order::getCreateTime, LocalDateTime.now().minusMinutes(autoAuditTime)).eq(Order::getOrderStatus, 2));
            List<Shop> shopList = shopService.list();
            for (Order order : orderList) {
                for (Shop shop : shopList) {
                    if (order.getShopId().equals(shop.getShopId())) {
                        if (shop.getStrategyStatus() == 0) {
                            order.setIsHandOrder(1);
                            order.setOrderStatus(1);
                            order.setErrorReason("该店铺策略为手动审单策略");
                            orderService.updateById(order);
                        }
                        List<OrderDetail> detailList = detailService.list(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, order.getOrderId()));
                        for (OrderDetail orderDetail : detailList) {
                            //判断商品库存
                            ShopProduct shopProduct = shopProductService.getOne(Wrappers.<ShopProduct>lambdaQuery().eq(ShopProduct::getShopId, order.getShopId()).eq(ShopProduct::getProductId, orderDetail.getProductId()));

                            if (null == shopProduct) {
                                order.setIsHandOrder(1);
                                order.setOrderStatus(OrderStatus.HANDLE);
                                order.setErrorReason("该店铺的商品没有配置库存，设置为手动审单");
                                orderService.updateById(order);
                            } else if (shopProduct.getNumber() <= 0) {
                                order.setIsHandOrder(1);
                                order.setOrderStatus(OrderStatus.HANDLE);
                                order.setErrorReason("该店铺的商品库存已不足，设置为手动审单");
                                orderService.updateById(order);
                            } else if (shopProduct.getRatio() == null || shopProduct.getRatio() == 0) {
                                order.setIsHandOrder(1);
                                order.setOrderStatus(OrderStatus.HANDLE);
                                order.setErrorReason("不符合该商品的发货比例，设置为手动审单");
                                orderService.updateById(order);
                            }
                        }
                    }
                }
                if (remarkStrategy.getIsStop() == 0) {
                    SendReceiveInfo sendReceiveInfo = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
                    if (StringUtils.isNotBlank(sendReceiveInfo.getReceiveRemark()) || StringUtils.isNotBlank(sendReceiveInfo.getSendRemark())) {
                        order.setIsHandOrder(1);
                        order.setOrderStatus(1);
                        order.setErrorReason("该订单有备注 为手动审单");
                        orderService.updateById(order);
                    }
                }
            }
            List<Order> newOrderList = orderService.list(Wrappers.<Order>lambdaQuery().le(Order::getCreateTime, LocalDateTime.now()
                    .minusMinutes(autoAuditTime)).eq(Order::getOrderStatus, 2).eq(Order::getIsHandOrder, 0));
            //商品分类策略
            Strategy productTypeStrategy = strategyList.get(OrderStrategyConstant.PRODUCT_TYPE_DIVIDE - 1);
            //需要拆单的商品分类
            Set<Long> productTypes = productTypeStrategyService.list().stream().map(ProductTypeStrategy::getProductTypeId).collect(Collectors.toSet());
            //店铺仓库策略
            Strategy shopWarehouseStrategy = strategyList.get(OrderStrategyConstant.SHOP_WAREHOUSE - 1);
            for (Order order : newOrderList) {
                SendReceiveInfo sendReceiveInfo = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
                List<OrderDetail> orderDetailList = orderDetailService.list(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, order.getOrderId()));
                if (productTypeStrategy.getIsStop() == 0) {
                    Set<Long> typeSet = new HashSet<>();
                    //获取订单明细中的商品
                    for (OrderDetail orderDetail : orderDetailList) {
                        Product product = productService.getById(orderDetail);
                        DetailShippingOrderRelation dso = new DetailShippingOrderRelation();
                        dso.setOrderDetailId(orderDetail.getOrderDetailId());
                        //如果商品存在
                        if (null != product) {
                            Long productType = product.getProductType();
                            //如果商品分类集合已经不包含当前分类则创建一个发货单
                            if (!typeSet.contains(productType)) {
                                ShippingOrder shippingOrder = new ShippingOrder();
                                shippingOrder.setStatus(0);
                                shippingOrder.setProvince(sendReceiveInfo.getProvince());
                                shippingOrder.setLocationCity(sendReceiveInfo.getLocationCity());
                                shippingOrder.setDistrict(sendReceiveInfo.getDistrict());
                                shippingOrder.setAddress(sendReceiveInfo.getAddress());
                                shippingOrder.setCreateId(1L);
                                shippingOrder.setCreateTime(LocalDateTime.now());
                                shippingOrder.setIsCombined(0);
                                shippingOrder.setIsDeleted(0);
                                shippingOrder.setIsRefuse(0);
                                shippingOrder.setMobilePhone(sendReceiveInfo.getMobilePhone());
                                shippingOrder.setOrderId(order.getOrderId());
                                shippingOrder.setReceiveName(sendReceiveInfo.getReceiveName());
                                shippingOrder.setReceiveRemark(sendReceiveInfo.getReceiveRemark());
                                shippingOrder.setShippingOrderNo(orderService.getSerializeNo("FH"));
                                Warehouse warehouse = orderMatchWarehouse(order, orderDetail);
                                if (null != warehouse) {
                                    shippingOrder.setWarehouseId(warehouse.getWarehouseId());
                                    shippingOrder.setExpressCompanyId(warehouse.getExpressCompanyId());
                                } else {
                                    order.setOrderStatus(OrderStatus.CHECK_ERROR);
                                    order.setErrorReason("商品:" + orderDetail.getProductName() + "未匹配到仓库");
                                    orderService.updateById(order);
                                    break;
                                }
                                //如果当前商品分类符合拆单策略，则按照数量拆单
                                if (productTypes.contains(productType)) {
                                    for (int i = 0; i < orderDetail.getAmount(); i++) {
                                        shippingOrderService.save(shippingOrder);
                                        dso.setShippingOrderId(shippingOrder.getShippingOrderId());
                                        detailShippingService.save(dso);
                                    }
                                } else {
                                    //否则直接绑定发货单和订单详情
                                    shippingOrderService.save(shippingOrder);
                                    dso.setShippingOrderId(shippingOrder.getShippingOrderId());
                                    detailShippingService.save(dso);
                                }
                                //订单状态更新为待出库
                                order.setOrderStatus(4);
                                orderService.updateById(order);
                                typeSet.add(productType);
                            } else {
                                //通过订单详情id找到之前已经生成相应商品分类的发货单 然后将发货单绑定订单详情
                                List<Long> shippingOrderId = shippingOrderService.getShippingOrderByProductType(orderDetail.getOrderDetailId());
                                ShippingOrder shippingOrder = shippingOrderService.getById(shippingOrderId.get(0));
                                //判断该发货单的仓库是否有该商品的库存 如果没有 则需要查找新仓库 新建发货单
                                Integer warehouseId = shippingOrder.getWarehouseId();
                                WarehouseProductRelation wp = wprService.getOne(Wrappers.<WarehouseProductRelation>lambdaQuery().eq(WarehouseProductRelation
                                        ::getProductId, orderDetail.getProductId()).eq(WarehouseProductRelation::getWarehouseId, warehouseId));
                                if (null == wp || (wp.getQuantity() <= 0)) {
                                    Warehouse newWarehouse = orderMatchWarehouse(order, orderDetail);
                                    //查看仓库是否存在
                                    if (null != newWarehouse) {
                                        //判断订单中是否存在仓库和承运商一致的发货单
                                        ShippingOrder so0 = shippingOrderService.getOne(
                                                Wrappers.<ShippingOrder>lambdaQuery().eq(ShippingOrder::getOrderId, order.getOrderId())
                                                        .eq(ShippingOrder::getWarehouseId, newWarehouse.getWarehouseId())
                                                        .eq(ShippingOrder::getExpressCompanyId, newWarehouse.getExpressCompanyId())
                                        );
                                        //如果不存在则新建发货单
                                        if (so0 == null) {
                                            ShippingOrder so = new ShippingOrder();
                                            BeanUtils.copyProperties(shippingOrder, so);
                                            so.setExpressCompanyId(newWarehouse.getExpressCompanyId());
                                            so.setWarehouseId(newWarehouse.getWarehouseId());
                                            so.setShippingOrderNo(orderService.getSerializeNo("FH"));
                                            so.setShippingOrderId(IdWorker.getId());
                                            shippingOrderService.save(so);
                                            dso.setShippingOrderId(so.getShippingOrderId());
                                        } else {
                                            //已存在发货单则将发货单绑定商品详情
                                            dso.setShippingOrderId(so0.getShippingOrderId());
                                        }
                                        detailShippingService.save(dso);
                                    } else {
                                        //仓库不存在则审单异常 删掉该订单关联的发货单
                                        shippingOrderService.remove(Wrappers.<ShippingOrder>lambdaQuery().eq(ShippingOrder::getOrderId, order.getOrderId()));
                                        order.setOrderStatus(OrderStatus.CHECK_ERROR);
                                        order.setErrorReason("匹配到的仓库不存在 无法生成发货单");
                                        orderService.updateById(order);
                                        break;
                                    }
                                } else {
                                    dso.setShippingOrderId(shippingOrderId.get(0));
                                    detailShippingService.save(dso);
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
                //生成发货单给上游服务发送发货单状态消息
                List<ShippingOrder> shippingOrderList = shippingOrderService.list(Wrappers.<ShippingOrder>lambdaQuery().eq(ShippingOrder::getOrderId, order.getOrderId()));
                for (ShippingOrder shippingOrder : shippingOrderList) {
                    AddShippingOrderDTO dto = new AddShippingOrderDTO();
                    BeanUtils.copyProperties(shippingOrder, dto);
                    List<DetailShippingOrderRelation> list = detailShippingService.list(Wrappers.<DetailShippingOrderRelation>lambdaQuery().eq(DetailShippingOrderRelation::getShippingOrderId, shippingOrder.getShippingOrderId()));
                    List<OrderDetail> detailList = new ArrayList<>();
                    for (DetailShippingOrderRelation dso : list) {
                        OrderDetail orderDetail = orderDetailService.getById(dso.getOrderDetailId());
                        Product product = productService.getById(orderDetail.getProductId());
                        orderDetail.setProductSku(product.getProductNo());
                        detailList.add(orderDetail);
                    }
                    dto.setOrderDetails(detailList);
                    dto.setOrderStatus(order.getOrderStatus());
                    dto.setOriginOrderNo(order.getOriginOrderNo());
                    dto.setOrderNo(order.getOrderNo());
                    dto.setOrderId(order.getOrderId());
                    Message message = null;
                    try {
                        message = new Message("split_order",
                                "store_card",
                                JsonUtil.beanToJson(dto).getBytes(RemotingHelper.DEFAULT_CHARSET));
                        mqProducer.send(message, new SendCallback() {
                            @Override
                            public void onSuccess(SendResult sendResult) {
                                System.out.println("消息发送成功");
                            }

                            @Override
                            public void onException(Throwable e) {
                                e.printStackTrace();
                                System.out.println("消息发送异常");
                            }
                        });
                    } catch (MQClientException | RemotingException | InterruptedException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.success();
    }

    public Warehouse orderMatchWarehouse(Order order, OrderDetail detail) {
        Integer productType = detail.getProductType();
        StrategyProductType spt = sptService.getOne(Wrappers.<StrategyProductType>lambdaQuery().eq(StrategyProductType::getParamType, 1)
                .eq(StrategyProductType::getParamId, productType).eq(StrategyProductType::getIsStop, 0));
        if (null == spt) {
            return null;
        }
        //获取店铺关联仓库
        List<ShopWarehouse> list = shopWarehouseService.list(Wrappers.<ShopWarehouse>lambdaQuery().eq(ShopWarehouse::getShopId, order.getShopId()));
        //获取商品分类关联仓库
        List<StrategyWarehouseRelation> wList = swrService.list(Wrappers.<StrategyWarehouseRelation>lambdaQuery().eq(StrategyWarehouseRelation::getSptId, spt.getStrategyProductTypeId()));
        //获取商品分类负责区域
        List<StrategyRegionRelation> regionList = srrService.list(Wrappers.<StrategyRegionRelation>lambdaQuery().eq(StrategyRegionRelation::getSptId, spt.getStrategyProductTypeId()));
        //获取收货人地址
        SendReceiveInfo sendReceiveInfo = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
        for (StrategyRegionRelation srr : regionList) {
            SysRegion region = regionService.detail(srr.getRegionId());
            //如果匹配到负责区域
            if ((region.getLevel() == 1 && sendReceiveInfo.getProvince().equals(region.getName())) || (region.getLevel() == 2 && sendReceiveInfo.getLocationCity().equals(region.getName()))) {
                //准备一个Map存放仓库编号和对应的优先级
                Map<Integer, Integer> map = new HashMap<>();
                for (StrategyWarehouseRelation swr : wList) {
                    WarehouseProductRelation wpr = wprService.getOne(Wrappers.<WarehouseProductRelation>lambdaQuery().eq(WarehouseProductRelation::getWarehouseId,
                            swr.getWarehouseId()).eq(WarehouseProductRelation::getProductId, detail.getProductId()));
                    if (null != wpr) {
                        for (ShopWarehouse shopWarehouse : list) {
                            if (swr.getWarehouseId().equals(shopWarehouse.getWarehouseId())) {
                                map.put(swr.getWarehouseId(),wpr.getPriority());
                            }
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(map)) {
                    //按照优先级进行排序 取第一个优先级为最高
                    List<Integer> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
                    return warehouseService.getById(collect.get(0));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        HashMap<Integer, Integer> finalMap = new HashMap<>();
        map.put(2001,4);
        map.put(2002,10);
        map.put(2003,6);
        map.put(2004,1);
        map.put(2005,8);
        HashMap<Integer, Integer> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldVal, newVal) -> oldVal,
                HashMap::new));
        System.out.println(collect);
    }


}
