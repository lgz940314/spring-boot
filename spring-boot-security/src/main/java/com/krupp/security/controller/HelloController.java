package com.krupp.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/11/20
 */
@RestController
@RequestMapping("demo")
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String index() {
        /*
        Spring Boot 项目引入了 Spring Security 以后，自动装配了 Spring Security 的环境
        Spring Security 的默认配置是要求经过了 HTTP Basic 认证成功后才可以访问到 URL 对应的资源
        且默认的用户名是 user，密码则是一串 UUID 字符串，输出到了控制台日志里
        */
        return "Welcome to learn Spring Security!";
    }

}
