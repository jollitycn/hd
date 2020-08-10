package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.OriginalOrder;
import com.insigma.ordercenter.entity.dto.AddShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
public interface OrderMapper extends BaseMapper<Order> {

//    IPage<OrderListVO> queryOrderListPage(Page<OrderListVO> page,
//                                          @Param("orderId") String orderId,
//                                          @Param("orderStatus") String orderStatus,
//                                          @Param("orderNo") String orderNo,
//                                          @Param("shippingOrderId") String shippingOrderId,
//                                          @Param("reviewTime") String reviewTime,
//                                          @Param("createTime") String createTime,
//                                          @Param("receiveName") String receiveName,
//                                          @Param("mobilePhone") String mobilePhone);

    IPage<OrderListVO> queryOrderListPage(Page<OrderListVO> page,
                                          @Param("orderDTO")OrderDTO orderDTO);

    List<ExpressCompanyVO> queryExpressCompany(@Param("warehouseId")Long warehouseId);


    List<OrderDetailExamineVO> queryOrderDetailList(@Param("orderId") Long orderId);


    List<ShippingOrderCancelVO> cancelOrder(@Param("orderId") Long orderId);


    List<AddShippingOrderDTO> queryShippingOrderListByOrderId(@Param("orderId") Long orderId);


    OrderListVO queryOrderById(@Param("orderId")Long orderId);


    List<OriginalOrderVO> queryOriginalOrderList(@Param("orderId")Long orderId);


    List<RefundInfoVO>  queryRefundInfo(@Param("orderId")Long orderId);


    List<OrderOperationLogVO> queryOrderOperationLogInfo(@Param("orderId")Long orderId);

}
