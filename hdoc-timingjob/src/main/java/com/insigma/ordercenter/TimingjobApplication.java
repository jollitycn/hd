package com.insigma.ordercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p></p>
 *
 * @author Xuchao
 * @className TimingjobApplication
 * @since 2020/7/15 15:09
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TimingjobApplication {

    public static void main(String[] args) {

        SpringApplication.run(TimingjobApplication.class);

    }
}

