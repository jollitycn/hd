package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.shop.ShopEdit;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryRequest;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.dto.shop.ShopSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */
public interface ShopMapper extends BaseMapper<Shop> {

    List<ShopQueryResponse> list( @Param("page")  Page page,   @Param("request")  ShopQueryRequest request);

    boolean delete(Long id);

    boolean edit(ShopEdit data);

    boolean disable(Long id);

    boolean enable(Long id);

    boolean setting(ShopSetting data);
}
