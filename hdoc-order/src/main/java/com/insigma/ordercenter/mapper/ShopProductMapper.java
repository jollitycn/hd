package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ShopProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ShopProductVO;

import java.util.List;

/**
 * <p>
 * 电商发货比例表 Mapper 接口
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-28
 */
public interface ShopProductMapper extends BaseMapper<ShopProduct> {

    List<ShopProductVO> getProductRatio(Long productId);
}
