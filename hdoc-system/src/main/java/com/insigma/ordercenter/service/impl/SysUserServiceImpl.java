package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.constant.SysUserStopEnum;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.query.*;
import com.insigma.ordercenter.entity.vo.sysuservo.SysRoleListVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserDetailVO;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserListVO;
import com.insigma.ordercenter.enums.RedisKeyEnum;
import com.insigma.ordercenter.mapper.RoleButtonMapper;
import com.insigma.ordercenter.mapper.SysMenuMapper;
import com.insigma.ordercenter.mapper.SysUserMapper;
import com.insigma.ordercenter.mapper.UserRoleRelationMapper;
import com.insigma.ordercenter.service.ISysRoleService;
import com.insigma.ordercenter.service.ISysUserService;
import com.insigma.ordercenter.service.IUserRoleRelationService;
import com.insigma.ordercenter.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private IUserRoleRelationService userRoleRelationService;

    @Resource
    private UserRoleRelationMapper userRoleRelationMapper;

    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RoleButtonMapper roleButtonMapper;

    @Override
    public IPage<SysUserListVO> getSysUserList(Page<SysUserListVO> page, BaseRequest request) {

        String userAccount = StringUtil.addPercent(request.getSearchKey());
        List<SysUserListVO> resultList = baseMapper.getSysUserList(page, userAccount,request.getUserName(),request.getRoleId());
        for (SysUserListVO user : resultList) {
            SysUser sysUser = getById(user.getCreateId());

            user.setCreateName(sysUser.getUserName());

            List<SysRoleListVO> list = new ArrayList<>();
            List<UserRoleRelation> userRoleRelations = userRoleRelationService.listByUserId(user.getUserId());
            for (UserRoleRelation userRoleRelation : userRoleRelations) {
                SysRole sysRole = sysRoleService.getById(userRoleRelation.getRoleId());
                if (null != sysRole) {
                    SysRoleListVO sysRoleListVO = new SysRoleListVO();
                    sysRoleListVO.setRoleId(sysRole.getRoleId());
                    sysRoleListVO.setRoleName(sysRole.getRoleName());
                    list.add(sysRoleListVO);
                }
            }
            user.setRoles(list);
        }

        IPage<SysUserListVO> iPage = page;
        iPage.setRecords(resultList);
        return iPage;
    }

    @Override
    public Result<?> deleteSysUser(Long userId, LoginUser loginUser) {
        SysUser sysUser = getById(userId);
        sysUser.setIsDeleted(Constant.SYS_ONE);
        sysUser.setModifyId(loginUser.getUserId());
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = updateById(sysUser);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
    }

    @Override
    public Result<LoginUser> login(UserLoginQuery userLoginQuery, HttpServletRequest request,
                                   HttpServletResponse response) {
        // 查看验证码是否正确
//        HttpSession session = request.getSession();
//        String verifyCodeRedisKey = session.getId() + Constant.Sys.RAND_IMAGE_VALIDATE_CODE;
//        if (!redisUtil.hasKey(verifyCodeRedisKey)) {
//            return Result.error(CodeMsg.IMAGE_VALIDATE_CODE_ERROR);
//        }
//        Object verifyCode = redisUtil.get(verifyCodeRedisKey);
//        if (!userLoginQuery.getRandImageValidateCode().toLowerCase().equals(verifyCode)) {
//            return Result.error(CodeMsg.IMAGE_VALIDATE_CODE_ERROR);
//        }
//        //从缓存中移除验证码
//        redisUtil.del(verifyCodeRedisKey);
        SysUser user = baseMapper.getSysUserByAccount(userLoginQuery.getUserAccount());
        // 判断用户是否存在
        if (null == user) {
            return Result.error(CodeMsg.USER_NOT_EXIST);
        }
        // 判断用户是否停用
        if (SysUserStopEnum.USER_HAS_STOPPED.getStatus().equals(user.getIsStopped())) {
            return Result.error(CodeMsg.USER_HAS_STOPPED);
        }
        // 判断用户密码是否正确
        if (!user.getUserPassword().equals(PasswordUtil.sha256HexByMd5Str(userLoginQuery.getPassword()))) {
            return Result.error(CodeMsg.USER_PASS_ERROR);
        }

        //登陆成功，将用户信息封装到返回实体中并放入session中
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);

        // 查询用户角色信息
        QueryWrapper<UserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper<UserRoleRelation>();
        userRoleRelationQueryWrapper.eq("user_id", user.getUserId());
        List<UserRoleRelation> rolesRelation = userRoleRelationMapper.selectList(userRoleRelationQueryWrapper);
        if (null != rolesRelation && !rolesRelation.isEmpty()) {
            List<Long> rolesId = Lists.newArrayList();
            for (UserRoleRelation role : rolesRelation) {
                rolesId.add(role.getRoleId());
            }
            loginUser.setRolesId(rolesId);
            // 获取其所有角色的一级菜单信息（去重）
            List<SysLoginUserMenu> sysLoginUserMenus = sysMenuMapper.listLevelOneMenuByRoleIds(rolesId);
            sysLoginUserMenus.forEach(levelOneMenu -> {
                List<SysLoginUserMenu> subList = listChildrenMenu(rolesId, levelOneMenu.getMenuId() + "");
                levelOneMenu.setSubs(subList);
                subList.forEach(levelTwoMenu -> {
                    // 查询按钮
                    List<SysButtonVO> buttons = roleButtonMapper.listButtonByRoles(rolesId, levelTwoMenu.getMenuId());
                    levelTwoMenu.setButtonList(buttons);
                });
            });
            loginUser.setMenus(sysLoginUserMenus);
        }
        String redisUserKey = UUID.randomUUID().toString().replaceAll("-", "");
        loginUser.setToken(redisUserKey);
        String jsonStr = JsonUtil.beanToJson(loginUser);
        //生成token
        final String randomKey = jwtTokenUtil.getRandomKey();
        // randomKey和token已经生成完毕
        final String token = jwtTokenUtil.generateToken(jsonStr, randomKey);
        redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, token, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
        return Result.success(loginUser);
    }

    /**
     * 递归获取一批角色菜单的下级子菜单
     *
     * @param rolesId  角色id集合
     * @param parentId 父级id
     * @return java.util.List&lt;com.insi.da.wscommon.pojo.result.SysLoginUserMenu&gt;
     * @author Pan Juncai
     * @date 2020/1/15 9:52
     */
    private List<SysLoginUserMenu> listChildrenMenu(List<Long> rolesId, String parentId) {
        List<SysLoginUserMenu> childrenMenus = sysMenuMapper.listLowerMenuByRoleIds(rolesId, parentId);
        childrenMenus.forEach(menu -> {
            menu.setSubs(listChildrenMenu(rolesId, menu.getMenuId() + ""));
        });
        return childrenMenus;
    }

    @Override
    public boolean addSysUser(CreateUserQuery user, LoginUser loginUser) {

        //存储系统用户
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount(user.getUserAccount());
        sysUser.setUserPassword(PasswordUtil.sha256HexByMd5Str(user.getUserPassword()));
        sysUser.setUserName(user.getUserName());
        sysUser.setMobilePhone(user.getMobilePhone());
        sysUser.setCreateId(loginUser.getUserId());
        sysUser.setCreateTime(LocalDateTime.now());
        boolean status = save(sysUser);
//        if (!status) {
//            return false;
//        } else {
//            //存储用户角色关联
//            for (Long roleId : user.getRoleIds()) {
//                UserRoleRelation userRoleRelation = new UserRoleRelation();
//                userRoleRelation.setUserId(sysUser.getUserId());
//                userRoleRelation.setRoleId(roleId);
//                status = status && userRoleRelationService.save(userRoleRelation);
//            }
//        }
        return status;

    }

    @Override
    public boolean updateUserInfo(LoginUser loginUser, UpdateUserQuery userInfo) {

        SysUser sysUser = getById(userInfo.getUserId());
        sysUser.setUserName(userInfo.getUserName());
        sysUser.setMobilePhone(userInfo.getMobilePhone());
        sysUser.setModifyId(loginUser.getUserId());
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = updateById(sysUser);

        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userInfo.getUserId());
        userRoleRelationService.remove(wrapper);

        //存储用户角色关联
        for (Long roleId : userInfo.getRoleIds()) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(sysUser.getUserId());
            userRoleRelation.setRoleId(roleId);
            status = status && userRoleRelationService.save(userRoleRelation);
        }

        return status;
    }

    @Override
    public SysUserDetailVO getSysUserDetail(Long userId) {

        SysUser user = getById(userId);
        SysUserDetailVO userInfo = new SysUserDetailVO();
        BeanUtils.copyProperties(user, userInfo);
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<UserRoleRelation> relationList = userRoleRelationService.list(wrapper);

        List<SysRoleListVO> roleList = new ArrayList<>();
        for (UserRoleRelation userRoleRelation : relationList) {
            SysRoleListVO sysRoleListVO = new SysRoleListVO();
            sysRoleListVO.setRoleId(userRoleRelation.getRoleId());
            SysRole sysRole = sysRoleService.getById(userRoleRelation.getRoleId());
            if (null != sysRole) {
                sysRoleListVO.setRoleName(sysRole.getRoleName());
            }
            roleList.add(sysRoleListVO);
        }
        userInfo.setRoles(roleList);
        return userInfo;
    }

    @Override
    public Result<?> codeVerify(VerifyCodeQuery verifyCodeQuery, HttpServletRequest request,
                                HttpServletResponse response) {

        // 查看验证码是否正确
        HttpSession session = request.getSession();

        if (!verifyCodeQuery.getRandImageValidateCode().equals(session.getAttribute(Constant.Sys.RAND_IMAGE_VALIDATE_CODE))) {
            return Result.error(CodeMsg.IMAGE_VALIDATE_CODE_ERROR);
        } else {

            //获取手机号关联的用户数
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("mobile_phone", verifyCodeQuery.getMobile());
            int count = count(wrapper);
            if (count == 1) {
                //todo 发送短信验证码存储短信验证码
                return Result.success();
            } else {
                return Result.error(CodeMsg.MOBILE_NOT_EXIST);
            }

        }
    }

    @Override
    public Result<?> msgVerify(VerifyMsgQuery verifyMsgQuery) {

        //todo 验证短信验证码
        boolean status = true;

        if (status) {
            //获取手机号关联的用户
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("mobile_phone", verifyMsgQuery.getMobile());
            SysUser sysUser = getOne(wrapper);
            if (sysUser == null) {
                return Result.error(CodeMsg.MOBILE_NOT_MATCH);
            }
            return Result.success(sysUser.getUserId().toString());
        } else {
            return Result.error(CodeMsg.MSG_WRONG);
        }
    }

    @Override
    public void updateUserRole(UpdateUserRoleQuery param) {
        // 先删除原来关联的角色
        QueryWrapper<UserRoleRelation> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq(UserShopRelation.USER_ID, param.getUserId());
        userRoleRelationMapper.delete(deleteWrapper);

        // 在重新关联新的角色
        List<UserRoleRelation> list = Lists.newArrayList();
        param.getRoleList().forEach(shopId -> {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(param.getUserId());
            userRoleRelation.setRoleId(shopId);
            list.add(userRoleRelation);
        });
        userRoleRelationService.saveBatch(list);
    }

}
