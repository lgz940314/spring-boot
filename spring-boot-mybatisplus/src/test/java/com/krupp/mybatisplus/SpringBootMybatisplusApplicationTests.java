package com.krupp.mybatisplus;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.krupp.mybatisplus.common.entity.User;
import com.krupp.mybatisplus.common.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootMybatisplusApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void save() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setAge(i);
            user.setName("Tom"+i);
            userServiceImpl.save(user);
        }

    }

    /**
     * 条件查询
     */
    @Test
    void list() {
        //List<User> list = userServiceImpl.list();
        HashMap<String, Object> alleq = new HashMap<>();
        alleq.put("age","21");
        alleq.put("name","tom21");
        List<User> list = userServiceImpl.list(new QueryWrapper<User>()
                .eq("age", 20)
                //.allEq(alleq)
                .select( "name", "age")
        );
        System.out.println(list.toString());
    }

    /**
     * 分页
     */
    @Test
    void page() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = userServiceImpl.page(page, new QueryWrapper<User>()
                .eq("age", 20));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        /*print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");

        System.out.println("json 正反序列化 begin");
        String json = JSON.toJSONString(page);
        Page<User> page1 = JSON.parseObject(json, Page.class);
        print(page1.getRecords());
        System.out.println("json 正反序列化 end");

        System.out.println("----- 自定义 XML 分页 ------");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome(20, "Jack");
        MyPage<User> userMyPage = mapper.mySelectPage(myPage, paramSome);
        assertThat(myPage).isSameAs(userMyPage);
        System.out.println("总条数 ------> " + userMyPage.getTotal());
        System.out.println("当前页数 ------> " + userMyPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userMyPage.getSize());
        print(userMyPage.getRecords());
        System.out.println("----- 自定义 XML 分页 ------");*/
    }


}
