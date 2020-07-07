package com.insigma.ordercenter.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.query.UserLoginQuery;
import com.insigma.ordercenter.entity.query.VerifyCodeQuery;
import com.insigma.ordercenter.entity.query.VerifyMsgQuery;
import com.insigma.ordercenter.enums.RedisKeyEnum;
import com.insigma.ordercenter.service.ISysUserService;
import com.insigma.ordercenter.utils.PasswordUtil;
import com.insigma.ordercenter.utils.RandImageValidateCodeUtils;
import com.insigma.ordercenter.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台系统登录相关
 * </p>
 *
 * @author panjuncai
 * @since 2020-01-09
 */

@Api(tags = {"后台系统登录相关"})
@RestController
@Slf4j
@RefreshScope
public class SysLoginController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @GetMapping(value = "/validateCode/getValidateCodeImage")
    @ApiOperation(httpMethod = "GET", value = "获取图形验证码")
    public void getValidateCodeImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandImageValidateCodeUtils randomValidateCode = new RandImageValidateCodeUtils();
            //输出验证码图片方法
            randomValidateCode.getRandCode(request, response);
        } catch (Exception e) {
            log.error("获取验证码失败>>>>   ", e);
        }
    }

    @PostMapping(value = "/login")
    @ApiOperation(httpMethod = "POST", value = "用户登录")
    public Result<LoginUser> login(@RequestBody @Valid UserLoginQuery userLoginQuery, HttpServletRequest request,
                                   HttpServletResponse response) {
        return sysUserService.login(userLoginQuery, request, response);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(httpMethod = "GET", value = "用户登出")
    public Result<?> logout(HttpServletRequest request) {
        LoginUser redisUser = this.redisUser();
        Object listUuid = redisUtil.get(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + redisUser.getUserId());
        if (null != listUuid) {
            List<String> list = new ObjectMapper().convertValue(listUuid.toString(), List.class);
//            List<String> list1 = new Gson().fromJson(listUuid.toString(),List.class);
            List<String> list2 = JSON.parseObject((String) listUuid, new TypeReference<List<String>>() {
            });
            int index = -1;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String s = list.get(i);
                if (s.equals(redisUser.getToken())) {
                    index = i;
                }
            }
            if (index != -1) {
                list.remove(index);
            }
            redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_ID.getKey() + redisUser.getUserId(), JSONObject.toJSONString(list), RedisKeyEnum.REDIS_KEY_USER_ID.getExpireTime());
        }

        redisUtil.del(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getToken());
        return Result.success();
    }

    @PostMapping(value = "/codeVerify")
    @ApiOperation(value = "忘记密码验证短信发送")
    public Result<?> codeVerify(@RequestBody VerifyCodeQuery verifyCodeQuery, HttpServletRequest request,
                                HttpServletResponse response) {

        return sysUserService.codeVerify(verifyCodeQuery, request, response);
    }

    @PostMapping(value = "msgVerify")
    @ApiOperation(value = "短信验证码验证")
    public Result<?> msgVerify(@RequestBody VerifyMsgQuery verifyMsgQuery) {

        return sysUserService.msgVerify(verifyMsgQuery);
    }

    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    public Result<?> changePassword(Long userId, String password) {
        SysUser sysUser = sysUserService.getById(userId);
        if (sysUser == null) {
            return Result.error(CodeMsg.USER_NOT_EXIST);
        }
        sysUser.setUserPassword(PasswordUtil.sha256HexByMd5Str(password));
        sysUser.setModifyId(userId);
        sysUser.setModifyTime(LocalDateTime.now());
        boolean status = sysUserService.updateById(sysUser);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }
}
