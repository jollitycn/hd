package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.GiftStrategy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddGiftDTO;
import com.insigma.ordercenter.entity.dto.AddGiftStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.entity.vo.GiftListVO;
import com.insigma.ordercenter.entity.vo.GiftStrategyInfoVO;

import java.util.List;

/**
 * <p>
 * 赠品策略参数配置表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface IGiftStrategyService extends IService<GiftStrategy> {
    /**
     * 获取换货策略列表
     *
     * @param req 条件
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 16:26
     */
    List<ExchangeOrGiftStrategyVO> listGiftStrategy(StrategyParamDTO req);

    /**
     * 新增赠品策略参数
     *
     * @param req 参数信息
     * @author Pan Juncai
     * @date 2020/7/29 19:34
     */
    void addGiftStrategy(AddGiftStrategyDTO req);

    /**
     * 新增贈品
     * @return
     * @param req
     */
    boolean addGift(AddGiftDTO req);

    /**
     * 获取赠品列表
     * @return
     * @param giftStrategyId
     */
    List<GiftListVO> getGiftList(Long giftStrategyId);

    /**
     * 查看赠品参数详情
     * @param giftStrategyId
     * @return
     */
    GiftStrategyInfoVO getGiftStrategyInfo(Long giftStrategyId);

    /**
     * 更新赠品策略参数
     * @param req
     * @return
     */
    Boolean updateGiftStrategy(AddGiftStrategyDTO req);
}
