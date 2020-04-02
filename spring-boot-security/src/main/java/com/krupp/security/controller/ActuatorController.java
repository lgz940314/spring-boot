package com.krupp.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/11/20
 */
@RequestMapping
@RestController("actuator")
public class ActuatorController {

    @ResponseBody
    @RequestMapping("hi")
    public String index() {
        System.out.println("Welcome to learn Spring Security!");
        return "Welcome to learn Spring Security!";

    }

}
