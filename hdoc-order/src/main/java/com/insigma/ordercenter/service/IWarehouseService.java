package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
public interface IWarehouseService extends IService<Warehouse> {

    Result<?> addWarehouse(WarehouseDTO wareHouseDTO, LoginUser redisUser);

    Result<?> updateWarehouse(WarehouseDTO warehouseDTO, LoginUser loginUser);

    Result<?> addProduct(WarehouseProductDTO req);

    Result<?> modifyStock(WarehouseProductRelation whp, LoginUser redisUser);

    Result<?> deleteWarehouse(Serializable warehouseId);

    Result<?> page(IPage<WarehouseVo> page, WarehouseDTO dto);

    Page listProducts(WarehouseProductDTO req);
//
//    Result<?> listProducts(WarehouseProductDTO req);
}
