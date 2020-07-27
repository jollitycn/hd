package com.insigma.ordercenter.controller;

import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.SysMenu;
import com.insigma.ordercenter.entity.query.AddMenuQuery;
import com.insigma.ordercenter.entity.vo.SysMenuVO;
import com.insigma.ordercenter.service.IRoleMenuRelationService;
import com.insigma.ordercenter.service.ISysMenuService;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Api(tags = {"系统菜单管理"})
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;

    @Resource
    private IUserRoleRelationService userRoleRelationService;

    @Resource
    private IRoleMenuRelationService roleMenuRelationService;

    @GetMapping("/getAllMenuList")
    @ApiOperation("查询所有的菜单列表信息")
    public Result getAllMenuList() {
        List<SysMenuVO> allMenuList = sysMenuService.getAllMenuList();
        return Result.success(allMenuList);
    }

    @GetMapping("/listByRoleId/{roleId}")
    @ApiOperation("根据角色获取菜单列表")
    public Result listByRoleId(@PathVariable Long roleId) {
        List<SysMenu> result = sysMenuService.listByRoleId(roleId);
        return Result.success(result);
    }

    @GetMapping("/listByUserId/{userId}")
    @ApiOperation("根据账号ID获取菜单列表")
    public Result listByUserId(@PathVariable Long userId) {
        List<SysMenu> result = sysMenuService.listByUserId(userId);
        userRoleRelationService.listByUserId(userId);
        return Result.success(result);
    }

    @PostMapping("/addRoleMenuRelatiin")
    @ApiOperation("添加角色和菜单关系信息")
    public Result addRoleMenuRelatiin(AddMenuQuery addMenuQuery) {
        Boolean aBoolean = roleMenuRelationService.addRoleMenuList(addMenuQuery);
        if(aBoolean){
            return Result.success();
        }
       return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }
}
