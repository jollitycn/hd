package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.StockOperationLog;
import com.insigma.ordercenter.entity.dto.StockOperationLogPage;
import com.insigma.ordercenter.entity.dto.StockOperationLogPageQuery;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.mapper.StockOperationLogMapper;
import com.insigma.ordercenter.service.IStockOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 库存记录操作日志表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-28
 */
@Service
public class StockOperationLogServiceImpl extends ServiceImpl<StockOperationLogMapper, StockOperationLog> implements IStockOperationLogService {

    @Override
    public List listStockLog(String warehouseId, String productId) {
        return this.baseMapper.listStockLog(warehouseId,productId);
    }

    @Override
    public Page<StockOperationLog> page(StockOperationLogPageQuery request) {
        Page page = new Page<>(request.getPageNum(), request.getPageSize());
        List<StockOperationLogPage> list = baseMapper.page(page, request);
        page.setRecords(list);
        return page;
    }
}
