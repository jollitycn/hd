package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ExchangeStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.entity.vo.ShopListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 换货策略参数配置表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface ExchangeStrategyMapper extends BaseMapper<ExchangeStrategy> {

    /**
     *  查询换货策略
     * @param strategyId
     * @return
     */
    List<ExchangeOrGiftStrategyVO> selectExchangeStrategy(@Param("strategyId") Long strategyId);

    /**
     * 获取换货策略的店铺列表
     * @param id
     * @return
     */
    List<ShopListVO> getShopList(@Param("id")Long id);

    /**
     * 获取换货策略详情
     * @param exchangeStrategyId
     * @return
     */
    ExchangeOrGiftStrategyVO getExchangeStrategy(@Param("exchangeStrategyId")Long exchangeStrategyId);
}
