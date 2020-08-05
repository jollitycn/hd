package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.Gift;
import com.insigma.ordercenter.entity.GiftStrategy;
import com.insigma.ordercenter.entity.ParamShop;
import com.insigma.ordercenter.entity.dto.AddGiftDTO;
import com.insigma.ordercenter.entity.dto.AddGiftStrategyDTO;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.ExchangeOrGiftStrategyVO;
import com.insigma.ordercenter.entity.vo.GiftListVO;
import com.insigma.ordercenter.entity.vo.GiftStrategyInfoVO;
import com.insigma.ordercenter.entity.vo.ShopListVO;
import com.insigma.ordercenter.mapper.GiftStrategyMapper;
import com.insigma.ordercenter.service.IGiftService;
import com.insigma.ordercenter.service.IGiftStrategyService;
import com.insigma.ordercenter.service.IParamShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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
//        List<Gift> giftList = Lists.newArrayList();
//        req.getGiftList().forEach(addGiftDTO -> {
//            Gift gift = new Gift();
//            BeanUtil.copyProperties(addGiftDTO, gift);
//            gift.setGiftStrategyId(giftStrategy.getGiftStrategyId());
//            giftList.add(gift);
//        });
//        if (!giftList.isEmpty()) {
//            giftService.saveBatch(giftList);
//        }

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

    @Override
    public boolean addGift(AddGiftDTO req) {

        // 在新增参数与赠品的关联关系
        List<Gift> giftList = Lists.newArrayList();
        req.getProductId().forEach(addGiftDTO -> {
            Gift gift = new Gift();
            gift.setProductId(addGiftDTO);
            gift.setGiftStrategyId(req.getGiftStrategyId());
            giftList.add(gift);
        });
        boolean b = false;
        if (!giftList.isEmpty()) {
             b = giftService.saveBatch(giftList);
        }
        return b;
    }

    @Override
    public List<GiftListVO> getGiftList(Long giftStrategyId) {
        List<GiftListVO> list = this.baseMapper.getGiftList(giftStrategyId);
        return list;
    }

    @Override
    public GiftStrategyInfoVO getGiftStrategyInfo(Long giftStrategyId) {

        GiftStrategyInfoVO infoVO = new GiftStrategyInfoVO();
        // 查询赠品参数信息
        GiftStrategy giftStrategy = this.baseMapper.selectById(giftStrategyId);
        BeanUtil.copyProperties(giftStrategy,infoVO);

        // 查找商铺信息
        List<ShopListVO> shopList = this.baseMapper.getShop(giftStrategyId);
        infoVO.setShopIdList(shopList);

        // 查找赠品信息
        List<GiftListVO> giftList = this.baseMapper.getGiftList(giftStrategyId);
        infoVO.setGiftListVOS(giftList);

        return infoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateGiftStrategy(AddGiftStrategyDTO req) {

        // 先更新赠品策略参数
        GiftStrategy giftStrategy = BeanUtil.toBean(req, GiftStrategy.class);
        int i = baseMapper.updateById(giftStrategy);

        // 先删除关联商铺
        QueryWrapper<ParamShop> wrapper = new QueryWrapper<>();
        wrapper.eq(ParamShop.PARAM_TYPE,2);
        wrapper.eq(ParamShop.PARAM_ID,req.getGiftStrategyId());
        this.paramShopService.remove(wrapper);

        List<ParamShop> paramShopList = Lists.newArrayList();
        req.getShopIdList().forEach(shopId -> {
            ParamShop paramShop = new ParamShop();
            paramShop.setParamId(giftStrategy.getGiftStrategyId());
            paramShop.setShopId(shopId);
            paramShop.setParamType(2);
            paramShopList.add(paramShop);
        });
        boolean b = paramShopService.saveBatch(paramShopList);

        if(b && 1 == i){
            return true;
        }
        return false;
    }
}
