package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.IWarehouseManagerService;
import com.insigma.ordercenter.service.IWarehouseRegionService;
import com.insigma.ordercenter.service.IWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IWarehouseManagerService managerService;

    @Autowired
    private IWarehouseRegionService warehouseRegionService;

    @Autowired
    private RegionService regionService;

    @PutMapping
    @ApiOperation("新增仓库")
    public Result<?> addWarehouse(@RequestBody WarehouseDTO wareHouseDTO) {
        if (StringUtils.isBlank(wareHouseDTO.getWarehouseName())) {
            return Result.error(CodeMsg.LACK_OF_PARAM);
        }
        return warehouseService.addWarehouse(wareHouseDTO,redisUser());
    }

    @PostMapping
    @ApiOperation("修改仓库")
    public Result<?> modifyWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        if (null == warehouseDTO.getWarehouseId()) {
            return Result.error(CodeMsg.LACK_OF_WHID);
        }
        return warehouseService.updateWarehouse(warehouseDTO,redisUser());
    }

    @GetMapping
    @ApiOperation("仓库列表")
    public Result<?> list(Warehouse warehouse, @ApiParam("页面大小")@RequestParam(required = false) Integer pageSize ,
                          @ApiParam("当前页码") @RequestParam(required = false) Integer pageNum) {

        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(warehouse.getWarehouseNo())) {
            wrapper.eq(Warehouse.WAREHOUSE_NO,warehouse.getWarehouseNo());
        }
        if (StringUtils.isNotBlank(warehouse.getWarehouseName())) {
            wrapper.like(Warehouse.WAREHOUSE_NAME,warehouse.getWarehouseName());
        }
        IPage<Warehouse> page = new Page<>(pageNum,pageSize);
        return Result.success(warehouseService.page(page,wrapper));
    }

    @DeleteMapping("/{warehouseId}")
    public Result<?> deleteWarehouse(@PathVariable Serializable warehouseId) {
        return warehouseService.deleteWarehouse(warehouseId);
    }

    @GetMapping("/{warehouseId}")
    @ApiOperation(value = "仓库详情",response = WarehouseVo.class)
    public Result warehouseDetail(@PathVariable Integer warehouseId) {
        Warehouse warehouse = warehouseService.getById(warehouseId);
        WarehouseVo warehouseVo = new WarehouseVo();
        if (null != warehouse) {
            BeanUtils.copyProperties(warehouse,warehouseVo);
            List<WarehouseManager> managerList = managerService.list(Wrappers.<WarehouseManager>lambdaQuery().eq(WarehouseManager::getWarehouseId, warehouse.getWarehouseId()));
            warehouseVo.setManagers(managerList);
            List<SysRegion> regions = new ArrayList<>();
            List<WarehouseRegion> list = warehouseRegionService.list(Wrappers.<WarehouseRegion>lambdaQuery().eq(WarehouseRegion::getWarehouseId, warehouseVo.getWarehouseId()));
            list.forEach(warehouseRegion -> {
                SysRegion region = regionService.detail(warehouseRegion.getRegionId());
                regions.add(region);
            });
            warehouseVo.setRegions(regions);
            return Result.success(warehouseVo);
        }
        return Result.success();
    }

    @PostMapping("/product")
    @ApiOperation("仓库添加商品库存")
    public Result<?> addProduct(@RequestBody WarehouseProductDTO req) {
        return warehouseService.addProduct(req);
    }

    @PostMapping("/stock")
    @ApiOperation("修改库存")
    public Result<?> stock(@RequestBody WarehouseProductRelation whp) {
        return warehouseService.modifyStock(whp,redisUser());
    }



}