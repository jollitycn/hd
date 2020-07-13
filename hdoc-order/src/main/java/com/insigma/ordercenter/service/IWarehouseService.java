package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
public interface IWarehouseService extends IService<Warehouse> {

    Result<?> addWarehouse(WarehouseReq wareHouseReq, LoginUser redisUser);

    Result<?> updateWarehouse(WarehouseReq warehouseReq, LoginUser loginUser);

    Result<?> addProduct(WarehouseProductReq req);

    Result<?> modifyStock(WarehouseProductRelation whp, LoginUser redisUser);
}
