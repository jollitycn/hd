package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.StockOperationLogPageQuery;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.service.IStockOperationLogService;
import com.insigma.ordercenter.service.IWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 库存记录操作日志表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/sol")
@Api(tags = {"仓库库存日志接口"})
public class StockOperationLogController  extends BaseController {

    @Autowired
    private IStockOperationLogService stockOperationLogService;

    @PostMapping("/{warehouseId}/{productId}")
    @ApiOperation("历史信息")
    public Result<?> page(@RequestBody StockOperationLogPageQuery query) {
        return Result.success(stockOperationLogService.page(query));
    }
}
