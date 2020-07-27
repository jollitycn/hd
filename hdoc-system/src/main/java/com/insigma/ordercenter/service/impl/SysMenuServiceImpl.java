package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.RoleMenuRelation;
import com.insigma.ordercenter.entity.SysMenu;
import com.insigma.ordercenter.entity.UserRoleRelation;
import com.insigma.ordercenter.entity.vo.SysMenuVO;
import com.insigma.ordercenter.mapper.RoleMenuRelationMapper;
import com.insigma.ordercenter.mapper.SysMenuMapper;
import com.insigma.ordercenter.mapper.UserRoleRelationMapper;
import com.insigma.ordercenter.service.ISysMenuService;
import org.springframework.stereotype.Service;

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


    @Override
    public List<SysMenuVO> getAllMenuList() {
        List<SysMenuVO> sysMenus = baseMapper.queryAllMenu("0");
        sysMenus.forEach(SysMenuVO -> { ;
            List<SysMenuVO> sysMenuList = baseMapper.queryAllMenu(SysMenuVO.getMenuId().toString());
            SysMenuVO.setMenuVOList(sysMenuList);
        });
        return sysMenus;
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
        List<SysMenu> result = baseMapper.selectBatchIds(menuIds);
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
