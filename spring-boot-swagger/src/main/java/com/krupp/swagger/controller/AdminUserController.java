package com.krupp.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/10/21
 */
@Api(value = "AdminUserController ")
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    /**
     * 用户登录
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName, String password){
       if ("admin".equals(userName)&&"123456".equals(password)){
           return "成功";
       }
       return "失败";
    }
}
