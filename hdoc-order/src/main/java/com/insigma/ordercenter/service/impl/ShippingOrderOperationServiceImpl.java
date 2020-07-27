package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.ShippingOrderOperation;
import com.insigma.ordercenter.mapper.ShippingOrderOperationMapper;
import com.insigma.ordercenter.service.IShippingOrderOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  发货单操作日志表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-23
 */
@Service
public class ShippingOrderOperationServiceImpl extends ServiceImpl<ShippingOrderOperationMapper, ShippingOrderOperation> implements IShippingOrderOperationService {

}
