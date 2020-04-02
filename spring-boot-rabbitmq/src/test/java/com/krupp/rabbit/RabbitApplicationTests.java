package com.krupp.rabbit;

import com.krupp.rabbit.config.RabbitConfig;
import com.krupp.rabbit.entity.Book;
import com.krupp.rabbit.producer.RabbitOrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitApplicationTests {


    @Test
    public void contextLoads() {

         }

/*    @Test
    public void send() {Book book = new Book();
        book.setId("1");
        book.setName("一本好书");
        rabbitOrderSender.sendSimpleMessage(null,book,"1", RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY);
    }*/


}
