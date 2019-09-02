package com.krupp.rabbit.controller;

import com.krupp.rabbit.config.RabbitConfig;
import com.krupp.rabbit.entity.Book;
import com.krupp.rabbit.producer.RabbitOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @PostMapping("add")
    public void defaultMessage() {
        Book book = new Book();
        book.setId("1");
        book.setName("一起来学RabbitMQ");
        rabbitOrderSender.sendSimpleMessage(null, book, "1", RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY);
    }


}
