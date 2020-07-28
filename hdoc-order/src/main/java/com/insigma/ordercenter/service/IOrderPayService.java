package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.OrderPay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.vo.OrderpayVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
public interface IOrderPayService extends IService<OrderPay> {

    List<OrderpayVO> queryOrderPayInfo(Long orderId);

}
