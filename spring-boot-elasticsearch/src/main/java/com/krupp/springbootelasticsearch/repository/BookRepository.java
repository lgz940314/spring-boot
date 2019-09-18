package com.krupp.springbootelasticsearch.repository;

import com.krupp.springbootelasticsearch.bean.BookBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/18
 */
public interface BookRepository extends ElasticsearchRepository<BookBean, String> {

}