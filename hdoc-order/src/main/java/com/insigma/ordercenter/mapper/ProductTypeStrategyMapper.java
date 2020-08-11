package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.ProductTypeStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ProductTypeStrategyVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 策略关联商品分类表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface ProductTypeStrategyMapper extends BaseMapper<ProductTypeStrategy> {
    /**
     * 查询策略关联的商品分类
     *
     *
     * @param page
     * @param strategyId 策略id
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ProductTypeStrategyVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 10:24
     */
    IPage<ProductTypeStrategyVO> listProductTypeStrategy(Page<ProductTypeStrategyVO> page,
                                                         @Param("strategyId") Long strategyId);
}
