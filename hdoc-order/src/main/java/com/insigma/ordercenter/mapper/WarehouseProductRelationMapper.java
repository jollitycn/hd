package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 仓库商品关联表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface WarehouseProductRelationMapper extends BaseMapper<WarehouseProductRelation> {

    int getTotalStock(@Param("productId") Long productId);

    List<ProductStockInfoVO> getProductStockInfo(@Param("productId") Long productId);
}
