package com.insigma.ordercenter.gateway.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 */
@Configuration
@Data
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "mySecret";

    private Long expiration = 604800L;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    private String ignoreUrl = "";

}
