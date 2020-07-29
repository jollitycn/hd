package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.GiftStrategy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddGiftStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;

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
}
