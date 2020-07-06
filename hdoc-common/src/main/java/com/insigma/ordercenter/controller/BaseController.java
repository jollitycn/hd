package com.insigma.ordercenter.controller;

import com.google.gson.Gson;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.enums.RedisKeyEnum;
import com.insigma.ordercenter.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author youwk
 * @ClassName BaseController
 * @description TODO
 * @date 2020/4/27 15:59
 * @Version 1.0
 */
public class BaseController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 由网关 传入最新的user信息
     * @return
     */
    public LoginUser redisUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authToken = request.getHeader(RedisKeyEnum.REDIS_KEY_USER_HEADER_CODE.getKey());
        //解析token
        if (!StringUtils.isEmpty(authToken)) {
            //解析token
            String userInfo = jwtTokenUtil.getUsernameFromToken(authToken);
            LoginUser redisUser = new Gson().fromJson(userInfo, LoginUser.class);
            return redisUser;
        }
        return null;
    }
}
