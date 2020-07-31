package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.RoleButton;
import com.insigma.ordercenter.entity.SysButtonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色关联按钮表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-30
 */
public interface RoleButtonMapper extends BaseMapper<RoleButton> {

    /**
     * 根据角色ids和菜单id获取有权的按钮
     *
     * @param roles  角色ids
     * @param menuId 菜单id
     * @return java.util.List&lt;com.insigma.ordercenter.entity.SysButtonVO&gt;
     * @author Pan Juncai
     * @date 2020/7/31 10:50
     */
    List<SysButtonVO> listButtonByRoles(@Param("roles") List<Long> roles, @Param("menuId") Long menuId);
}
