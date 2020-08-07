package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.ProductTypeStrategy;
import com.insigma.ordercenter.entity.dto.AddProductTypeStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.AccountBlackVO;
import com.insigma.ordercenter.entity.vo.ProductTypeStrategyVO;
import com.insigma.ordercenter.mapper.ProductTypeStrategyMapper;
import com.insigma.ordercenter.service.IProductTypeStrategyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 策略关联商品分类表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Service
public class ProductTypeStrategyServiceImpl extends ServiceImpl<ProductTypeStrategyMapper, ProductTypeStrategy> implements IProductTypeStrategyService {

    @Override
    public IPage<ProductTypeStrategyVO> listProductTypeStrategy(StrategyParamDTO req, Long strategyId) {
        Page<ProductTypeStrategyVO> page = new Page<>(null == req.getPageNum() ? 1 : req.getPageNum(),
                null == req.getPageSize() ? 10 : req.getPageSize());

        return baseMapper.listProductTypeStrategy(page,strategyId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveProductTypeStrategy(AddProductTypeStrategyDTO req) {
        // 先删除这个策略关联的商品分类
        QueryWrapper<ProductTypeStrategy> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq(ProductTypeStrategy.STRATEGY_ID, req.getStrategyId());
        baseMapper.delete(deleteWrapper);

        // 重新建立关系
        List<ProductTypeStrategy> inserts = Lists.newArrayList();
        req.getProductTypeIdList().forEach(productTypeId -> {
            ProductTypeStrategy productTypeStrategy = new ProductTypeStrategy();
            productTypeStrategy.setStrategyId(req.getStrategyId());
            productTypeStrategy.setProductTypeId(productTypeId);
            inserts.add(productTypeStrategy);
        });
        this.saveBatch(inserts);
    }
}
