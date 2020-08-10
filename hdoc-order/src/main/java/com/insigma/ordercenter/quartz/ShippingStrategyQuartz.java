package com.insigma.ordercenter.quartz;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.insigma.ordercenter.constant.OrderStrategyConstant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.*;
import com.insigma.ordercenter.utils.JsonUtil;
import com.insigma.ordercenter.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

//    @Scheduled(fixedDelay = 2*60*1000)
    @GetMapping("shippingQuartz")
    public boolean shippingOrderDeal() {
        //从缓存获取策略
        Object redisStrategy = redisUtil.get("strategyList");
        List<Strategy> strategyList = JsonUtil.jsonToList(JsonUtil.beanToJson(redisStrategy),Strategy.class);
        if (null == strategyList || strategyList.size() == 0) {
            log.error("未获取到策略，无法进行处理");
            return false;
        }
        Strategy strategy = strategyList.get(OrderStrategyConstant.AUTO_SHIPPING - 1);
        Strategy remarkStrategy = strategyList.get(OrderStrategyConstant.REMARK - 1);
        //判断自动审单是否开启
        if (strategy.getIsStop() == 0) {
            Integer autoAuditTime = strategy.getAutoAuditTime();
//            Integer autoAuditTime = 4;
            //查询最近策略中配置的分钟数以外的订单
            List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery()
                    .le(Order::getCreateTime, LocalDateTime.now().minusMinutes(autoAuditTime)).eq(Order::getOrderStatus,0));
            List<Shop> shopList = shopService.list();
            for (Order order : orderList) {
                for (Shop shop : shopList) {
                    if(order.getShopId().equals(shop.getShopId())) {
                        if (shop.getStrategyStatus() == 1) {
                            order.setIsHandOrder(1);
                            order.setOrderStatus(1);
                            order.setErrorReason("该店铺策略为手动审单策略");
                            orderService.updateById(order);
                        }
                        List<OrderDetail> detailList = detailService.list(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, order.getOrderId()));
                        for (OrderDetail orderDetail : detailList) {
                            ShopProduct shopProduct = shopProductService.getOne(Wrappers.<ShopProduct>lambdaQuery().eq(ShopProduct::getShopId, order.getShopId()).eq(ShopProduct::getProductId, orderDetail.getProductId()));
                            if (null != shopProduct && shopProduct.getNumber() <= 0) {
                                order.setIsHandOrder(1);
                                order.setOrderStatus(1);
                                order.setErrorReason("该店铺的商品库存已不足，设置为手动审单");
                                orderService.updateById(order);
                            }
                        }
                    }
                }
                if (remarkStrategy.getIsStop() == 0) {
                    SendReceiveInfo sendReceiveInfo = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
                    if (StringUtils.isNotBlank(sendReceiveInfo.getReceiveRemark())||StringUtils.isNotBlank(sendReceiveInfo.getSendRemark())) {
                        order.setIsHandOrder(1);
                        order.setOrderStatus(1);
                        order.setErrorReason("该订单有备注 为手动审单");
                        orderService.updateById(order);
                    }
                }
            }
            List<Order> newOrderList = orderService.list(Wrappers.<Order>lambdaQuery()
                    .le(Order::getCreateTime, LocalDateTime.now().minusMinutes(autoAuditTime)).eq(Order::getOrderStatus,0));
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
                                Integer warehouseId = orderMatchWarehouse(order, orderDetail);
                                if (null != warehouseId) {
                                    shippingOrder.setWarehouseId(warehouseId);
                                    Warehouse warehouse = warehouseService.getById(warehouseId);
                                    shippingOrder.setExpressCompanyId(warehouse.getExpressCompanyId());
                                }
                                //如果当前商品分类符合拆单策略，则按照数量拆单
                                if (productTypes.contains(productType)) {
                                    for (int i = 0; i < orderDetail.getAmount(); i++) {
                                        shippingOrderService.save(shippingOrder);
                                        dso.setShippingOrderId(shippingOrder.getShippingOrderId());
                                        detailShippingService.save(dso);

                                    }
                                } else {
                                    shippingOrderService.save(shippingOrder);
                                    dso.setShippingOrderId(shippingOrder.getShippingOrderId());
                                    detailShippingService.save(dso);
                                }
                                order.setOrderStatus(4);
                                orderService.updateById(order);
                                typeSet.add(productType);
                            }  else {
                                //通过订单详情id找到之前已经生成相应商品分类的发货单 然后将发货单绑定订单详情
                                List<Long> shippingOrderId = shippingOrderService.getShippingOrderByProductType(orderDetail.getOrderDetailId());
                                dso.setShippingOrderId(shippingOrderId.get(0));
                                detailShippingService.save(dso);
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
    public Integer orderMatchWarehouse(Order order,OrderDetail detail) {
        //根据订单中的店铺去查找对应的仓库
        List<ShopWarehouse> list = shopWarehouseService.list(Wrappers.<ShopWarehouse>lambdaQuery().eq(ShopWarehouse::getShopId, order.getShopId()));
        for (ShopWarehouse shopWarehouse : list) {
            //查询仓库对应的负责区域
            List<WarehouseRegion> regionList = warehouseRegionService.list(Wrappers.<WarehouseRegion>lambdaQuery().eq(WarehouseRegion::getWarehouseId, shopWarehouse.getWarehouseId()));
            SendReceiveInfo sendReceiveInfo = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
            for (WarehouseRegion wr : regionList) {
                Integer regionId = wr.getRegionId();
                SysRegion region = regionService.detail(regionId);
                if (region.getName().equals(sendReceiveInfo.getProvince())) {
                    //根据当前商品查询仓库 按优先级排序
                    List<WarehouseProductRelation> wList = wprService.list(Wrappers.<WarehouseProductRelation>lambdaQuery()
                            .eq(WarehouseProductRelation::getProductId,detail.getProductId()).orderByAsc(WarehouseProductRelation::getPriority));
                    for (WarehouseProductRelation wp : wList) {
                        //判断如果仓库编号存在仓库有该商品的库存
                        if (wp.getWarehouseId().equals(shopWarehouse.getWarehouseId()) && wp.getQuantity() > 0) {
                            return wp.getWarehouseId();
                        }
                    }
                }
                //获取当前负责区域级别
//                Integer level = region.getLevel();

//                //如果级别为省
/*                if (level == 1) {
                    if (region.getName().equals(sendReceiveInfo.getProvince())) {
                        return shopWarehouse.getWarehouseId();
                    }
                }
                //如果级别为市
                if (level == 2) {
                    if (region.getName().equals(sendReceiveInfo.getLocationCity())) {
                        return shopWarehouse.getWarehouseId();
                    }
                }*/
            }
        }
        return null;
    }
}
