package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.ExpressWarehouseRelation;
import com.insigma.ordercenter.mapper.ExpressWarehouseRelationMapper;
import com.insigma.ordercenter.service.IExpressWarehouseRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流仓库关联表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */
@Service
public class ExpressWarehouseRelationServiceImpl extends ServiceImpl<ExpressWarehouseRelationMapper, ExpressWarehouseRelation> implements IExpressWarehouseRelationService {

    /**
     * 商品指定仓库
     *
     * @param expressCompanyId
     * @param warehouseId
     * @return
     */
    @Override
    public boolean designatedWarehouse(Long expressCompanyId, Long warehouseId) {

        ExpressWarehouseRelation expressWarehouseRelation=new ExpressWarehouseRelation();
        expressWarehouseRelation.setExpressCompanyId(expressCompanyId);
        expressWarehouseRelation.setWarehouseId(warehouseId);
        expressWarehouseRelation.setPriority(10);

        return this.save(expressWarehouseRelation);
    }

    /**
     * 改变优先级
     *
     * @param expressWarehouseRelationId
     * @param value
     * @return
     */
    @Override
    public boolean changePriority(Long expressWarehouseRelationId, Integer value) {

        ExpressWarehouseRelation expressWarehouseRelation=this.getById(expressWarehouseRelationId);
        expressWarehouseRelation.setPriority(value);

        return this.updateById(expressWarehouseRelation);
    }


}
