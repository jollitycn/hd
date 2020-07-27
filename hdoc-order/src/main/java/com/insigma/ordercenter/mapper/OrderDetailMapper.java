package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单明细 Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetail> getOrderDetail(@Param("orderId")Long orderId);


}
