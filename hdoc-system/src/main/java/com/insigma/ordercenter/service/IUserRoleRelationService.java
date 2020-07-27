package com.insigma.ordercenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.UserRoleRelation;

import java.util.List;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-01-10
 */
public interface IUserRoleRelationService extends IService<UserRoleRelation> {
    List<UserRoleRelation> listByUserId(Long userId);
}
