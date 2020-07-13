package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.mapper.WarehouseMapper;
import com.insigma.ordercenter.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    private IWarehouseManagerService managerService;

    @Autowired
    private IWarehouseRegionService regionService;

    @Autowired
    private IWarehouseProductRelationService productRelationService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IStockOperationLogService stockLogService;

    @Override
    @Transactional
    public Result<?> addWarehouse(WarehouseReq wareHouseReq, LoginUser redisUser) {
        //新增仓库
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(wareHouseReq,warehouse);
        warehouse.setCreateId(redisUser.getUserId());
        warehouse.setCreateTime(LocalDateTime.now());
        boolean res = this.save(warehouse);
        //新增仓库地区
        Integer[] regionIds = wareHouseReq.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            regionService.save(region);
        }
        //新增仓库管理员
        WarehouseManager[] managers = wareHouseReq.getManagers();

        for (WarehouseManager manager : managers) {
            WarehouseManager wm = new WarehouseManager();
            wm.setUserId(manager.getUserId());
            wm.setUserName(manager.getUserName());
            wm.setWarehouseId(warehouse.getWarehouseId());
            managerService.save(wm);
        }

        if (res) {
            return Result.success(CodeMsg.SUCCESS);
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> updateWarehouse(WarehouseReq warehouseReq, LoginUser loginUser) {
        //修改仓库信息
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseReq,warehouse);
        warehouse.setModifyId(loginUser.getUserId());
        warehouse.setModifyTime(LocalDateTime.now());
        this.updateById(warehouse);
        //先删除仓库地区和仓库管理员
        QueryWrapper<WarehouseManager> wrapper1 = new QueryWrapper<>();
        wrapper1.eq(WarehouseManager.WAREHOUSE_ID,warehouse.getWarehouseId());
        managerService.remove(wrapper1);
        QueryWrapper<WarehouseRegion> wrapper2 = new QueryWrapper<>();
        wrapper2.eq(WarehouseRegion.WAREHOUSE_ID,warehouse.getWarehouseId());
        regionService.remove(wrapper2);
        //重新添加仓库地区和管理员
        Integer[] regionIds = warehouseReq.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            regionService.save(region);
        }

        WarehouseManager[] managers = warehouseReq.getManagers();

        for (WarehouseManager manager : managers) {
            WarehouseManager wm = new WarehouseManager();
            wm.setUserId(manager.getUserId());
            wm.setUserName(manager.getUserName());
            wm.setWarehouseId(warehouse.getWarehouseId());
            managerService.save(wm);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @Override
    public Result<?> addProduct(WarehouseProductReq req) {
        //遍历商品编号 ，新增仓库商品记录
        for (Long productId : req.getProductIds()) {
            WarehouseProductRelation whp = new WarehouseProductRelation();
            whp.setWarehouseId(req.getWarehouseId());
            whp.setProductId(productId);
            Product product = productService.getById(productId);
            if (null == product) {
                return Result.error(CodeMsg.PRODUCT_NOT_EXIST);
            }
            whp.setQuantity(0);
            whp.setProductSku(product.getProductSku());
            productRelationService.save(whp);
        }
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> modifyStock(WarehouseProductRelation whp, LoginUser redisUser) {
        StockOperationLog log = new StockOperationLog();
        //根据主键查询旧库存数量
        WarehouseProductRelation productStock = productRelationService.getById(whp.getWarehouseProductRelationId());
        if (null == productStock) {
            return Result.error(CodeMsg.STOKE_NOT_EXIST);
        }
        log.setOriginQuantity(productStock.getQuantity());
        //改变量等于 新库存减旧库存
        Integer changeQuantity = whp.getQuantity() - productStock.getQuantity() ;
        //更新库存
        boolean b = productRelationService.updateById(whp);
        //新增库存变更记录
        log.setChangeQuantity(changeQuantity);
        log.setCreateId(redisUser.getUserId());
        log.setCreateTime(LocalDateTime.now());
        log.setDestinationQuantity(whp.getQuantity());
        log.setWarehouseProductRelationId(whp.getWarehouseProductRelationId());
        stockLogService.save(log);
        return Result.success(CodeMsg.SUCCESS);
    }
}