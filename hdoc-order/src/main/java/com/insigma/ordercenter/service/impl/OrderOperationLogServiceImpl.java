package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.OrderOperationLog;
import com.insigma.ordercenter.entity.vo.OrderOperationLogVO;
import com.insigma.ordercenter.mapper.OrderOperationLogMapper;
import com.insigma.ordercenter.service.IOrderOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单操作日志表 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Service
public class OrderOperationLogServiceImpl extends ServiceImpl<OrderOperationLogMapper, OrderOperationLog> implements IOrderOperationLogService {

}
