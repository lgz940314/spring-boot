package com.krupp.mybatis.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "sys_role")
public class Role {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型
     */
    private String type;
}