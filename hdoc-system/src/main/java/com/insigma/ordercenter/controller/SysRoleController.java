package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.UserRoleRelation;
import com.insigma.ordercenter.entity.vo.SysRoleVO;
import com.insigma.ordercenter.service.ISysRoleService;
import com.insigma.ordercenter.service.ISysUserService;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import com.insigma.ordercenter.service.IUserShopRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */

@Api(tags = {"系统角色管理"})
@RestController
@RequestMapping("/sys-role")
@Slf4j
public class SysRoleController extends BaseController{

    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private ISysUserService iSysUserService;

    @Resource
    private IUserRoleRelationService iUserRoleRelationService;

    @PutMapping("/add")
    @ApiOperation("新增角色")
    public Result add(@RequestBody SysRole data) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", data.getRoleName());
        int count = sysRoleService.count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.ROLE_NAME_REPEAT);
        }
        data.setCreateId(redisUser().getUserId());
        data.setCreateTime(LocalDateTime.now());
        boolean status = sysRoleService.save(data);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @DeleteMapping("/delete/{Id}")
    @ApiOperation("删除角色")
    public Result delete(@PathVariable Long Id) {

        // 判断角色是否绑定账号，绑定则不允许删除
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.eq(UserRoleRelation.ROLE_ID,Id);
        int count = this.iUserRoleRelationService.count(wrapper);

        if(0 != count){
            return Result.error(CodeMsg.ROLE_USER);
        }

        boolean status = sysRoleService.removeById(Id);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新角色")
    public Result update(@RequestBody SysRole sys) {
        boolean status = sysRoleService.updateById(sys);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.ROLE_UPDATE_FAIL);
        }
    }

    @GetMapping("/detail/{Id}")
    @ApiOperation("获取角色详情")
    public Result detail(@PathVariable Long Id) {
        SysRole data = sysRoleService.getById(Id);
        return Result.success(data);
    }

    @GetMapping("/list")
    @ApiOperation("获取角色列表")
    public Result list() {
        List<SysRole> result = sysRoleService.list();

        result.forEach(sysRole -> {

            SysUser user = this.iSysUserService.getById(sysRole.getCreateId());
            sysRole.setCreateName(user.getUserName());
        });
        return Result.success(result);
    }

//    @GetMapping("/getPermissionByUserId/{userId}")
//    @ApiOperation("根据用户获取角色权限列表")
//    public JSONResult getPermissionByUserId(String userId) {
//        List<SysRole> result = sysRoleService.getPermissionByUserId(userId);
//        return JSONResult.ok(result);
//    }

    @GetMapping("/queryByRoleId")
    @ApiOperation("获取角色下所有的人员信息")
    public Result<IPage<SysRoleVO>> queryByRoleId(BaseRequest request) {
        Page<SysRoleVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<SysRoleVO> sysRoleVOS = sysRoleService.queryByRoleId(page,request);
        return Result.success(sysRoleVOS);
    }

}
