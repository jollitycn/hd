package com.insigma.ordercenter.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.utils.PasswordUtil;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.UserRoleRelation;
import com.insigma.ordercenter.entity.query.CreateUserQuery;
import com.insigma.ordercenter.entity.query.UpdateUserQuery;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserDetailVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserListVO;
import com.insigma.ordercenter.service.ISysRoleService;
import com.insigma.ordercenter.service.ISysUserService;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-09
 */
@Api(tags = {"系统用户管理"})
@RestController
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private IUserRoleRelationService userRoleRelationService;

    @GetMapping("/list")
    @ApiOperation(value = "系统用户列表")
    public Result<IPage<SysUserListVO>> list(BaseRequest request) {

        Page<SysUserListVO> page = new Page<>(request.getPageNum(), request.getPageSize());

        IPage<SysUserListVO> result = sysUserService.getSysUserList(page, request);

        return Result.success(result);
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation("删除系统用户")
    public Result<?> delete(@PathVariable Long userId) {

        return sysUserService.deleteSysUser(userId, redisUser());
    }

    @GetMapping("/detail/{userId}")
    @ApiOperation("获取用户详情")
    public Result<?> detail(@PathVariable Long userId) {

        SysUserDetailVO sysUserDetailVO = sysUserService.getSysUserDetail(userId);
        return Result.success(sysUserDetailVO);
    }

    @PostMapping("/update")
    @ApiOperation("更新用户信息")
    public Result<?> update(@RequestBody UpdateUserQuery userInfo) {

        //判断登录用户是否是业代
        boolean isAgent = false;
        List<Long> rolesId = redisUser().getRolesId();
        for (Long roleId : rolesId) {
            if (roleId == 3L) {
                isAgent = true;
            } else if (roleId == 2L) {
                isAgent = false;
                break;
            }
        }

        if (isAgent) {
            //如果是业代则只让编辑角色为经销商的用户
            SysUser sysUser = sysUserService.getById(userInfo.getUserId());
            //判断修改用户角色
            QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userInfo.getUserId());
            List<UserRoleRelation> list = userRoleRelationService.list(wrapper);

            for (UserRoleRelation userRoleRelation : list) {
                //如果角色不是经销商则禁止编辑
                if (userRoleRelation.getRoleId() != 4L) {
                    return Result.error(CodeMsg.AGENT_DENY);
                }
            }

            for (Long roleId : userInfo.getRoleIds()) {
                if (roleId != 4L) {
                    return Result.error(CodeMsg.AGENT_DENY);
                }
            }
        }

        boolean status = sysUserService.updateUserInfo(redisUser(), userInfo);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PostMapping("/block/{userId}")
    @ApiOperation("停用用户")
    public Result<?> block(@PathVariable Long userId) {

        SysUser sysUser = sysUserService.getById(userId);
        sysUser.setIsStopped(Constant.SYS_ONE);
        sysUser.setModifyId(redisUser().getUserId());
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = sysUserService.updateById(sysUser);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PostMapping("/unblock/{userId}")
    @ApiOperation("启用用户")
    public Result<?> unblock(@PathVariable Long userId) {

        SysUser sysUser = sysUserService.getById(userId);
        sysUser.setIsStopped(Constant.SYS_ZERO);
        sysUser.setModifyId(redisUser().getUserId());
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = sysUserService.updateById(sysUser);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PostMapping("/add")
    @ApiOperation("创建用户")
    public Result<?> add(@Valid @RequestBody CreateUserQuery user) {

        //判断登录用户是否是业代
//        boolean isAgent = false;
//        List<Long> rolesId = redisUser().getRolesId();
//        for (Long roleId : rolesId) {
//            if (roleId == 3L) {
//                isAgent = true;
//            } else if (roleId == 2L) {
//                isAgent = false;
//                break;
//            }
//        }
//
//        if (isAgent) {
//            //如果是业代则只让创建角色为经销商的用户
//            List<Long> roleList = user.getRoleIds();
//            for (Long roleId : roleList) {
//                if (roleId != 4L) {
//                    return Result.error(CodeMsg.AGENT_DENY);
//                }
//            }
//        }

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", user.getUserAccount());
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        int count = sysUserService.count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.USER_ACCOUNT_REPEAT);
        }

        wrapper = new QueryWrapper<>();
        wrapper.eq("mobile_phone", user.getMobilePhone());
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        count = sysUserService.count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.MOBILE_USED);
        }
        boolean status = sysUserService.addSysUser(user, redisUser());

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    public Result<?> changePassword(Long userId, String password) {

        SysUser sysUser = sysUserService.getById(userId);
        sysUser.setUserPassword(PasswordUtil.sha256HexByMd5Str(password));
        sysUser.setModifyId(redisUser().getUserId());
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = sysUserService.updateById(sysUser);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @GetMapping("/getRoleList")
    @ApiOperation("获取角色列表")
    public Result<?> getRoleList() {

        List<SysRole> sysRoleList = sysRoleService.list();
        return Result.success(sysRoleList);
    }

    @GetMapping("/verify/{userId}")
    @ApiOperation("验证角色权限")
    public Result<?> verify(@PathVariable Long userId) {

        //判断登录用户是否是业代
        boolean isAgent = false;
        List<Long> rolesId = redisUser().getRolesId();
        for (Long roleId : rolesId) {
            if (roleId == 3L) {
                isAgent = true;
            } else if (roleId == 2L) {
                isAgent = false;
                break;
            }
        }

        if (isAgent) {
            //如果是业代则只让编辑角色为经销商的用户
            SysUser sysUser = sysUserService.getById(userId);
            //判断修改用户角色
            QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            List<UserRoleRelation> list = userRoleRelationService.list(wrapper);

            for (UserRoleRelation userRoleRelation : list) {
                //如果角色不是经销商则禁止编辑
                if (userRoleRelation.getRoleId() != 4L) {
                    return Result.error(CodeMsg.AGENT_DENY);
                }
            }
        }
        return Result.success();
    }

    @PostMapping("/test")
    @ApiOperation("test")
    public Result<?> test(@RequestBody SysUser sysUser) {

        sysUser.setUserId(null);
        return Result.success(sysUserService.save(sysUser));
    }
}
