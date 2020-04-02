package com.krupp.nacos.springbootnacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
//使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新
public class SpringBootNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNacosApplication.class, args);
    }

}
