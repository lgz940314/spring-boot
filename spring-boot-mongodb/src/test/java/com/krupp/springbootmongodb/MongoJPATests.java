package com.krupp.springbootmongodb;

import com.krupp.springbootmongodb.bean.Programmer;
import com.krupp.springbootmongodb.repository.ProgrammerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoJPATests {

    @Autowired
    private ProgrammerRepository repository;

    @Test
    public void insert() {
        // 单条插入
        repository.save(new Programmer("python", 23, 21832.34f, new Date()));
        // 批量插入
        List<Programmer> programmers = new ArrayList<Programmer>();
        programmers.add(new Programmer("java", 21, 52200.21f, new Date()));
        programmers.add(new Programmer("Go", 34, 500.21f, new Date()));
        repository.saveAll(programmers);
    }

    // 条件查询
    @Test
    public void select() {
        Programmer java = repository.findByNameAndAge("java", 21);
        Assert.assertEquals(java.getSalary(), 52200.21f, 0.01);
    }


    // 更新数据
    @Test
    public void MUpdate() {
        repository.save(new Programmer("Go", 8, 500.21f, new Date()));
        Programmer go = repository.findAllByName("Go");
        Assert.assertEquals(go.getAge(), 8);
    }

    // 删除指定数据
    @Test
    public void delete() {
        repository.deleteAllByName("python");
        Optional<Programmer> python = repository.findById("python");
        Assert.assertFalse(python.isPresent());
    }

}

