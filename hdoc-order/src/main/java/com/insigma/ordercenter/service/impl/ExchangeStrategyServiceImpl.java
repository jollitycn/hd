package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.ExchangeStrategy;
import com.insigma.ordercenter.entity.ParamShop;
import com.insigma.ordercenter.entity.dto.AddExchangeStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.dto.UpdateExchangeStrategyDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.entity.vo.ShopListVO;
import com.insigma.ordercenter.mapper.ExchangeStrategyMapper;
import com.insigma.ordercenter.service.IExchangeStrategyService;
import com.insigma.ordercenter.service.IParamShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private IParamShopService paramShopService;

    @Override
    public List<ExchangeOrGiftStrategyVO> listExchangeStrategy(StrategyParamDTO req) {
        //List<ExchangeOrGiftStrategyVO> list = Lists.newArrayList();
        // 查询换货策略
        List<ExchangeOrGiftStrategyVO> strategyList = this.baseMapper.selectExchangeStrategy(req.getStrategyId());
        strategyList.forEach(exchangeStrategy -> {
            List<ShopListVO> list = this.baseMapper.getShopList(exchangeStrategy.getId());
            exchangeStrategy.setShopIdList(list);
        });


//        // 渲染返回数据
//        strategyList.forEach(exchangeStrategy -> {
//            ExchangeOrGiftStrategyVO exchangeOrGiftStrategyVO = new ExchangeOrGiftStrategyVO();
//            BeanUtil.copyProperties(exchangeStrategy, exchangeOrGiftStrategyVO);
//            exchangeOrGiftStrategyVO.setId(exchangeStrategy.getExchangeStrategyId());
//            exchangeOrGiftStrategyVO.setTheme(exchangeStrategy.getExchangeTheme());
//            list.add(exchangeOrGiftStrategyVO);
//        });
        return strategyList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addExchangeStrategy(AddExchangeStrategyDTO req) {
        // 新增参数主表
        ExchangeStrategy exchangeStrategy = BeanUtil.toBean(req, ExchangeStrategy.class);
        exchangeStrategy.setExchangeTheme(req.getTheme());
        baseMapper.insert(exchangeStrategy);

        // 新增参数关联的店铺
        if (null != req.getShopIdList() && !req.getShopIdList().isEmpty()) {
            List<ParamShop> paramShopList = Lists.newArrayList();
            req.getShopIdList().forEach(shopId -> {
                ParamShop paramShop = new ParamShop();
                paramShop.setParamId(exchangeStrategy.getExchangeStrategyId());
                paramShop.setShopId(shopId);
                paramShop.setParamType(1);
                paramShopList.add(paramShop);
            });
            paramShopService.saveBatch(paramShopList);
        }
    }

    @Override
    public void updateExchangeStrategy(UpdateExchangeStrategyDTO req) {

        // 修改参数主表
        ExchangeStrategy exchangeStrategy = BeanUtil.toBean(req, ExchangeStrategy.class);
        exchangeStrategy.setExchangeTheme(req.getTheme());
        baseMapper.updateById(exchangeStrategy);

        // 修改参数关联的店铺
        QueryWrapper<ParamShop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ParamShop.PARAM_TYPE,1);
        queryWrapper.eq(ParamShop.PARAM_ID,exchangeStrategy.getExchangeStrategyId());
        paramShopService.remove(queryWrapper);

        if (null != req.getShopIdList() && !req.getShopIdList().isEmpty()) {
            List<ParamShop> paramShopList = Lists.newArrayList();
            req.getShopIdList().forEach(shopId -> {
                ParamShop paramShop = new ParamShop();
                paramShop.setParamId(exchangeStrategy.getExchangeStrategyId());
                paramShop.setShopId(shopId);
                paramShop.setParamType(1);
                paramShopList.add(paramShop);
            });
            paramShopService.saveBatch(paramShopList);
        }
    }

    @Override
    public ExchangeOrGiftStrategyVO getExchangeStrategy(Long exchangeStrategyId) {

        ExchangeOrGiftStrategyVO vo = this.baseMapper.getExchangeStrategy(exchangeStrategyId);

        // 获取店铺列表
        List<ShopListVO> list = this.baseMapper.getShopList(exchangeStrategyId);
        if(list.size() > 0){
            List<String> strings = list.stream().map(ShopListVO::getShopName).collect(Collectors.toList());
            vo.setShopName(String.join(",",strings));
        }
        return vo;
    }
}
