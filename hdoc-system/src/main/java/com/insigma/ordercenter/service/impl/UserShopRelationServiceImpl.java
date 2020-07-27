package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.UserShopRelation;
import com.insigma.ordercenter.entity.query.UpdateUserShopQuery;
import com.insigma.ordercenter.entity.vo.ShopInfoVO;
import com.insigma.ordercenter.mapper.UserShopRelationMapper;
import com.insigma.ordercenter.service.IUserShopRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户-店铺关联表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-24
 */
@Service
public class UserShopRelationServiceImpl extends ServiceImpl<UserShopRelationMapper, UserShopRelation> implements IUserShopRelationService {

    @Override
    public List<ShopInfoVO> listEnabledShop() {
        return baseMapper.listEnabledShop();
    }

    @Override
    public List<ShopInfoVO> listShopByUserId(Long userId) {
        return baseMapper.listShopByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserShop(UpdateUserShopQuery param) {
        // 先删除原来关联的店铺
        QueryWrapper<UserShopRelation> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq(UserShopRelation.USER_ID, param.getUserId());
        baseMapper.delete(deleteWrapper);

        // 在重新关联新的店铺
        List<UserShopRelation> list = Lists.newArrayList();
        param.getShopIdList().forEach(shopId -> {
            UserShopRelation userShopRelation = new UserShopRelation();
            userShopRelation.setUserId(param.getUserId());
            userShopRelation.setShopId(shopId);
            list.add(userShopRelation);
        });
        this.saveBatch(list);
    }
}
