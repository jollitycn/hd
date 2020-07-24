package com.insigma.ordercenter.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.insigma.ordercenter.gateway.res.JwtResponse;
import com.insigma.ordercenter.gateway.res.RedisKeyEnum;
import com.insigma.ordercenter.gateway.res.RedisUser;
import com.insigma.ordercenter.gateway.util.DateUtils;
import com.insigma.ordercenter.gateway.util.JwtTokenUtil;
import com.insigma.ordercenter.gateway.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @ClassName TokenFilter
 * @Description 登录token拦截
 * @Author youwk
 * @Date 2019/6/13 15:07
 * @Version 1.0
 */

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * JWT 失效时间小于60分钟，更新JWT
     */
    private final static Long EXPIREDJWT = 3600L;

    /**
     * redis 30 分钟会话失效时间
     */
    private final static Long EXPIREDREDIS = 1800L;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uuid = exchange.getRequest().getHeaders().getFirst(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey());
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String authToken = "";
        try {
            String path = serverHttpRequest.getURI().getPath();
            String[] ignoresUrl = {"/validateCode/getValidateCodeImage","login","/order/sfoms/cb","/v2/api-docs","/swagger-ui.html"};
            for (String url : ignoresUrl) {
                if (path.contains(url)) {
                    return chain.filter(exchange);
                }
            }

            if (StringUtils.isBlank(uuid)) {
                return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "token出错");
            } else {
                Object sessionJwt = redisUtil.get(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + uuid);
                if (sessionJwt == null) {
                    return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "会话已失效，请重新登录");
                }
                authToken = String.valueOf(sessionJwt);
                String userInfo = jwtTokenUtil.getUsernameFromToken(authToken);
                RedisUser redisUser = new Gson().fromJson(userInfo, RedisUser.class);
                Date getExpirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(String.valueOf(sessionJwt));
                long remainingMinutes = DateUtils.getMinuteDifference(getExpirationDateFromToken, DateUtils.getCurrentTime());
                //刷新JWT
                if (remainingMinutes <= EXPIREDJWT) {
                    // randomKey和token已经生成完毕
                    final String randomKey = jwtTokenUtil.getRandomKey();
                    final String newToken = jwtTokenUtil.generateToken(userInfo, randomKey);
                    redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getToken(), newToken, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                    authToken = newToken;
                }
                //刷新Redis-token时间
                Long expireTime = redisUtil.getExpire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getToken());
                if (expireTime <= EXPIREDREDIS) {
                    //刷新redis时间
                    redisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUser.getToken(), RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                }
                ServerHttpRequest request = exchange
                        .getRequest().mutate()
                        // 统一头部，用于防止直接调用服务
                        .header(RedisKeyEnum.REDIS_KEY_USER_HEADER_CODE.getKey(), authToken)
                        .header("exit", uuid)
                        .build();
                exchange.getResponse().getHeaders().add("testresponse", "testresponse");
                ServerWebExchange webExchange = exchange.mutate().request(request).build();
                return chain.filter(webExchange);
            }
        } catch (MalformedJwtException e) {
            log.error("JWT格式错误异常:{}======>>>:{}====={}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT格式错误");
        } catch (SignatureException e) {
            log.error("JWT签名错误异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT签名错误");
        } catch (ExpiredJwtException e) {
            log.error("JWT过期异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "会话已失效，请重新登录");
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "JWT格式不正确");
        } catch (Exception e) {
            log.error("TokenFilter，不支持的异常:{}======>>>:{}", uuid, e.getMessage(), e);
            return JwtResponse.jwtResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "TokenFilter：token 解析异常:");
        }
    }

    @Override
    public int getOrder() {
        return -999;
    }
}