package com.krupp.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bs_street")
public class Street {
    /**
     * 自增列
     */
    @Id
    @Column(name = "STREET_ID")
    private Integer streetId;

    /**
     * 街道代码
     */
    @Column(name = "STREET_CODE")
    private String streetCode;

    /**
     * 父级区代码
     */
    @Column(name = "AREA_CODE")
    private String areaCode;

    /**
     * 街道名称
     */
    @Column(name = "STREET_NAME")
    private String streetName;

    /**
     * 简称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;

    /**
     * 经度
     */
    @Column(name = "LNG")
    private String lng;

    /**
     * 纬度
     */
    @Column(name = "LAT")
    private String lat;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 创建时间
     */
    @Column(name = "GMT_CREATE")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "GMT_MODIFIED")
    private Date gmtModified;

    /**
     * 备注
     */
    @Column(name = "MEMO")
    private String memo;

    /**
     * 状态
     */
    @Column(name = "DATA_STATE")
    private Integer dataState;

    /**
     * 租户ID
     */
    @Column(name = "TENANT_CODE")
    private String tenantCode;
}