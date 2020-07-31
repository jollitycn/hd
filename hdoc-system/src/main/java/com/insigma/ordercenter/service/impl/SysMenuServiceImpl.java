package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.query.UpdateRoleMenuButtonQuery;
import com.insigma.ordercenter.entity.vo.RoleMenuVO;
import com.insigma.ordercenter.entity.vo.SysMenuVO;
import com.insigma.ordercenter.mapper.RoleMenuRelationMapper;
import com.insigma.ordercenter.mapper.SysMenuMapper;
import com.insigma.ordercenter.mapper.UserRoleRelationMapper;
import com.insigma.ordercenter.service.IRoleButtonService;
import com.insigma.ordercenter.service.IRoleMenuRelationService;
import com.insigma.ordercenter.service.ISysButtonService;
import com.insigma.ordercenter.service.ISysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Resource
    RoleMenuRelationMapper roleMenuRelationMapper;
    @Resource
    UserRoleRelationMapper userRoleRelationMapper;

    @Resource
    private ISysButtonService buttonService;

    @Resource
    private IRoleMenuRelationService roleMenuRelationService;

    @Resource
    private IRoleButtonService roleButtonService;

    @Override
    public List<SysMenuVO> getAllMenuList() {
        List<SysMenuVO> sysMenus = baseMapper.queryAllMenu("0");
        sysMenus.forEach(SysMenuVO -> {
            ;
            List<SysMenuVO> sysMenuList = baseMapper.queryAllMenu(SysMenuVO.getMenuId().toString());
            SysMenuVO.setMenuVOList(sysMenuList);
        });
        return sysMenus;
    }

    @Override
    public RoleMenuVO listMenuAndButtonByRoleId(Long roleId) {
        RoleMenuVO roleMenuVO = new RoleMenuVO();
        // 查询一级菜单
        QueryWrapper<SysMenu> levelOneWrapper = new QueryWrapper<>();
        levelOneWrapper.eq("menu_level", 1);
        List<SysMenu> levelOneMenus = baseMapper.selectList(levelOneWrapper);

        List<SysLoginUserMenu> list = Lists.newArrayList();
        levelOneMenus.forEach(levelOneMenu -> {
            SysLoginUserMenu sysLoginUserMenu = new SysLoginUserMenu();
            BeanUtil.copyProperties(levelOneMenu, sysLoginUserMenu);

            // 判断是否配置菜单权限
            QueryWrapper<RoleMenuRelation> roleLevelOneMenuWrapper = new QueryWrapper<>();
            roleLevelOneMenuWrapper.eq("role_id", roleId);
            roleLevelOneMenuWrapper.eq("menu_id", levelOneMenu.getMenuId());
            Integer levelOneCount = roleMenuRelationMapper.selectCount(roleLevelOneMenuWrapper);
            if (levelOneCount > 0) {
                // 菜单已配置
                sysLoginUserMenu.setCheckFlag(1);
            } else {
                // 菜单未配置
                sysLoginUserMenu.setCheckFlag(0);
            }

            // 查询二级菜单
            QueryWrapper<SysMenu> levelTwoWrapper = new QueryWrapper<>();
            levelTwoWrapper.eq("menu_level", 2);
            levelTwoWrapper.eq("parent_id", levelOneMenu.getMenuId());
            List<SysMenu> levelTwoMenus = baseMapper.selectList(levelTwoWrapper);

            List<SysLoginUserMenu> subMenus = Lists.newArrayList();
            levelTwoMenus.forEach(levelTwoMenu -> {
                SysLoginUserMenu sub = new SysLoginUserMenu();
                BeanUtil.copyProperties(levelTwoMenu, sub);

                // 判断是否配置菜单权限
                QueryWrapper<RoleMenuRelation> roleLevelTwoMenuWrapper = new QueryWrapper<>();
                roleLevelTwoMenuWrapper.eq("menu_id", levelTwoMenu.getMenuId());
                roleLevelTwoMenuWrapper.eq("role_id", roleId);
                Integer levelTwoCount = roleMenuRelationMapper.selectCount(roleLevelTwoMenuWrapper);
                if (levelTwoCount > 0) {
                    // 菜单已配置
                    sub.setCheckFlag(1);
                } else {
                    // 菜单未配置
                    sub.setCheckFlag(0);
                }

                // 查询按钮
                QueryWrapper<SysButton> buttonWrapper = new QueryWrapper<>();
                buttonWrapper.eq(SysButton.MENU_ID, levelTwoMenu.getMenuId());
                List<SysButton> buttons = buttonService.list(buttonWrapper);

                List<SysButtonVO> sysButtonList = Lists.newArrayList();
                buttons.forEach(button -> {
                    SysButtonVO sysButtonVO = new SysButtonVO();
                    BeanUtil.copyProperties(button, sysButtonVO);
                    // 判断该角色是否配置了button
                    QueryWrapper<RoleButton> roleButtonWrapper = new QueryWrapper<>();
                    roleButtonWrapper.eq(RoleButton.ROLE_ID, roleId);
                    roleButtonWrapper.eq(RoleButton.BUTTON_ID, button.getButtonId());
                    int count = roleButtonService.count(roleButtonWrapper);
                    if (count > 0) {
                        // 已配置按钮权限
                        sysButtonVO.setCheckFlag(1);
                    } else {
                        // 未配置按钮权限
                        sysButtonVO.setCheckFlag(0);
                    }
                    sysButtonList.add(sysButtonVO);
                });
                sub.setButtonList(sysButtonList);
                subMenus.add(sub);
            });
            sysLoginUserMenu.setSubs(subMenus);
            list.add(sysLoginUserMenu);
        });
        roleMenuVO.setMenuList(list);
        return roleMenuVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenuAndButton(UpdateRoleMenuButtonQuery req) {
        // 先删除原有的菜单权限和按钮权限
        QueryWrapper<RoleMenuRelation> deleteMenuWrapper = new QueryWrapper<>();
        deleteMenuWrapper.eq("role_id", req.getRoleId());
        roleMenuRelationMapper.delete(deleteMenuWrapper);
        QueryWrapper<RoleButton> deleteButtonWrapper = new QueryWrapper<>();
        deleteButtonWrapper.eq(RoleButton.ROLE_ID, req.getRoleId());
        roleButtonService.remove(deleteButtonWrapper);

        // 建立新的菜单、按钮关系
        if (null != req.getMenuIdList() && !req.getMenuIdList().isEmpty()) {
            List<RoleMenuRelation> menuList = Lists.newArrayList();
            req.getMenuIdList().forEach(menuId -> {
                RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
                roleMenuRelation.setRoleId(req.getRoleId());
                roleMenuRelation.setMenuId(menuId);
                menuList.add(roleMenuRelation);
            });
            roleMenuRelationService.saveBatch(menuList);
        }
        if (null != req.getButtonIdList() && !req.getButtonIdList().isEmpty()) {
            List<RoleButton> buttonList = Lists.newArrayList();
            req.getButtonIdList().forEach(buttonId -> {
                RoleButton roleButton = new RoleButton();
                roleButton.setRoleId(req.getRoleId());
                roleButton.setButtonId(buttonId);
                buttonList.add(roleButton);
            });
            roleButtonService.saveBatch(buttonList);
        }
    }

    @Override
    public List<SysMenu> listByRoleId(Long roleId) {
        QueryWrapper<RoleMenuRelation> roleMenuRelationQueryWrapper = new QueryWrapper<RoleMenuRelation>();
        roleMenuRelationQueryWrapper.eq("role_id", roleId);
        List<RoleMenuRelation> roleMenuRelations = roleMenuRelationMapper.selectList(roleMenuRelationQueryWrapper);
        QueryWrapper<RoleMenuRelation> menu = new QueryWrapper<RoleMenuRelation>();
        List<Long> menuIds = new ArrayList<>();
        roleMenuRelations.forEach(
                new Consumer<RoleMenuRelation>() {
                    @Override
                    public void accept(RoleMenuRelation roleMenuRelation) {
                        menuIds.add(roleMenuRelation.getMenuId());
                    }
                }
        );
        List<SysMenu> result = Lists.newArrayList();
        if (!menuIds.isEmpty()) {
            return baseMapper.selectBatchIds(menuIds);
        }
        return result;
    }

    @Override
    public List<SysMenu> listByUserId(Long userId) {
        List<SysMenu> result = new ArrayList<>();
        QueryWrapper<UserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper<UserRoleRelation>();
        userRoleRelationQueryWrapper.eq("user_id", userId);
        List<UserRoleRelation> roleMenuRelations = userRoleRelationMapper.selectList(userRoleRelationQueryWrapper);
        if (roleMenuRelations != null) {
            roleMenuRelations.forEach(new Consumer<UserRoleRelation>() {
                @Override
                public void accept(UserRoleRelation userRoleRelation) {
                    List<SysMenu> resultByRole = listByRoleId(userRoleRelation.getRoleId());
                    for (SysMenu menu :
                            resultByRole) {
                        if (!result.contains(menu)) {
                            result.add(menu);
                        }
                    }
                }
            });
        }

        return result;
    }

    @Override
    public List<SysMenu> listByRoleIds(List<Long> roleIds) {
        List<SysMenu> result = new ArrayList<>();
        if (roleIds != null) {
            roleIds.forEach(new Consumer<Long>() {
                @Override
                public void accept(Long roleId) {
                    List<SysMenu> resultByRole = listByRoleId(roleId);
                    for (SysMenu menu :
                            resultByRole) {
                        if (!result.contains(menu)) {
                            result.add(menu);
                        }
                    }
                }
            });
        }

        return result;
    }
}
