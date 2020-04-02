package com.krupp.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/11/20
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("================WebSecurityConfig================");
        http.csrf().disable();
        http.authorizeRequests()// 拦截请求，创建了FilterSecurityInterceptor拦截器
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()// 设置所有请求都得经过认证后才可以访问
                .and()// 用and来表示配置过滤器结束，以便进行下一个过滤器的创建和配置
                .formLogin()// 设置表单登录，创建TSysUsernamePasswordAuthenticationFilter拦截器
                .permitAll();// 开启HTTP Basic，创建BasicAuthenticationFilter拦截器
    }

}
