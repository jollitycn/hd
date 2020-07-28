package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.vo.AddOrderDetailVO;
import com.insigma.ordercenter.entity.vo.OrderDetailExamineVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单明细 服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
public interface IOrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> getOrderDetail(Long orderId);

    Boolean addOrderDerail(AddOrderDetailVO addOrderDetailVO);

}
