package com.insigma.ordercenter.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.insigma")
public class HdocGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdocGatewayApplication.class, args);
    }

}