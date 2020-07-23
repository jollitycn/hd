package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ShopWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-23
 */
public interface ShopWarehouseMapper extends BaseMapper<ShopWarehouse> {

    void deleteByShopId(Long shopId);

    List<String> listByShopId(Long shopId);
}
