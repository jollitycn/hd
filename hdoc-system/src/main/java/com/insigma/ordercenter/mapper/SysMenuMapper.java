package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.SysLoginUserMenu;
import com.insigma.ordercenter.entity.SysMenu;
import com.insigma.ordercenter.entity.vo.SysMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获取一批角色的其一级菜单（去重）
     *
     * @param rolesId 角色id集合
     * @return java.util.List&lt;com.insi.da.wscommon.pojo.result.SysLoginUserMenu&gt;
     * @author Pan Juncai
     * @date 2020/1/14 18:22
     */
    List<SysLoginUserMenu> listLevelOneMenuByRoleIds(@Param("rolesId") List<Long> rolesId);

    /**
     * 根据角色id集合和上级菜单id获取其下级菜单（去重）
     *
     * @param rolesId 角色id集合
     * @param pid    上级菜单的id
     * @return java.util.List&lt;com.insi.da.wscommon.pojo.result.SysLoginUserMenu&gt;
     * @author Pan Juncai
     * @date 2020/1/14 18:22
     */
    List<SysLoginUserMenu> listLowerMenuByRoleIds(@Param("rolesId") List<Long> rolesId, @Param("pid") String pid);

    List<SysMenuVO> queryAllMenu(@Param("parentId")String parentId);

}
