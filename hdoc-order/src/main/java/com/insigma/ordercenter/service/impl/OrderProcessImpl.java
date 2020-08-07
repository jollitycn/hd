package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author youwk
 * @ClassName 订单处理流程
 * @description TODO
 * @date 2020/7/31 11:07
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderProcessImpl implements IOrderProcessService {

    @Autowired
    private IAccountBlacklistStrategyService blacklistStrategyService;

    @Autowired
    private IPhoneBlacklistStrategyService phoneBlacklistStrategyService;

    @Autowired
    private IRegionBlacklistStrategyService regionBlacklistStrategyService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private ISendReceiveInfoService sendReceiveInfoService;

    @Autowired
    private IExchangeStrategyService exchangeStrategyService;

    @Autowired
    private IParamShopService paramShopService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IGiftService giftService;

    @Autowired
    private IGiftStrategyService giftStrategyService;

    @Autowired
    private IOriginalOrderService originalOrderService;

    @Override
    public Map<String, Set<Long>> combinedOriginOrder(List<OriginalOrder> originalOrderList) {
        return null;
    }

    @Override
    public Result<?> exchangeProductStrategy(Long batchNo) {
        List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));
        for (Order order : orderList) {
            //获取订单详情列表
            List<OrderDetail> orderDetailList = orderDetailService.list(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderId, order.getOrderId()));
            //获取换货策略
            List<ExchangeStrategy> list = exchangeStrategyService.list(Wrappers.<ExchangeStrategy>lambdaQuery()
                    .eq(ExchangeStrategy::getIsStop, 0).le(ExchangeStrategy::getStartDate, LocalDate.now()).ge(ExchangeStrategy::getEndDate, LocalDate.now()));
            for (OrderDetail orderDetail : orderDetailList) {
                for (ExchangeStrategy exchange : list) {
                    //判断换货策略列表中有没有和订单明细匹配的商品编号
                    if (exchange.getOldProductId().equals(orderDetail.getProductId())) {
                        //如果该策略能匹配到店铺，则进行换货
                        ParamShop paramShop = paramShopService.getOne(Wrappers.<ParamShop>lambdaQuery().eq(ParamShop::getParamId, exchange.getExchangeStrategyId()).eq(ParamShop::getShopId, order.getShopId()));
                        if (null != paramShop) {
                            Product newProduct = productService.getById(exchange.getNewProductId());
                            if (null == newProduct) {
                                log.error("新商品：{} 不存在", exchange.getNewProductId());
                                break;
                            }
                            orderDetail.setProductId(newProduct.getProductId());
                            orderDetail.setProductName(newProduct.getProductName());
                            orderDetail.setProductPrice(newProduct.getProductPrice());
                            orderDetail.setProductSku(newProduct.getProductSku());
                            orderDetail.setProductSpecs(newProduct.getProductSpecs());
                            orderDetail.setUnit(newProduct.getUnit());
                            orderDetail.setTotalPrice(newProduct.getProductPrice().multiply(new BigDecimal(orderDetail.getAmount())));
                            orderDetailService.updateById(orderDetail);
                        }
                    }
                }
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> giftProductStrategy(Long batchNo) {
        List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));
        for (Order order : orderList) {
            //获取赠品策略
            List<GiftStrategy> list = giftStrategyService.list(Wrappers.<GiftStrategy>lambdaQuery()
                    .eq(GiftStrategy::getIsStop, 0).le(GiftStrategy::getStartDate, LocalDate.now()).ge(GiftStrategy::getEndDate, LocalDate.now()));
            for (GiftStrategy giftStrategy : list) {
                //判断换货策略列表中有没有和订单明细匹配的商品编号
                //如果该策略能匹配到店铺，则在订单明细中 添加赠品
                ParamShop paramShop = paramShopService.getOne(Wrappers.<ParamShop>lambdaQuery().eq(ParamShop::getParamId, giftStrategy.getGiftStrategyId()).eq(ParamShop::getShopId, order.getShopId()));
                if (null != paramShop) {
                    List<Gift> giftList = giftService.list(Wrappers.<Gift>lambdaQuery().eq(Gift::getGiftStrategyId, giftStrategy.getGiftStrategyId()));
                    for (Gift gift : giftList) {
                        Product giftProduct = productService.getById(gift.getProductId());
                        if (null != giftProduct) {
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setProductId(gift.getProductId());
                            orderDetail.setAmount(gift.getGiftNum());
                            orderDetail.setProductName(giftProduct.getProductName());
                            orderDetail.setOrderId(order.getOrderId());
                            orderDetail.setProductPrice(giftProduct.getProductPrice());
                            orderDetail.setProductSku(giftProduct.getProductSku());
                            orderDetail.setProductSpecs(giftProduct.getProductSpecs());
                            orderDetail.setUnit(giftProduct.getUnit());
                            orderDetail.setTotalPrice(giftProduct.getProductPrice().multiply(new BigDecimal(orderDetail.getAmount())));
                            orderDetailService.save(orderDetail);
                        }
                    }
                }
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> shopBlackStrategy(Long batchNo) {
        List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));
        List<AccountBlacklistStrategy> blackList = blacklistStrategyService.list();
        for (Order order : orderList) {
            SendReceiveInfo sendReceive = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
            for (AccountBlacklistStrategy blackAccount : blackList) {
                Long shopId = blackAccount.getShopId();
                String account = blackAccount.getAccount();
                if (shopId.equals(order.getShopId()) && account.equals(sendReceive.getBuyerNickname())) {
                    order.setErrorReason(CodeMsg.STRATEGY_BLACK_ACCOUNT.getMessage() + "买家账号为：" + account);
                    order.setIsHandOrder(1);
                    order.setOrderStatus(1);
                    orderService.updateById(order);
                }
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> mobileStrategy(Long batchNo) {
        List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));
        for (Order order : orderList) {
        List<PhoneBlacklistStrategy> phoneList = phoneBlacklistStrategyService.list();
            for (PhoneBlacklistStrategy phoneBlack : phoneList) {
                if (order.getShopId().equals(phoneBlack.getShopId()) && order.getMobilePhone().equals(phoneBlack.getPhone())) {
                    order.setIsHandOrder(1);
                    order.setOrderStatus(1);
                    order.setErrorReason(order.getErrorReason()==null?"":order.getErrorReason() + "  " + CodeMsg.STRATEGY_BLACK_PHONE.getMessage());
                    orderService.updateById(order);
                }
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> districtStrategy(Long batchNo) {
        List<Order> orderList = orderService.list(Wrappers.<Order>lambdaQuery().eq(Order::getBatchNo, batchNo));
        List<RegionBlacklistStrategy> blackRegionList = regionBlacklistStrategyService.list();
        for (Order order : orderList) {
            SendReceiveInfo sendReceive = sendReceiveInfoService.getOne(Wrappers.<SendReceiveInfo>lambdaQuery().eq(SendReceiveInfo::getOrderId, order.getOrderId()));
            for (RegionBlacklistStrategy blackRegion : blackRegionList) {
                if (order.getShopId().equals(blackRegion.getShopId()) && sendReceive.getProvince().equals(blackRegion.getProvince())
                        && sendReceive.getLocationCity().equals(blackRegion.getCity())) {
                    order.setErrorReason(order.getErrorReason()==null?"":order.getErrorReason() + " " + CodeMsg.STRATEGY_BLACK_REGION.getMessage());
                    order.setIsHandOrder(1);
                    order.setOrderStatus(1);
                    orderService.updateById(order);
                }
            }
        }
        return Result.success();
    }

}
