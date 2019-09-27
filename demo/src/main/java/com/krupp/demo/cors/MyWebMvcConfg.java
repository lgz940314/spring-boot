package com.krupp.demo.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/6
 */
@Configuration
public class MyWebMvcConfg implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置允许跨域的路径
        //registry.addRedirectViewController();
    }
}