package com.insigma.ordercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author youwk
 * @ClassName 上传
 * @description TODO
 * @date 2020/5/12 15:14
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.insigma.ordercenter.mapper")
@ComponentScan(basePackages = "com.insigma")
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class,args);
    }
}
