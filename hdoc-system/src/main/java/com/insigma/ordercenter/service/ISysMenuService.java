package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.SysMenu;
import com.insigma.ordercenter.entity.query.UpdateRoleMenuButtonQuery;
import com.insigma.ordercenter.entity.vo.RoleMenuVO;
import com.insigma.ordercenter.entity.vo.SysMenuVO;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu> listByRoleId(Long roleId);

    List<SysMenu> listByUserId(Long userId);

    List<SysMenu> listByRoleIds(List<Long> roleIds);

    List<SysMenuVO> getAllMenuList();

    /**
     * 根据角色获取所有的菜单信息
     *
     * @param roleId 角色id
     * @return com.insigma.ordercenter.entity.vo.RoleMenuVO
     * @author Pan Juncai
     * @date 2020/7/30 19:27
     */
    RoleMenuVO listMenuAndButtonByRoleId(Long roleId);

    /**
     * 修改角色的菜单、按钮权限
     *
     * @param req 新的权限
     * @author Pan Juncai
     * @date 2020/7/31 9:25
     */
    void updateRoleMenuAndButton(UpdateRoleMenuButtonQuery req);
}
