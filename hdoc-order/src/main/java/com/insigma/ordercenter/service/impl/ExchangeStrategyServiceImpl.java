package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.ExchangeStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.mapper.ExchangeStrategyMapper;
import com.insigma.ordercenter.service.IExchangeStrategyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 换货策略参数配置表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Service
public class ExchangeStrategyServiceImpl extends ServiceImpl<ExchangeStrategyMapper, ExchangeStrategy> implements IExchangeStrategyService {

    @Override
    public List<ExchangeOrGiftStrategyVO> listExchangeStrategy(StrategyParamDTO req) {
        List<ExchangeOrGiftStrategyVO> list = Lists.newArrayList();
        QueryWrapper<ExchangeStrategy> exchangeStrategyWrapper = new QueryWrapper<>();
        exchangeStrategyWrapper.eq(ExchangeStrategy.STRATEGY_ID, req.getStrategyId());
        // 查询换货策略
        List<ExchangeStrategy> strategyList = baseMapper.selectList(exchangeStrategyWrapper);
        // 渲染返回数据
        strategyList.forEach(exchangeStrategy -> {
            ExchangeOrGiftStrategyVO exchangeOrGiftStrategyVO = new ExchangeOrGiftStrategyVO();
            BeanUtil.copyProperties(exchangeStrategy, exchangeOrGiftStrategyVO);
            exchangeOrGiftStrategyVO.setId(exchangeStrategy.getExchangeStrategyId());
            exchangeOrGiftStrategyVO.setTheme(exchangeStrategy.getExchangeTheme());
            list.add(exchangeOrGiftStrategyVO);
        });
        return list;
    }
}
