package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.query.CreateUserQuery;
import com.insigma.ordercenter.entity.query.UpdateUserQuery;
import com.insigma.ordercenter.entity.query.UpdateUserRoleQuery;
import com.insigma.ordercenter.entity.query.UpdateUserShopQuery;
import com.insigma.ordercenter.entity.vo.ShopInfoVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserDetailVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserListVO;
import com.insigma.ordercenter.service.ISysRoleService;
import com.insigma.ordercenter.service.ISysUserService;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import com.insigma.ordercenter.service.IUserShopRelationService;
import com.insigma.ordercenter.utils.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Resource
    private IUserShopRelationService userShopRelationService;

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

    @GetMapping("/listEnabledShop")
    @ApiOperation("获取使用中的店铺列表")
    public Result<List<ShopInfoVO>> listEnabledShop() {
        List<ShopInfoVO> shopInfoList = userShopRelationService.listEnabledShop();
        return Result.success(shopInfoList);
    }

    @GetMapping("/listShopByUserId/{userId}")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @ApiOperation("获取该用户关联的店铺")
    public Result<List<ShopInfoVO>> listShopByUserId(@PathVariable("userId") @Valid
                                                     @NotNull(message = "用户id不能为空")
                                                     @Min(value = 1L, message = "用户id不合法")
                                                     @Max(value = Long.MAX_VALUE, message = "用户id不合法") Long userId) {
        List<ShopInfoVO> shopInfoList = userShopRelationService.listShopByUserId(userId);
        return Result.success(shopInfoList);
    }

    @PutMapping("/updateUserShop")
    @ApiOperation("修改该用户关联的店铺")
    public Result listShopByUserId(@Valid @RequestBody UpdateUserShopQuery param) {
        userShopRelationService.updateUserShop(param);
        return Result.success();
    }

    @PutMapping("/updateUserRole")
    @ApiOperation("修改该用户关联的角色")
    public Result updateUserRole(@Valid @RequestBody UpdateUserRoleQuery param) {
        sysUserService.updateUserRole(param);
        return Result.success();
    }

    @PostMapping("/test")
    @ApiOperation("test")
    public Result<?> test(@RequestBody SysUser sysUser) {

        sysUser.setUserId(null);
        return Result.success(sysUserService.save(sysUser));
    }
}
