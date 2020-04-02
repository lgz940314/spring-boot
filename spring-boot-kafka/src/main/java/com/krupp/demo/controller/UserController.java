package com.krupp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/17
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("test")
    public void test(){
        kafkaTemplate.send("user-log","这是一条测试数据");
    }

}
