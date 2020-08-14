package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.dto.DesignatedWarehouseDTO;
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

    List<WarehouseProductRelation>  getWarehouseProductRelation(String warehouseId, String productId);
    /**
     * 删除商品库存
     * @param warehouseProductRelationId
     * @return
     */
    boolean delProductStock(Long warehouseProductRelationId);


    /**
     * 商品指定仓库
     * @param designatedWarehouseDTO
     * @return
     */
    boolean designatedWarehouse(DesignatedWarehouseDTO designatedWarehouseDTO);

    /**
     * 改变优先级
     * @param warehouseProductRelationId
     * @param priority
     * @return
     */
    boolean changePriority(Long warehouseProductRelationId, Integer priority);
}
