package com.insigma.ordercenter.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.query.*;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserDetailVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserListVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUserListVO> getSysUserList(Page<SysUserListVO> page, BaseRequest request);

    Result<?> deleteSysUser(Long userId, LoginUser loginUser);

    /**
     * 用户登录：返回用户信息、企业信息、菜单信息
     *
     * @param userLoginQuery 登录用户信息
     * @param request        请求信息
     * @param response       响应
     * @return
     * @author Pan Juncai
     * @date 2020/1/14 14:31
     */
    Result<LoginUser> login(UserLoginQuery userLoginQuery, HttpServletRequest request, HttpServletResponse response);

    boolean addSysUser(CreateUserQuery user, LoginUser loginUser);


    boolean updateUserInfo(LoginUser loginUser, UpdateUserQuery userInfo);

    SysUserDetailVO getSysUserDetail(Long userId);

    Result<?> codeVerify(VerifyCodeQuery verifyCodeQuery, HttpServletRequest request, HttpServletResponse response);

    Result<?> msgVerify(VerifyMsgQuery verifyMsgQuery);

    /**
     * 修改用户角色
     *
     * @param param 修改的用户角色信息
     * @author Pan Juncai
     * @date 2020/7/24 17:59
     */
    void updateUserRole(UpdateUserRoleQuery param);

}
