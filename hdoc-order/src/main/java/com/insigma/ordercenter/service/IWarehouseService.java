package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.WarehouseVo;
import com.insigma.ordercenter.entity.Warehouse;
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

    Result<?> addWarehouse(WarehouseVo wareHouseVo, LoginUser redisUser);

    Result<?> updateWarehouse(WarehouseVo warehouseVo, LoginUser loginUser);
}
