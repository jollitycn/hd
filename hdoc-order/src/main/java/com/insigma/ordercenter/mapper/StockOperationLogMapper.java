package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.StockOperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.StockOperationLogPage;
import com.insigma.ordercenter.entity.dto.StockOperationLogPageQuery;

import java.util.List;

/**
 * <p>
 * 库存记录操作日志表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-28
 */
public interface StockOperationLogMapper extends BaseMapper<StockOperationLog> {

    List listStockLog(String warehouseId, String productId);

    List<StockOperationLogPage> page(Page page, StockOperationLogPageQuery request);
}
