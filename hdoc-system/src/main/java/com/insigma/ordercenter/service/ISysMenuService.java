package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.SysMenu;
import com.insigma.ordercenter.entity.vo.SysMenuVO;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-09
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu> listByRoleId(Long roleId);
    List<SysMenu> listByUserId(Long userId);
    List<SysMenu> listByRoleIds(List<Long> roleIds);

    List<SysMenuVO> getAllMenuList();

}
