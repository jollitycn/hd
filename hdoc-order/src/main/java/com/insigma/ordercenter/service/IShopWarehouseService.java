package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.ShopWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.Warehouse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-23
 */
public interface IShopWarehouseService extends IService<ShopWarehouse> {

    void update(Long shopId, List<String> warehouseIds);

    List<Warehouse> listByShopId(Long shopId);
}
