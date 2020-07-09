package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.WarehouseVo;
import com.insigma.ordercenter.service.IWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public Result<?> addWarehouse(@RequestBody WarehouseVo wareHouseVo) {
        if (StringUtils.isBlank(wareHouseVo.getWarehouseName())) {
            return Result.error(CodeMsg.LACK_OF_PARAM);
        }
        return warehouseService.addWarehouse(wareHouseVo,redisUser());
    }

}