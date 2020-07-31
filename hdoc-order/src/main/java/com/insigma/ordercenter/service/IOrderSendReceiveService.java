package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.OrderSendReceive;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.UpdateSendReceiveInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收发人信息 服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
public interface IOrderSendReceiveService extends IService<OrderSendReceive> {

    SendReceiveInfoVO getSendReceiveInfo(@Param("orderId") Long orderId);

    Boolean updateSendReceiveInfo(UpdateSendReceiveInfoVO updateSendReceiveInfoVO);

}
