package com.krupp.mybatis;

import com.krupp.mybatis.entity.Street;
import com.krupp.mybatis.mapper.StreetMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.krupp.mybatis.mapper")
public class MybatisApplicationTests {

    @Autowired
    private StreetMapper streetMapper;

    @Test
    public void contextLoads() {
        List<Street> list = streetMapper.selectAll();
        System.out.println(list.toString());
    }





}
