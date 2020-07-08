package com.insigma.ordercenter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web 配置类
 *
 * @author Xuchao
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置string解析器放在json解析器前边
     *
     * @author fengshuonan
     * @Date 2019/8/7 23:09
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        HttpMessageConverter<?> httpMessageConverter = converters.get(0);
        converters.remove(0);
        converters.add(2, httpMessageConverter);
    }

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        //本应用
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");

        //流程设计器
//        registry.addResourceHandler("/activiti-editor/**").addResourceLocations("classpath:/activiti-editor/");

        //静态资源访问
//        registry.addResourceHandler(mvcPath).addResourceLocations(fileImageAddress);
//        registry.addResourceHandler("/static/**").addResourceLocations("file:D:/uploadPath");
    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }
}
