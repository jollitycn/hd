package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.AddShippingOrderResultDTO;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.UpdateOrderStatuDTO;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.mapper.OrderMapper;
import com.insigma.ordercenter.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private ISendReceiveInfoService sendReceiveInfoService;

    @Resource
    private IShippingOrderService shippingOrderService;

    @Resource
    private IDetailShippingOrderRelationService detailShippingOrderRelationService;

    @Override
    public IPage<OrderListVO> queryOrderListPage(Page<OrderListVO> page, OrderDTO orderDTO) {
        return baseMapper.queryOrderListPage(page, orderDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addOrder(SendReceiveInfoVO sendReceiveInfoVO) {
        try {
            //新增订单表信息
            Order order = new Order();
            // TODO 按规则定义订单号 order.setOrderNo();
            order.setCreateTime(LocalDateTime.now());
            order.setConsumerName(sendReceiveInfoVO.getConsumerName());
            order.setShopId(sendReceiveInfoVO.getShopId());
            order.setIsCombined(Constant.SYS_ZERO);
            order.setIsHandOrder(Constant.SYS_ONE);
            order.setMobilePhone(sendReceiveInfoVO.getMobilePhone());
            order.setOrderStatus(Constant.SYS_ZERO);
            orderService.save(order);

            //新增订单收发件人信息
            SendReceiveInfo sendReceiveInfo = new SendReceiveInfo();
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
            sendReceiveInfoService.save(sendReceiveInfo);

            //新增订单明细信息
            orderDetailService.saveBatch(sendReceiveInfoVO.getOrderDetails());
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
        return orderMapper.queryOrderDetailList(orderId);
    }

    @Override
    public List<ExpressCompanyVO> queryExpressCompany(Long warehouseId) {
        return orderMapper.queryExpressCompany(warehouseId);
    }

    @Override
    public Result addShippingOrder(AddShippingOrderResultDTO addShippingOrderResultDTO) {
        if (addShippingOrderResultDTO.getAddShippingOrderDTOS().size() == 0) {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
        addShippingOrderResultDTO.getAddShippingOrderDTOS().forEach(AddShippingOrderDTO -> {
            ShippingOrder shippingOrder = new ShippingOrder();
            shippingOrder.setWarehouseId(AddShippingOrderDTO.getWarehouseId());
            shippingOrder.setExpressCompanyId(AddShippingOrderDTO.getExpressCompanyId());
            shippingOrder.setCreateId(AddShippingOrderDTO.getCreateId());
            shippingOrder.setCreateTime(LocalDateTime.now());
            shippingOrder.setIsDeleted(Constant.SYS_ZERO);
            shippingOrder.setStatus(Constant.SYS_ZERO);
            shippingOrder.setReceiveName(AddShippingOrderDTO.getReceiveName());
            shippingOrder.setMobilePhone(AddShippingOrderDTO.getMobilePhone());
            shippingOrder.setAddress(AddShippingOrderDTO.getAddress());
            shippingOrder.setIsCombined(Constant.SYS_ZERO);
            shippingOrder.setShippingOrderNo(AddShippingOrderDTO.getShippingOrderNo());
            shippingOrderService.save(shippingOrder);
            DetailShippingOrderRelation detailShippingOrderRelation = new DetailShippingOrderRelation();
            detailShippingOrderRelation.setOrderDetailId(AddShippingOrderDTO.getOrderDetailId());
            detailShippingOrderRelation.setShippingOrderId(shippingOrder.getShippingOrderId());
            detailShippingOrderRelationService.save(detailShippingOrderRelation);
        });
        return Result.success();
    }

    @Override
    public Result cancelOrder(Long orderId) {
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        AtomicReference<Integer> count = new AtomicReference<>(0);
        List<ShippingOrderCancelVO> shippingOrderCancelVOS = orderMapper.cancelOrder(orderId);
        shippingOrderCancelVOS.forEach(ShippingOrderCancelVO -> {
            if (null != ShippingOrderCancelVO && ShippingOrderCancelVO.getStatus() != null) {
                //发货单状态，3：冻结；5：拒收；6：异常
                if (ShippingOrderCancelVO.getStatus() == 3 || ShippingOrderCancelVO.getStatus() == 5 || ShippingOrderCancelVO.getStatus() == 6) {
                    UpdateOrderStatuDTO updateOrderStatuDTO = new UpdateOrderStatuDTO();
                    updateOrderStatuDTO.setOrderId(orderId);
                    updateOrderStatuDTO.setOrderStatus(Long.parseLong("4"));
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
            updateOrderStatuDTO.setOrderStatus(Long.parseLong("5"));
            orderService.updateOrderStatu(updateOrderStatuDTO);
        }
        return Result.success();
    }

    @Override
    public OrderListVO queryOrderById(Long orderId) {
        return orderService.queryOrderById(orderId);
    }

    @Override
    public List<OriginalOrderVO> queryOriginalOrderList(Long orderId) {
        return orderService.queryOriginalOrderList(orderId);
    }
}