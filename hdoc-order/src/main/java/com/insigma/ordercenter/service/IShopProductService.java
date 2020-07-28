package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.ShopProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电商发货比例表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-28
 */
public interface IShopProductService extends IService<ShopProduct> {

    List<ShopProduct> getByProductId(Long productId);
}
