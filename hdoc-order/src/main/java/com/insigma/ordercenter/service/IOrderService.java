package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddShippingOrderResultDTO;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.dto.UpdateOrderStatuDTO;
import com.insigma.ordercenter.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
public interface IOrderService extends IService<Order> {

    IPage<OrderListVO> queryOrderListPage(Page<OrderListVO> page, OrderDTO orderDTO);

    Result addOrder(SendReceiveInfoVO sendReceiveInfoVO);

    Boolean updateOrderStatu(UpdateOrderStatuDTO updateOrderStatuDTO);

    List<OrderDetailExamineVO> queryOrderDetailList(Long orderId);

    List<ExpressCompanyVO> queryExpressCompany(Long warehouseId);

    Result addShippingOrder(AddShippingOrderResultDTO addShippingOrderResultDTO);

    Result cancelOrder(Long orderId);

    OrderListVO queryOrderById(Long orderId);

    List<OriginalOrderVO> queryOriginalOrderList(Long orderId);

    String generateOrderNo(Long shopId);
    List<RefundInfoVO>  queryRefundInfo(Long orderId);

    List<OrderOperationLogVO> queryOrderOperationLogInfo(Long orderId);

}
