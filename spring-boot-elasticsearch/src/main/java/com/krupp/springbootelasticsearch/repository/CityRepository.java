package com.krupp.springbootelasticsearch.repository;


import com.krupp.springbootelasticsearch.bean.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/20
 */
public interface CityRepository extends ElasticsearchRepository<City, Integer> {
}
