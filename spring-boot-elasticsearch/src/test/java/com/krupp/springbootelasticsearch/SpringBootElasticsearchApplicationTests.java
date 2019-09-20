package com.krupp.springbootelasticsearch;

import com.krupp.springbootelasticsearch.bean.City;
import com.krupp.springbootelasticsearch.mapper.CityMapper;
import com.krupp.springbootelasticsearch.repository.CityRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.krupp.springbootelasticsearch.mapper")
public class SpringBootElasticsearchApplicationTests {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;

    @Test
    public void saveAll() {
        List<City> cities = cityMapper.selectAll();
        /*for (City city: cities) {
            cityRepository.save(city);
        }*/
        //批量保存
        cityRepository.saveAll(cities);
    }


    @Test
    public void delectAll() {
        //批量删除
        cityRepository.deleteAll();
    }

    @Test
    public void select() {
//        Iterable<City> cities = cityRepository.findAll();
//        for (City citie:cities) {
//            System.out.println(citie.toString());
//        }
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Iterable<City> allById = cityRepository.findAllById(ids);
        Optional<City> byId = cityRepository.findById(1);
        System.out.println("allById:"+allById);
        System.out.println("byId:"+byId);
    }

    /**
     * 使用QueryBuilder
     */
    @Test
    public void testQueryBuilder() {
        //termQuery("name", obj) 完全匹配,查询中文为空失败 需要在name上加.keyword
        QueryBuilder queryBuilder1 = QueryBuilders.termQuery("cityName.keyword", "上海市");
        //termsQuery("name", obj1, obj2..)   一次匹配多个值
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("cityName.keyword", "北京市", "天津市");
        // matchQuery("name", Obj) 单个匹配, field不支持通配符, 前缀具高级特性,相当于中文分词，数字不分词
        QueryBuilder queryBuilder3 = QueryBuilders.matchQuery("cityName", "北京");
        // multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行，中文无效果
        QueryBuilder queryBuilder4 = QueryBuilders.multiMatchQuery("cityName", "北京市", "上海市");
        //matchAllQuery();        匹配所有文件
        QueryBuilder queryBuilder5 = QueryBuilders.matchAllQuery();
        Iterable<City> search = cityRepository.search(queryBuilder5);
        int a = 0;
        for (City city:search) {
            a++;
            System.out.println(city.toString());
        }
        System.out.println(a);
    }

    /**
     * 组合查询
     * must(QueryBuilders) :   AND
     * mustNot(QueryBuilders): NOT
     * should:                  : OR
     */
    @Test
    public void testQueryBuilder2() {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must( QueryBuilders.termsQuery("cityName.keyword", "北京市", "天津市"))
                .mustNot(QueryBuilders.termQuery("cityCode", "110100"))
                .should(QueryBuilders.termQuery("cityCode", "652700"));
        System.out.println(queryBuilder.toString());
        Iterable<City> search = cityRepository.search(queryBuilder);
        int a = 0;
        for (City city:search) {
            a++;
            System.out.println(city.toString());
        }
        System.out.println(a);
    }


}
