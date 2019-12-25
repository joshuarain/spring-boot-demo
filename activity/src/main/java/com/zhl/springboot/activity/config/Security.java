package com.zhl.springboot.activity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Lenovo
 * @title: Security
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/24 8:43
 */
@Configuration
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不禁用csrf，post请求会验证token，报403
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }
}
