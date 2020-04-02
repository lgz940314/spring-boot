package com.krupp.demo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/17
 */
@Data
@Accessors(chain = true)
public class UserLog {
    private String username;
    private String userid;
    private String state;
}
