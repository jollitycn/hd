package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import com.insigma.ordercenter.mapper.WarehouseProductRelationMapper;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库商品关联表 服务实现类
 * </p>
 *
 * @author LiuHao
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
}
