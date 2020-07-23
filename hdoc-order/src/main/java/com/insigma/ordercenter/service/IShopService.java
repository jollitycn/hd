package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.ExpressCompanyAddDTO;
import com.insigma.ordercenter.entity.dto.shop.ShopEdit;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryRequest;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.dto.shop.ShopSetting;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */
public interface IShopService extends IService<Shop> {

    boolean add(ShopEdit shop,Long userId);

//    IPage<ShopQueryResponse> list(ShopQueryRequest request);

    boolean delete(Long id);

    boolean edit(ShopEdit data);

    boolean disable(Long id);

    boolean enable(Long id);

    boolean setting(ShopSetting data);

    Page page(ShopQueryRequest request);
}