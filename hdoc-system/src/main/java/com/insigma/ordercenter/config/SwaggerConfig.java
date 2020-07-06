package com.insigma.ordercenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: hd
 * @description: Swagger2配置类
 * @author: XuChao
 * @create: 2019-12-23 15:00
 **/


@Configuration
@EnableSwagger2
public class SwaggerConfig  {



//可以注入多个doket，也就是多个版本的api，可以在看到有三个版本groupName不能是重复的，v1和v2是ant风格匹配，配置文件
@Bean
public Docket api() {
    //可以添加多个header或参数
    ParameterBuilder aParameterBuilder = new ParameterBuilder();
    aParameterBuilder
            .parameterType("header") //参数类型支持header, cookie, body, query etc
            .name("x-token") //参数名
            .defaultValue("token") //默认值
            .description("header中token字段测试")
            .modelRef(new ModelRef("string"))//指定参数值的类型
            .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
    List<Parameter> aParameters = new ArrayList<Parameter>();
    aParameters.add(aParameterBuilder.build());
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.insigma.ordercenter.controller"))
//            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo1())
            .globalOperationParameters(aParameters);
}

private ApiInfo apiInfo1() {
    return new ApiInfoBuilder()
            .title("系统认证服务API 1.0")
            .termsOfServiceUrl("www.insigma.com")
            .contact(new Contact("Yuki", "http://baidu.com", "youwenkui@bis.com.cn"))
            .version("v1.0")
            .build();

}
}
