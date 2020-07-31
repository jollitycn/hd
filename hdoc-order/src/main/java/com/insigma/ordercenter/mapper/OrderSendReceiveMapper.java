package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.OrderSendReceive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收发人信息 Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
public interface OrderSendReceiveMapper extends BaseMapper<OrderSendReceive> {

    SendReceiveInfoVO getSendReceiveInfo(@Param("orderId") Long orderId);

}
