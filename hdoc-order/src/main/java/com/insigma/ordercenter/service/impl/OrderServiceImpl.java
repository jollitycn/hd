package com.insigma.ordercenter.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.constant.OrderStatus;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.*;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.mapper.OrderMapper;
import com.insigma.ordercenter.service.*;
import com.insigma.ordercenter.utils.DateUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private IOrderService orderService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private IShippingOrderService shippingOrderService;

    @Resource
    private IDetailShippingOrderRelationService detailShippingOrderRelationService;

    @Resource
    private IOrderSendReceiveService orderSendReceiveService;

    @Resource
    private IShopService shopService;

    @Resource
    private DefaultMQProducer producer;

    @Override
    public IPage<OrderListVO> queryOrderListPage(Page<OrderListVO> page, OrderDTO orderDTO) {
        return baseMapper.queryOrderListPage(page, orderDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addOrder(SendReceiveInfoVO sendReceiveInfoVO) {
        try {
            if(sendReceiveInfoVO.getSaveStatus()==0){

                //手动新增订单时，生成订单号
                String orderId = generateOrderNo(sendReceiveInfoVO.getShopId());
                //新增订单表信息
                Order order = new Order();
                order.setOrderNo(orderId);
                order.setCreateTime(LocalDateTime.now());
                order.setConsumerName(sendReceiveInfoVO.getConsumerName());
                order.setShopId(sendReceiveInfoVO.getShopId());
                order.setIsCombined(Constant.SYS_ZERO);
                order.setIsHandOrder(Constant.SYS_ONE);
                order.setMobilePhone(sendReceiveInfoVO.getMobilePhone());
                order.setConsumerName(sendReceiveInfoVO.getConsumerName());
                orderService.save(order);

                //新增订单收发件人信息
                OrderSendReceive sendReceiveInfo = new OrderSendReceive();
                sendReceiveInfo.setOrderId(order.getOrderId());
                sendReceiveInfo.setSendName(sendReceiveInfoVO.getSendName());
                sendReceiveInfo.setSendRemark(sendReceiveInfoVO.getSendRemark());
                sendReceiveInfo.setOrderTime(LocalDateTime.now());
                sendReceiveInfo.setMobilePhone(sendReceiveInfoVO.getMobilePhone());
                sendReceiveInfo.setAddress(sendReceiveInfoVO.getAddress());

                sendReceiveInfo.setReceiveRemark(sendReceiveInfoVO.getReceiveRemark());
                sendReceiveInfo.setOrderReason(sendReceiveInfoVO.getOrderReason());
                sendReceiveInfo.setLocationCity(sendReceiveInfoVO.getLocationCity());
                sendReceiveInfo.setProvince(sendReceiveInfoVO.getProvince());
                sendReceiveInfo.setLoginName(sendReceiveInfoVO.getLoginName());
                sendReceiveInfo.setRequestTime(sendReceiveInfoVO.getRequestTime());
                sendReceiveInfo.setSendRemark(sendReceiveInfoVO.getSendRemark());
                orderSendReceiveService.save(sendReceiveInfo);

                //新增订单明细信息
                if (null != sendReceiveInfoVO.getOrderDetails() && sendReceiveInfoVO.getOrderDetails().size() > 0) {
                    sendReceiveInfoVO.getOrderDetails().forEach(OrderDetail -> {
                        OrderDetail.setOrderId(order.getOrderId());
                        orderDetailService.save(OrderDetail);
                    });
                }
            }else{
                //删除原有的商品明细列表
                if (null != sendReceiveInfoVO.getOrderDetails() && sendReceiveInfoVO.getOrderDetails().size() > 0) {
                    orderDetailService.removeById(sendReceiveInfoVO.getOrderId());
                }
                //修改订单表信息
                Order order = new Order();
                order.setOrderId(sendReceiveInfoVO.getOrderId());
                order.setConsumerName(sendReceiveInfoVO.getConsumerName());
                order.setShopId(sendReceiveInfoVO.getShopId());
                order.setIsCombined(Constant.SYS_ZERO);
                order.setIsHandOrder(Constant.SYS_ONE);
                order.setMobilePhone(sendReceiveInfoVO.getMobilePhone());
                orderService.updateById(order);

                //修改订单收发件人信息
                OrderSendReceive sendReceiveInfo = new OrderSendReceive();
                sendReceiveInfo.setSendReceiveInfoId(sendReceiveInfoVO.getSendReceiveInfoId());
                sendReceiveInfo.setOrderId(order.getOrderId());
                sendReceiveInfo.setSendName(sendReceiveInfoVO.getSendName());
                sendReceiveInfo.setSendRemark(sendReceiveInfoVO.getSendRemark());
                sendReceiveInfo.setOrderTime(LocalDateTime.now());
                sendReceiveInfo.setMobilePhone(sendReceiveInfoVO.getMobilePhone());
                sendReceiveInfo.setAddress(sendReceiveInfoVO.getAddress());

                sendReceiveInfo.setReceiveRemark(sendReceiveInfoVO.getReceiveRemark());
                sendReceiveInfo.setOrderReason(sendReceiveInfoVO.getOrderReason());
                sendReceiveInfo.setLocationCity(sendReceiveInfoVO.getLocationCity());
                sendReceiveInfo.setProvince(sendReceiveInfoVO.getProvince());
                sendReceiveInfo.setLoginName(sendReceiveInfoVO.getLoginName());
                orderSendReceiveService.updateById(sendReceiveInfo);

                //修改订单明细信息，先删除之前的，再保存

                if (null != sendReceiveInfoVO.getOrderDetails() && sendReceiveInfoVO.getOrderDetails().size() > 0) {
                    sendReceiveInfoVO.getOrderDetails().forEach(orderDetail -> {
                        orderDetail.setOrderId(order.getOrderId());
                        orderDetailService.save(orderDetail);
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
        return Result.success();
    }

    @Override
    public Boolean updateOrderStatu(UpdateOrderStatuDTO updateOrderStatuDTO) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(updateOrderStatuDTO, orderDetail);
        return orderDetailService.updateById(orderDetail);
    }

    @Override
    public List<OrderDetailExamineVO> queryOrderDetailList(Long orderId) {
        return baseMapper.queryOrderDetailList(orderId);
    }

    @Override
    public List<ExpressCompanyVO> queryExpressCompany(Long warehouseId) {
        return baseMapper.queryExpressCompany(warehouseId);
    }

    public void sendMQMessage(Long orderId,List<AddShippingOrderDTO> shippingOrderDTOS){
        //查询修改后的订单信息                List<AddShippingOrderDTO> addShippingOrderDTOS
        Order order = orderService.getById(orderId);
        Shop shop = shopService.getById(order.getShopId());

        //组装需要发送消息的实体
        shippingOrderDTOS.forEach(addShippingOrderDTO -> {
            addShippingOrderDTO.setOrderId(order.getOrderId());
            addShippingOrderDTO.setOriginOrderNo(order.getOriginOrderNo());
            addShippingOrderDTO.setOrderNo(order.getOrderNo());
            addShippingOrderDTO.setOrderStatus(order.getOrderStatus());
        });

        Message message = null;
        Object obj = JSONArray.toJSON(shippingOrderDTOS);
        String str = obj.toString();
        try {
            message = new Message("split_order",
                    "store_card",
                    str.getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            producer.send(message, new SendCallback() {
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
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Result addShippingOrder(AddShippingOrderResultDTO addShippingOrderResultDTO) {
        if (addShippingOrderResultDTO.getAddShippingOrderDTOS().size() == 0) {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
        addShippingOrderResultDTO.getAddShippingOrderDTOS().forEach(addShippingOrderDTO -> {
            ShippingOrder shippingOrder = new ShippingOrder();
            shippingOrder.setWarehouseId(addShippingOrderDTO.getWarehouseId());
            shippingOrder.setExpressCompanyId(addShippingOrderDTO.getExpressCompanyId());
            shippingOrder.setCreateId(addShippingOrderDTO.getCreateId());
            shippingOrder.setCreateTime(LocalDateTime.now());
            shippingOrder.setIsDeleted(Constant.SYS_ZERO);
            shippingOrder.setStatus(Constant.SYS_ZERO);
            shippingOrder.setReceiveName(addShippingOrderDTO.getReceiveName());
            shippingOrder.setMobilePhone(addShippingOrderDTO.getMobilePhone());
            shippingOrder.setAddress(addShippingOrderDTO.getAddress());
            shippingOrder.setIsCombined(Constant.SYS_ZERO);
            shippingOrder.setShippingOrderNo(addShippingOrderDTO.getShippingOrderNo());
            shippingOrder.setShippingOrderId(addShippingOrderDTO.getShippingOrderId());
            shippingOrder.setOrderId(addShippingOrderDTO.getOrderId());
            shippingOrderService.save(shippingOrder);
            DetailShippingOrderRelation detailShippingOrderRelation = new DetailShippingOrderRelation();
            detailShippingOrderRelation.setOrderDetailId(addShippingOrderDTO.getOrderDetailId());
            detailShippingOrderRelation.setShippingOrderId(shippingOrder.getShippingOrderId());
            detailShippingOrderRelationService.save(detailShippingOrderRelation);

            // 审单时，发送队列消息到卡夫卡，给储值卡系统消费(* 给原始订单Id,发货单信息)
            Message message = null;
            Object obj = JSONArray.toJSON(addShippingOrderDTO);
            String str = obj.toString();
            try {
                message = new Message("split_order",
                        "store_card",//以店铺编码来分类消息
                        str.getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                producer.send(message, new SendCallback() {
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
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return Result.success();
    }


    @Override
    public Boolean shippingOrderStatuChange(UpdateShippingOrderStatuDTO updateOrderStatuDTO) {
        //提供修改订单的接口，在发货单状态改变的时候，调用修改订单状态接口
        //发货单状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消 5：拒收 6:异常 7：已完成）
        //订单状态：订单状态（0：新建状态，1：手动审核状态，2：待审核状态，3：审核异常状态，4：待出库状态，
        //      5：已出库状态，6：冻结状态，7：发货异常状态，8：已完成状态，9：取消状态，10：已退货状态）
        //取消
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        AtomicReference<Integer> count = new AtomicReference<>(0);
        //完成
        AtomicReference<Boolean> slag = new AtomicReference<>(true);
        AtomicReference<Integer> completeCount = new AtomicReference<>(0);
        //第一步：随着发货单状态，修改订单状态
        if(null == updateOrderStatuDTO.getOrderId()){
            return false;
        }
        List<AddShippingOrderDTO> shippingOrderCancelVOS=orderMapper.queryShippingOrderListByOrderId(updateOrderStatuDTO.getOrderId());

        shippingOrderCancelVOS.forEach(ShippingOrderCancelVO -> {

            //当有一个发货单为已发货状态，订单状态为已出库状态
            if(ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_FIVE){
                UpdateOrderStatuDTO orderStatuDTO = new UpdateOrderStatuDTO();
                orderStatuDTO.setOrderId(updateOrderStatuDTO.getOrderId());
                //订单状态 2 为已发货
                orderStatuDTO.setOrderStatus(OrderStatus.ORDER_TWO);
                orderService.updateOrderStatu(orderStatuDTO);
                return;
            }

            //当有一个发货单为拒收时，订单状态为冻结
            //异常时，订单状态不变
            //冻结时，是由订单状态引起的，发货单状态冻结
            if(ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_FIVE ){
                UpdateOrderStatuDTO orderStatuDTO = new UpdateOrderStatuDTO();
                orderStatuDTO.setOrderId(updateOrderStatuDTO.getOrderId());
                //订单状态 6 为冻结
                orderStatuDTO.setOrderStatus(OrderStatus.ORDER_SIX);
                orderService.updateOrderStatu(orderStatuDTO);
                return;
            }

            //当所有的发货单为已完成状态，订单状态为已完成
            if(ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_FOUR){
                slag.set(false);
            }else {
                completeCount.getAndSet(completeCount.get() + 1);
            }

            //当所有的发货单为取消时，订单状态为取消
            if(ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_FOUR){
                flag.set(false);
            }else {
                count.getAndSet(count.get() + 1);
            }
        });
        if (flag.get() && shippingOrderCancelVOS.size() == count.get()) {
            UpdateOrderStatuDTO orderStatuDTO = new UpdateOrderStatuDTO();
            orderStatuDTO.setOrderId(updateOrderStatuDTO.getOrderId());
            //订单状态 9 为取消
            orderStatuDTO.setOrderStatus(OrderStatus.ORDER_NINE);
            orderService.updateOrderStatu(orderStatuDTO);
        }

        if (slag.get() && shippingOrderCancelVOS.size() == completeCount.get()) {
            UpdateOrderStatuDTO orderStatuDTO = new UpdateOrderStatuDTO();
            orderStatuDTO.setOrderId(updateOrderStatuDTO.getOrderId());
            //订单状态 8 为已完成
            orderStatuDTO.setOrderStatus(OrderStatus.ORDER_EIGHT);
            orderService.updateOrderStatu(orderStatuDTO);
        }

        //第二步：发送MQ消息
        this.sendMQMessage(updateOrderStatuDTO.getOrderId(),shippingOrderCancelVOS);

        return true;
    }


    @Override
    public Result cancelOrder(Long orderId) {
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        AtomicReference<Integer> count = new AtomicReference<>(0);
        List<ShippingOrderCancelVO> shippingOrderCancelVOS = orderMapper.cancelOrder(orderId);
        shippingOrderCancelVOS.forEach(ShippingOrderCancelVO -> {
            if (null != ShippingOrderCancelVO && ShippingOrderCancelVO.getStatus() != null) {
                //发货单状态，3：冻结；5：拒收；6：异常
                if (ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_THREE || ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_FIVE || ShippingOrderCancelVO.getStatus() == OrderStatus.SHIPPING_ORDER_SIX) {
                    UpdateOrderStatuDTO updateOrderStatuDTO = new UpdateOrderStatuDTO();
                    updateOrderStatuDTO.setOrderId(orderId);
                    updateOrderStatuDTO.setOrderStatus(OrderStatus.ORDER_SIX);
                    orderService.updateOrderStatu(updateOrderStatuDTO);
                    flag.set(false);
                } else {
                    count.getAndSet(count.get() + 1);
                }
            }
        });
        if (flag.get() && shippingOrderCancelVOS.size() == count.get()) {
            UpdateOrderStatuDTO updateOrderStatuDTO = new UpdateOrderStatuDTO();
            updateOrderStatuDTO.setOrderId(orderId);
            //订单状态 5 为取消
            updateOrderStatuDTO.setOrderStatus(OrderStatus.ORDER_FIVE);
            orderService.updateOrderStatu(updateOrderStatuDTO);
        }
        // TODO 发货单那边提供接口，返回是否能取消成功，否则订单状态为冻结
        return Result.success();
    }

    @Override
    public OrderListVO queryOrderById(Long orderId) {
        return baseMapper.queryOrderById(orderId);
    }

    @Override
    public List<OriginalOrderVO> queryOriginalOrderList(Long orderId) {
        return baseMapper.queryOriginalOrderList(orderId);
    }

    @Override
    public List<RefundInfoVO> queryRefundInfo(Long orderId) {
        return baseMapper.queryRefundInfo(orderId);
    }


    @Override
    public List<OrderOperationLogVO> queryOrderOperationLogInfo(Long orderId) {
        return baseMapper.queryOrderOperationLogInfo(orderId);
    }

    @Override
    public String generateOrderNo(Long shopId) {
        Shop shop = shopService.getById(shopId);
        String platformNo = "NULL";
        if (null != shop) {
            platformNo = shop.getPlatformNo();
        }

        String orderNo = getSerializeNo(platformNo) ;
        return orderNo;
    }

    @Override
    public String getSerializeNo(String code) {
        String timestamp = DateUtils.formatLocalDateTimeToString(LocalDateTime.now(), DateUtils.TIME_PATTERN_MILLISECOND);
        String randomNum = String.valueOf(RandomUtils.nextInt(999));
        if (randomNum.length() == 1) {
            randomNum = "00" + randomNum;
        } else if (randomNum.length() == 2) {
            randomNum = "0" + randomNum;
        }
        String serialNo = code + timestamp + randomNum;
        return serialNo;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
//            System.out.println(getSerializeNo("FH"));
        }

    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        try{
            //删除订单明细表信息
            QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", orderId);
            orderDetailService.remove(queryWrapper);

            //删除订单收发人信息表信息
            QueryWrapper<OrderSendReceive> orderSendReceiveQueryWrapper = new QueryWrapper<>();
            orderSendReceiveQueryWrapper.eq("order_id", orderId);
            orderSendReceiveService.remove(orderSendReceiveQueryWrapper);

            //删除订单表信息
            orderService.removeById(orderId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<T> queryExpressInfo(Long shippingOrderNo) {
        QueryWrapper<ShippingOrder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("shipping_order_no",shippingOrderNo);
        ShippingOrder shippingOrder = shippingOrderService.getOne(queryWrapper);
        if(null != shippingOrder && shippingOrder.getExpressNo() != null){
            //TODO 调用物流接口，用物流单号查询物流信息
        }
        return null;
    }
}
