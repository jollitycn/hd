package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.WarehouseProductReq;
import com.insigma.ordercenter.entity.WarehouseReq;
import com.insigma.ordercenter.service.IWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/warehouse")
@Api(tags = {"仓库接口"})
public class WarehouseController extends BaseController {

    @Autowired
    private IWarehouseService warehouseService;

    @PutMapping
    @ApiOperation("新增仓库")
    public Result<?> addWarehouse(@RequestBody WarehouseReq wareHouseReq) {
        if (StringUtils.isBlank(wareHouseReq.getWarehouseName())) {
            return Result.error(CodeMsg.LACK_OF_PARAM);
        }
        return warehouseService.addWarehouse(wareHouseReq,redisUser());
    }

    @PostMapping
    @ApiOperation("修改仓库")
    public Result<?> modifyWarehouse(@RequestBody WarehouseReq warehouseReq) {
        if (null == warehouseReq.getWarehouseId()) {
            return Result.error(CodeMsg.LACK_OF_WHID);
        }
        return warehouseService.updateWarehouse(warehouseReq,redisUser());
    }

    @PostMapping("/product")
    @ApiOperation("仓库添加商品库存")
    public Result<?> addProduct(@RequestBody WarehouseProductReq req) {
        return warehouseService.addProduct(req);
    }

    @PostMapping("/stock")
    @ApiOperation("修改库存")
    public Result<?> stock(@RequestBody WarehouseProductRelation whp) {
        return warehouseService.modifyStock(whp,redisUser());
    }

}