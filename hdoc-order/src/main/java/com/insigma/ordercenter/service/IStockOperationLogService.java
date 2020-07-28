package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.StockOperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.StockOperationLogPageQuery;

import java.util.List;

/**
 * <p>
 * 库存记录操作日志表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-28
 */
public interface IStockOperationLogService extends IService<StockOperationLog> {

    List listStockLog(String warehouseId, String productId);

   Page<StockOperationLog> page(StockOperationLogPageQuery query);
}
