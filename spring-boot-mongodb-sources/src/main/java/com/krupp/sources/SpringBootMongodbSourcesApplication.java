package com.krupp.sources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 在启动类上加上注解，exclude排除springBoot启动自动加载MongoDB
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SpringBootMongodbSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbSourcesApplication.class, args);
    }

}
