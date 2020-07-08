package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.mapper.ShippingOrderMapper;
import com.insigma.ordercenter.service.IShippingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 发货单表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class ShippingOrderServiceImpl extends ServiceImpl<ShippingOrderMapper, ShippingOrder> implements IShippingOrderService {

}
