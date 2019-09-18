package com.krupp.springbootelasticsearch.controller;

import com.krupp.springbootelasticsearch.bean.BookBean;
import com.krupp.springbootelasticsearch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/18
 */
@RestController
@RequestMapping("elasticController")
public class ElasticController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/save")
    @ResponseBody
    public void Save(){
        BookBean book=new BookBean();
        book.setId("1");
        book.setAuthor("lgz");
        book.setTitle("ES入门教程");
        book.setPostDate("2019-09-18");
        System.out.println(book);
        bookRepository.save(book);
    }

    @RequestMapping("/saves")
    @ResponseBody
    public void Saves(){
        ArrayList<BookBean> arrayList = new ArrayList();
        for (int i = 2; i < 20 ; i++) {
            BookBean book=new BookBean();
            book.setId(i+"");
            book.setAuthor("作者"+i);
            book.setTitle("标题"+i);
            book.setPostDate("2019-09-18");
            arrayList.add(book);
        }
        bookRepository.saveAll(arrayList);
    }

    @RequestMapping("/find")
    @ResponseBody
    public HashMap<Object, Object> find(){
        Optional<BookBean> optional = bookRepository.findById("1");
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        List<BookBean> list = (List<BookBean>) bookRepository.findAllById(arrayList);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("optional",optional);
        objectObjectHashMap.put("list",list);
        return objectObjectHashMap;
    }

}