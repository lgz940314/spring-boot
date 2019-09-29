package com.krupp.demo.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfigurerAdapter已经过时，在新版本2.x中被废弃，
 * 原因是springboot2.0以后，引用的是spring5.0，而spring5.0取消了WebMvcConfigurerAdapter
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/6
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //设置允许的方法
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                //设置允许的Web头
                .allowedHeaders("origin", "content-type", "accept", "x-requested-with")
                //是否允许证书 不再默认开启
                .allowCredentials(false)
                //跨域允许时间
                .maxAge(3600);
        super.addCorsMappings(registry);
    }
}