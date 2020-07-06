package com.insigma.ordercenter.gateway.res;

import com.alibaba.fastjson.JSONObject;
import com.insigma.ordercenter.gateway.common.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Created by MOMO on 2018/12/28.
 */
public class JwtResponse {
    public static Mono<Void> jwtResponse(ServerWebExchange exchange, Integer httpStatus, String responseMsg) {
        byte[] bytes = JSONObject.toJSONString(Result.of(null, httpStatus.intValue(), responseMsg)).getBytes(StandardCharsets.UTF_8);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
