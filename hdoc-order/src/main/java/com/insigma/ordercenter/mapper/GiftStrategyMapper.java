package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.GiftStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.GiftListVO;
import com.insigma.ordercenter.entity.vo.ShopListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 赠品策略参数配置表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface GiftStrategyMapper extends BaseMapper<GiftStrategy> {

    /**
     * 获取赠品列表
     * @return
     * @param giftStrategyId
     */
    List<GiftListVO> getGiftList(@Param("giftStrategyId") Long giftStrategyId);

    /**
     * 查找店铺信息
     * @param giftStrategyId
     * @return
     */
    List<ShopListVO> getShop(@Param("giftStrategyId") Long giftStrategyId);

}
