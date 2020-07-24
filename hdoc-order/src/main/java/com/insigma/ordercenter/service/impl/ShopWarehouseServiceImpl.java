package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.ShopWarehouse;
import com.insigma.ordercenter.mapper.ShopWarehouseMapper;
import com.insigma.ordercenter.service.IShopWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-23
 */
@Service
public class ShopWarehouseServiceImpl extends ServiceImpl<ShopWarehouseMapper, ShopWarehouse> implements IShopWarehouseService {

    @Autowired
    private ShopWarehouseMapper shopWarehouseMapper;
    @Override

    public void update(Long shopId, List<String> warehouseIds) {
        shopWarehouseMapper.deleteByShopId(shopId);
        for (String warehouseId :
                warehouseIds) {
            ShopWarehouse shopWarehouse = new ShopWarehouse();
            shopWarehouse.setShopId(shopId);
            shopWarehouse.setWarehouseId(Long.parseLong(warehouseId));
            shopWarehouseMapper.insert(shopWarehouse);
        }
    }

    @Override
    public List<String> listByShopId(Long shopId) {
      return  shopWarehouseMapper.listByShopId(shopId);
    }
}
