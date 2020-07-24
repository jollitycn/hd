package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.BeanProperty;
import com.insigma.ordercenter.entity.Shop;
import com.insigma.ordercenter.entity.ShopWarehouse;
import com.insigma.ordercenter.entity.dto.shop.ShopEdit;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryRequest;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.dto.shop.ShopSetting;
import com.insigma.ordercenter.mapper.ShopMapper;
import com.insigma.ordercenter.mapper.ShopWarehouseMapper;
import com.insigma.ordercenter.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.service.IShopWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Autowired
    private  IShopWarehouseService shopWarehouseService;
    @Override
    public boolean add(ShopEdit data,Long userId) {
        Shop shop = new Shop();
        BeanUtil.copyProperties(data, shop);
        shop.setCreateId(userId);
        shop.setCreateTime(LocalDateTime.now());
        baseMapper.insert(shop);
        shopWarehouseService.update(shop.getShopId(),  data.getWarehouseIds());
        return true;
    }

//    @Override
//    public IPage<ShopQueryResponse> list(ShopQueryRequest request) {
//        Page page = new Page(request.getPageNum(), request.getPageSize());
//        IPage<ShopQueryResponse> result = baseMapper.list(page, request);
//        return result;
//    }

    @Override
    public boolean delete(Long id) {
        return baseMapper.delete(id);
    }

    @Override
    public boolean edit(ShopEdit data) {
        baseMapper.edit(data) ;
        shopWarehouseService.update(data.getShopId(),  data.getWarehouseIds());
        return true;
    }

    @Override
    public boolean disable(Long id) {
        return baseMapper.disable(id);
    }

    @Override
    public boolean enable(Long id) {
        return baseMapper.enable(id);
    }

    @Override
    public boolean setting(ShopSetting data) {
        return baseMapper.setting(data);
    }

    @Override
    public Page page(ShopQueryRequest request) {
        Page page = new Page<>(request.getPageNum(), request.getPageSize());
        List<ShopQueryResponse> list = baseMapper.list(page, request);
        for (ShopQueryResponse response:
        list) {
            response.setWarehouseIds(shopWarehouseService.listByShopId(response.getShopId()));
        }
        page.setRecords(list);
        return page;
    }
}
