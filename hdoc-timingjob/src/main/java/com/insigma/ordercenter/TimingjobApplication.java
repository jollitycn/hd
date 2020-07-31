package com.insigma.ordercenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @RestController
    @RefreshScope
    static class TestController {

        @Value("${AnnualLeaveJob.cron}")
        private String title;

        @GetMapping("/test")
        public String hello() {
            return title;
        }

    }


    public static void main(String[] args) {
        SpringApplication.run(TimingjobApplication.class);
    }
}

