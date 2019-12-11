package com.krupp.sources.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 根据配置连接mongoDB副库
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/11
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
public class SecondaryMongoConfig extends  AbstractMongoConfig{

    @Override
    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}