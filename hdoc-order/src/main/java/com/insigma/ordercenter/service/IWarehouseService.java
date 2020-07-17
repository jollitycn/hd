package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;

import java.io.Serializable;

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
}
