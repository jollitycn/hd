package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.Strategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.StrategyOneParamVO;
import com.insigma.ordercenter.mapper.StrategyMapper;
import com.insigma.ordercenter.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 策略表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements IStrategyService {

    @Resource
    private IShopService shopService;

    @Resource
    private IProductTypeStrategyService productTypeStrategyService;

    @Resource
    private IAccountBlacklistStrategyService accountBlacklistStrategyService;

    @Resource
    private IPhoneBlacklistStrategyService phoneBlacklistStrategyService;

    @Resource
    private IRegionBlacklistStrategyService regionBlacklistStrategyService;

    @Resource
    private IExchangeStrategyService exchangeStrategyService;

    @Resource
    private IGiftStrategyService giftStrategyService;

    @Override
    public Object getStrategyParam(StrategyParamDTO req) {
        Long strategyId = req.getStrategyId();
        Object result = new Object();
        Strategy strategy = baseMapper.selectById(strategyId);
        if (null == strategy) {
            return result;
        }
        // 查询各个策略id的参数配置
        switch (strategyId.intValue()) {
            case 1:
                StrategyOneParamVO strategyOneParamVO = new StrategyOneParamVO();
                strategyOneParamVO.setAutoAuditTime(null == strategy.getAutoAuditTime() ? 0 :
                        strategy.getAutoAuditTime());
                strategyOneParamVO.setShopStrategyVO(shopService.listTransformStrategyByStrategyId());
                result = strategyOneParamVO;
                break;
            case 4:
                result = null == strategy.getExpressTime() ? 0 : strategy.getExpressTime();
                break;
            case 7:
                result = productTypeStrategyService.listProductTypeStrategy(7L);
                break;
            case 8:
                result = strategy.getPriority();
                break;
            case 9:
                result = strategy.getPriority();
                break;
            case 10:
                result = strategy.getPriority();
                break;
            case 11:
                result = accountBlacklistStrategyService.listAccountBlackPage(req);
                break;
            case 12:
                result = phoneBlacklistStrategyService.listPhoneBlackPage(req);
                break;
            case 13:
                result = regionBlacklistStrategyService.listRegionBlackPage(req);
                break;
            case 15:
                result = exchangeStrategyService.listExchangeStrategy(req);
                break;
            case 16:
                result = giftStrategyService.listGiftStrategy(req);
                break;
            default:
                break;
        }
        return result;
    }
}
