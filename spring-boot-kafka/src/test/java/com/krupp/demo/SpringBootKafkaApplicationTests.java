package com.krupp.demo;

import com.krupp.demo.producer.UserLogProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootKafkaApplicationTests {
    @Autowired
    private UserLogProducer kafkaSender;

    @Test
    public void init(){
        for (int i = 0; i < 10; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.sendLog(String.valueOf(i));
        }
    }

    @Test
    void contextLoads() {

    }

}
