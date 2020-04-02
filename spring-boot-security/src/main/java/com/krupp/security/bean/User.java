package com.krupp.security.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/11/20
 */
@Data
@Builder
public class TSysUser {

    private String username;

    private String password;


}
