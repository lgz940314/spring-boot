package com.krupp.demo.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/29
 * 消除警告的第二种配置选择
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/");
        super.addResourceHandlers(registry);
    }
}
