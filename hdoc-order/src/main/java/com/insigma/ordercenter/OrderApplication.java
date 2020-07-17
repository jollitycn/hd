package com.insigma.ordercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author youwk
 * @ClassName OCSystemApplication
 * @description TODO
 * @date 2020/7/2 9:52
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.insigma"})
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class,args);

    }
}
