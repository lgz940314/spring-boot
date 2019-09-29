package com.krupp.demo.cors;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/29
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring 5 (或者Spring Boot 2.x)版本配置Web应用程序示例
 * @author ramostear
 * @create-time 2019/4/18 0018-1:40
 */
@Configuration
public class MvcConfigure implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/");
    }
}
