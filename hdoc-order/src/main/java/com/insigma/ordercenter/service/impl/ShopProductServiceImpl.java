package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.entity.ShopProduct;
import com.insigma.ordercenter.mapper.ShopProductMapper;
import com.insigma.ordercenter.service.IShopProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电商发货比例表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-28
 */
@Service
public class ShopProductServiceImpl extends ServiceImpl<ShopProductMapper, ShopProduct> implements IShopProductService {

    @Override
    public List<ShopProduct> getByProductId(Long productId) {
//        QueryWrapper<ShopProduct> wrapper = new QueryWrapper<ShopProduct>();
//        wrapper.eq(ShopProduct.PRODUCT_ID,productId);
        List<ShopProduct> result = baseMapper.selectList(productId);
        return result;
    }
}
