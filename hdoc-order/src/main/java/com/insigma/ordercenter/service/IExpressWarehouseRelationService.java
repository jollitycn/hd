package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.ExpressWarehouseRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 物流仓库关联表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */
public interface IExpressWarehouseRelationService extends IService<ExpressWarehouseRelation> {

    /**
     * 商品指定仓库
     * @param expressCompanyId
     * @param warehouseId
     * @return
     */
    boolean designatedWarehouse(Long expressCompanyId, Long warehouseId);

    /**
     * 改变优先级
     * @param expressWarehouseRelationId
     * @param value
     * @return
     */
    boolean changePriority(Long expressWarehouseRelationId, Integer value);

}
