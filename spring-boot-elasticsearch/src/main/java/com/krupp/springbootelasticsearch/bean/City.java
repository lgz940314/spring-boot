package com.krupp.springbootelasticsearch.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bs_city")
@Document(indexName = "city", type = "_doc")
public class City {
    /**
     * 自增列
     */
    @Id
    @Column(name = "CITY_ID")
    private Integer cityId;

    /**
     * 市代码
     */
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * 市名称
     */
    @Column(name = "CITY_NAME")
    private String cityName;

    /**
     * 简称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;

    /**
     * 省代码
     */
    @Column(name = "PROVINCE_CODE")
    private String provinceCode;

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