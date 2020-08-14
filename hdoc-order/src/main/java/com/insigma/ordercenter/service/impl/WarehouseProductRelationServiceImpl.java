package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.dto.DesignatedWarehouseDTO;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import com.insigma.ordercenter.mapper.WarehouseProductRelationMapper;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 仓库商品关联表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@Service
public class WarehouseProductRelationServiceImpl extends ServiceImpl<WarehouseProductRelationMapper, WarehouseProductRelation> implements IWarehouseProductRelationService {

    @Override
    public Integer getTotalStock(Long productId) {

        return baseMapper.getTotalStock(productId);
    }

    @Override
    public List<ProductStockInfoVO> getProductStockInfo(Long productId) {

        return baseMapper.getProductStockInfo(productId);
    }

    /**
     * 删除商品库存
     *
     * @param warehouseProductRelationId
     * @return
     */
    @Override
    public boolean delProductStock(Long warehouseProductRelationId) {
        return this.removeById(warehouseProductRelationId);
    }

    /**
     * 商品指定仓库
     *
     * @param designatedWarehouseDTO
     * @return
     */
    @Override
    public boolean designatedWarehouse(DesignatedWarehouseDTO designatedWarehouseDTO) {

        List<Integer> warehouseIds=designatedWarehouseDTO.getWarehouseIds();
        List<WarehouseProductRelation> insertList=new ArrayList<>();
        for (Integer warehouseId:warehouseIds) {
            WarehouseProductRelation warehouseProductRelation=new WarehouseProductRelation();
            BeanUtil.copyProperties(designatedWarehouseDTO,warehouseProductRelation);
            warehouseProductRelation.setWarehouseId(warehouseId);
            insertList.add(warehouseProductRelation);
        }
        return this.saveBatch(insertList);
    }

    /**
     * 改变优先级
     *
     * @param warehouseProductRelationId
     * @param priority
     * @return
     */
    @Override
    public boolean changePriority(Long warehouseProductRelationId, Integer priority) {


        WarehouseProductRelation warehouseProductRelation=this.getById(warehouseProductRelationId);
        warehouseProductRelation.setPriority(priority);

        return this.updateById(warehouseProductRelation);
    }

    @Override
    public List<WarehouseProductRelation> getWarehouseProductRelation(String warehouseId, String productId) {
        QueryWrapper<WarehouseProductRelation> wrapper = new QueryWrapper<WarehouseProductRelation>();
        wrapper.eq(WarehouseProductRelation.WAREHOUSE_ID,warehouseId);
        wrapper.eq(WarehouseProductRelation.PRODUCT_ID,productId);
        return baseMapper.selectList(wrapper);
    }


}
