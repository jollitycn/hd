package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.OrderPay;
import com.insigma.ordercenter.entity.vo.OrderPayAllVO;
import com.insigma.ordercenter.entity.vo.OrderpayVO;
import com.insigma.ordercenter.mapper.OrderPayMapper;
import com.insigma.ordercenter.service.IOrderPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Service
public class OrderPayServiceImpl extends ServiceImpl<OrderPayMapper, OrderPay> implements IOrderPayService {

    @Override
    public OrderPayAllVO queryOrderPayInfo(Long orderId) {
        OrderPayAllVO orderPayAllVO=new OrderPayAllVO();
        //计算总实付款
        double disbursements = 0;
        List<OrderpayVO> orderpayVOS = baseMapper.queryOrderPayInfo(orderId);

        for (OrderpayVO orderpayVO: orderpayVOS) {
            orderPayAllVO.setPayTime(orderpayVO.getPayDatetime());
            disbursements = disbursements +Double.parseDouble(orderpayVO.getPayMoney().toString());
        }
        orderPayAllVO.setDisbursements(new BigDecimal(String.valueOf(disbursements)));
        orderPayAllVO.setOrderPays(orderpayVOS);
        return orderPayAllVO;
    }
}
