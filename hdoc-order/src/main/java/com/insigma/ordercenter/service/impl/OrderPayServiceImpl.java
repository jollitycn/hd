package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.OrderPay;
import com.insigma.ordercenter.entity.vo.OrderpayVO;
import com.insigma.ordercenter.mapper.OrderPayMapper;
import com.insigma.ordercenter.service.IOrderPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public List<OrderpayVO> queryOrderPayInfo(Long orderId) {
        return baseMapper.queryOrderPayInfo(orderId);
    }
}
