package com.zhl.springboot.jwt.config;

import com.zhl.springboot.jwt.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Lenovo
 * @title: WebConfig
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/28 22:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**");
    }
}
