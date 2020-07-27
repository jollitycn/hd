package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.RoleMenuRelation;
import com.insigma.ordercenter.entity.query.AddMenuQuery;

/**
 * <p>
 * 角色菜单关联 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-01-10
 */
public interface IRoleMenuRelationService extends IService<RoleMenuRelation> {

    Boolean addRoleMenuList(AddMenuQuery addMenuQuery);

}
