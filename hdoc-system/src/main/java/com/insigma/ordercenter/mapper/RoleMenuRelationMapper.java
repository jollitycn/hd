package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.RoleMenuRelation;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色菜单关联 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-01-10
 */
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

    int deleteByRoleId(@Param("roleId") Long roleId);

}
