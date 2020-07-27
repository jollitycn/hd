package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ShopWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.Warehouse;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-23
 */
public interface ShopWarehouseMapper extends BaseMapper<ShopWarehouse> {

    void deleteByShopId(Long shopId);

    List<Warehouse> listByShopId(Long shopId);
}
