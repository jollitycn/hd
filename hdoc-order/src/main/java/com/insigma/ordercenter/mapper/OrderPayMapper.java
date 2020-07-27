package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.OrderPay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.OrderpayVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
public interface OrderPayMapper extends BaseMapper<OrderPay> {

    List<OrderpayVO> queryOrderPayInfo(@Param("orderId")Long orderId);

}
