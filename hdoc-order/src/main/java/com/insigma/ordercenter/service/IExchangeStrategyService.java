package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.ExchangeStrategy;
import com.insigma.ordercenter.entity.dto.AddExchangeStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.dto.UpdateExchangeStrategyDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;

import java.util.List;

/**
 * <p>
 * 换货策略参数配置表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface IExchangeStrategyService extends IService<ExchangeStrategy> {
    /**
     * 获取换货策略列表
     *
     * @param req 条件
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 16:26
     */
    List<ExchangeOrGiftStrategyVO> listExchangeStrategy(StrategyParamDTO req);

    /**
     * 新增换货策略参数配置
     *
     * @param req 参数信息
     * @author Pan Juncai
     * @date 2020/7/30 14:02
     */
    void addExchangeStrategy(AddExchangeStrategyDTO req);

    /**
     * 新增换货策略参数配置
     *
     * @param req 参数信息
     * @author yaoshibin
     * @date 2020/8/03 17:02
     */
    void updateExchangeStrategy(UpdateExchangeStrategyDTO req);

    /**
     * 获取换货策略详情
     * @param exchangeStrategyId
     * @return
     */
    ExchangeOrGiftStrategyVO getExchangeStrategy(Long exchangeStrategyId);
}
