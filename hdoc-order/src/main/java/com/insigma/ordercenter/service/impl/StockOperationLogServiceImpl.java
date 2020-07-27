package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.StockOperationLog;
import com.insigma.ordercenter.mapper.StockOperationLogMapper;
import com.insigma.ordercenter.service.IStockOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存记录操作日志表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@Service
public class StockOperationLogServiceImpl extends ServiceImpl<StockOperationLogMapper, StockOperationLog> implements IStockOperationLogService {

}
