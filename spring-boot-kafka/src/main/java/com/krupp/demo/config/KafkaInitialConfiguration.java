package com.krupp.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/17
 */
@Configuration
public class KafkaInitialConfiguration {

    //创建TopicName为topic.quick.initial的Topic并设置分区数为8以及副本数为1
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("user-log",8, (short) 1 );
    }
}
