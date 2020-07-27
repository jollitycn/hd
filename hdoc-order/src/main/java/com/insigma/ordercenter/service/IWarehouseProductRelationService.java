package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.WarehouseProductPageQuery;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;

import java.util.List;

/**
 * <p>
 * 仓库商品关联表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface IWarehouseProductRelationService extends IService<WarehouseProductRelation> {

    Integer getTotalStock(Long productId);

    List<ProductStockInfoVO> getProductStockInfo(Long productId);

}
