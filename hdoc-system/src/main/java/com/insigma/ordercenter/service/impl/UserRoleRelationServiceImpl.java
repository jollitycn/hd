package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.UserRoleRelation;
import com.insigma.ordercenter.mapper.UserRoleRelationMapper;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-10
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements IUserRoleRelationService {

    @Override
    public List<UserRoleRelation> listByUserId(Long userId) {
            QueryWrapper<UserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper<UserRoleRelation>();
            userRoleRelationQueryWrapper.eq("user_id", userId);
            List<UserRoleRelation> result = baseMapper.selectList(userRoleRelationQueryWrapper);
            return result;
        }

}
