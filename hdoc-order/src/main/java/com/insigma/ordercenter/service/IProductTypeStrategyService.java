package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.ProductTypeStrategy;
import com.insigma.ordercenter.entity.dto.AddProductTypeStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ProductTypeStrategyVO;

import java.util.List;

/**
 * <p>
 * 策略关联商品分类表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface IProductTypeStrategyService extends IService<ProductTypeStrategy> {
    /**
     * 查询策略关联的商品分类
     *
     *
     * @param req
     * @param strategyId 策略id
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ProductTypeStrategyVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 10:24
     */
    IPage<ProductTypeStrategyVO> listProductTypeStrategy(StrategyParamDTO req, Long strategyId);

    /**
     * 保存按商品分类拆分策略
     *
     * @param req 关联关系
     * @author Pan Juncai
     * @date 2020/7/29 17:31
     */
    void saveProductTypeStrategy(AddProductTypeStrategyDTO req);
}
