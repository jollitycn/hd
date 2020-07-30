package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.Gift;
import com.insigma.ordercenter.entity.GiftStrategy;
import com.insigma.ordercenter.entity.ParamShop;
import com.insigma.ordercenter.entity.dto.AddGiftStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.mapper.GiftStrategyMapper;
import com.insigma.ordercenter.service.IGiftService;
import com.insigma.ordercenter.service.IGiftStrategyService;
import com.insigma.ordercenter.service.IParamShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 赠品策略参数配置表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Service
public class GiftStrategyServiceImpl extends ServiceImpl<GiftStrategyMapper, GiftStrategy> implements IGiftStrategyService {

    @Resource
    private IGiftService giftService;

    @Resource
    private IParamShopService paramShopService;

    @Override
    public List<ExchangeOrGiftStrategyVO> listGiftStrategy(StrategyParamDTO req) {
        List<ExchangeOrGiftStrategyVO> list = Lists.newArrayList();
        QueryWrapper<GiftStrategy> giftStrategyWrapper = new QueryWrapper<>();
        giftStrategyWrapper.eq(GiftStrategy.STRATEGY_ID, req.getStrategyId());
        // 查询赠品策略
        List<GiftStrategy> giftStrategyList = baseMapper.selectList(giftStrategyWrapper);
        // 渲染返回数据
        giftStrategyList.forEach(giftStrategy -> {
            ExchangeOrGiftStrategyVO exchangeOrGiftStrategyVO = new ExchangeOrGiftStrategyVO();
            BeanUtil.copyProperties(giftStrategy, exchangeOrGiftStrategyVO);
            exchangeOrGiftStrategyVO.setId(giftStrategy.getGiftStrategyId());
            exchangeOrGiftStrategyVO.setTheme(giftStrategy.getGiftTheme());
            list.add(exchangeOrGiftStrategyVO);
        });
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addGiftStrategy(AddGiftStrategyDTO req) {
        // 先新增赠品策略参数
        GiftStrategy giftStrategy = BeanUtil.toBean(req, GiftStrategy.class);
        baseMapper.insert(giftStrategy);

        // 在新增参数与赠品的关联关系
        List<Gift> giftList = Lists.newArrayList();
        req.getGiftList().forEach(addGiftDTO -> {
            Gift gift = new Gift();
            BeanUtil.copyProperties(addGiftDTO, gift);
            gift.setGiftStrategyId(giftStrategy.getGiftStrategyId());
            giftList.add(gift);
        });
        if (!giftList.isEmpty()) {
            giftService.saveBatch(giftList);
        }

        // 新增参数关联的店铺
        if (null != req.getShopIdList() && !req.getShopIdList().isEmpty()) {
            List<ParamShop> paramShopList = Lists.newArrayList();
            req.getShopIdList().forEach(shopId -> {
                ParamShop paramShop = new ParamShop();
                paramShop.setParamId(giftStrategy.getGiftStrategyId());
                paramShop.setShopId(shopId);
                paramShop.setParamType(2);
                paramShopList.add(paramShop);
            });
            paramShopService.saveBatch(paramShopList);
        }
    }
}
