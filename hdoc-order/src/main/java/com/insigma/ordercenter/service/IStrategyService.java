package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.Strategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;

/**
 * <p>
 * 策略表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
public interface IStrategyService extends IService<Strategy> {
    /**
     * 根据策略id查询其配置参数
     *
     * @param strategyId 策略id
     * @return java.lang.Object
     * @author Pan Juncai
     * @date 2020/7/28 17:32
     */
    Object getStrategyParam(StrategyParamDTO req);
}
