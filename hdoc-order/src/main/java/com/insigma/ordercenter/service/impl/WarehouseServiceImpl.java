package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import com.insigma.ordercenter.mapper.WarehouseMapper;
import com.insigma.ordercenter.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    private IWarehouseTypeService typeService;

    @Autowired
    private IWarehouseRegionService warehouseRegionService;

    @Autowired
    private IWarehouseProductRelationService productRelationService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IStockOperationLogService stockLogService;

    @Override
    @Transactional
    public Result<?> addWarehouse(WarehouseDTO wareHouseDTO, LoginUser redisUser) {
        //新增仓库
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(wareHouseDTO,warehouse);
        warehouse.setCreateId(redisUser.getUserId());
        warehouse.setCreateTime(LocalDateTime.now());
        boolean res = this.save(warehouse);
        //新增仓库地区
        Integer[] regionIds = wareHouseDTO.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            warehouseRegionService.save(region);
        }
        Integer[] productTypes = wareHouseDTO.getProductTypes();
        for (Integer productType : productTypes) {
            WarehouseType warehouseType = new WarehouseType();
            warehouseType.setProductType(productType);
            warehouseType.setWarehouseId(wareHouseDTO.getWarehouseId());
            typeService.save(warehouseType);
        }


        if (res) {
            return Result.success(CodeMsg.SUCCESS);
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> updateWarehouse(WarehouseDTO warehouseDTO, LoginUser loginUser) {
        //修改仓库信息
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseDTO,warehouse);
        warehouse.setModifyId(loginUser.getUserId());
        warehouse.setModifyTime(LocalDateTime.now());
        this.updateById(warehouse);
        //先删除仓库地区和仓库管理员
        QueryWrapper<WarehouseType> wrapper1 = new QueryWrapper<>();
        wrapper1.eq(WarehouseManager.WAREHOUSE_ID,warehouse.getWarehouseId());
        typeService.remove(wrapper1);
        QueryWrapper<WarehouseRegion> wrapper2 = new QueryWrapper<>();
        wrapper2.eq(WarehouseRegion.WAREHOUSE_ID,warehouse.getWarehouseId());
        warehouseRegionService.remove(wrapper2);
        //重新添加仓库地区和管理员
        Integer[] regionIds = warehouseDTO.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            warehouseRegionService.save(region);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @Override
    public Result<?> addProduct(WarehouseProductDTO req) {
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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> deleteWarehouse(Serializable warehouseId) {
        //删除仓库相关负责人
        UpdateWrapper<WarehouseType> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq(WarehouseManager.WAREHOUSE_ID,warehouseId);
        typeService.remove(wrapper1);
        //删除仓库地区
        UpdateWrapper<WarehouseRegion> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq(WarehouseRegion.WAREHOUSE_ID,warehouseId);
        warehouseRegionService.remove(wrapper2);
        //删除仓库
        this.removeById(warehouseId);
        return Result.success();
    }

    @Override
    public Result<?> page(IPage<WarehouseVo> page, WarehouseDTO map) {
        List<WarehouseVo> page1 = this.baseMapper.page(page, map);
        page.setRecords(page1);
        return Result.success(page);
    }
}