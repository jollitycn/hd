package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.*;
import com.insigma.ordercenter.entity.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

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

    Result addOrder(SendReceiveInfoVO sendReceiveInfoVO,LoginUser loginUser);

    Boolean updateOrderStatu(UpdateOrderStatuDTO updateOrderStatuDTO,LoginUser loginUser);

    List<OrderDetailExamineVO> queryOrderDetailList(Long orderId);

    List<ExpressCompanyVO> queryExpressCompany(Long warehouseId);

    Result addShippingOrder(AddShippingOrderResultDTO addShippingOrderResultDTO, LoginUser loginUser);

    Result cancelOrder(Long orderId,LoginUser loginUser);

    OrderListVO queryOrderById(Long orderId);

    List<OriginalOrderVO> queryOriginalOrderList(Long orderId);

    String generateOrderNo(Long shopId);

    List<RefundInfoVO>  queryRefundInfo(Long orderId);

    List<OrderOperationLogVO> queryOrderOperationLogInfo(Long orderId);

    String getSerializeNo(String code);

    Boolean deleteOrder(Long orderId);

    List<?> queryExpressInfo(Long shippingOrderNo);

    Boolean shippingOrderStatuChange(UpdateShippingOrderStatuDTO updateOrderStatuDTO,LoginUser loginUser);
}
