package com.krupp.sources.controller;

import com.krupp.sources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/11
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    @Qualifier(value = "primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    @Autowired
    @Qualifier(value = "secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;

    @RequestMapping("/demo")
    public void demo(){
        primaryMongoTemplate.insert(new User("1","燎原火",18,"rrefe4@163.com"));
        secondaryMongoTemplate.insert(new User("2","山无陵",18,"rrefe4@163.com"));
    }

}
