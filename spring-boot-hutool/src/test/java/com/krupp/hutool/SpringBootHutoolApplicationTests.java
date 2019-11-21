package com.krupp.hutool;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.krupp.hutool.bean.Book;
import com.krupp.hutool.test.SnowflakeIdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootHutoolApplicationTests {

    @Test
    public void contextLoads() {
        final String text = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\t<taskInfo>\n" +
                "\t\t<taskNo>lhjg111</taskNo>\n" +
                "\t\t<taskName>测试任务</taskName>\n" +
                "\t\t<taskStartTime>2019-08-02</taskStartTime>\n" +
                "\t\t<initiateUnitNo>110000</initiateUnitNo>\n" +
                "\t\t<initiateUnitName>XXX部</initiateUnitName>\n" +
                "\t\t<initiateUnitType>dept</initiateUnitType>\n" +
                "\t\t<unionDeptNo>110010</unionDeptNo>\n" +
                "\t\t<unionDeptName>卫健委</unionDeptName>\n" +
                "\t\t<unionRegionNo>110020</unionRegionNo>\n" +
                "\t\t<unionRegionName>海淀区</unionRegionName>\n" +
                "\t\t<supervisionMatterCode>监管事项目录编码</supervisionMatterCode>\n" +
                "\t\t<superviseObjectNo>监管对象行业分类编码</superviseObjectNo>\n" +
                "\t\t<inspectContent>检查的主要内容</inspectContent>\n" +
                "\t\t<inspectClaim>检查要求</inspectClaim>\n" +
                "\t\t<remark>备注</remark>\n" +
                "\t\t<superviseObjects>\n" +
                "\t\t\t<superviseObject>\n" +
                "\t\t\t\t<superviseObjectType>监管对象类型1</superviseObjectType>\n" +
                "\t\t\t\t<superviseObjectName>监管对象名称1</superviseObjectName>\n" +
                "\t\t\t\t<superviseObjectCode>监管对象标识1</superviseObjectCode>\n" +
                "\t\t\t\t<credentialsType>证件类型1</credentialsType>\n" +
                "\t\t\t\t<credentialsValue>证件内容1</credentialsValue>\n" +
                "\t\t\t</superviseObject>\n" +
                "\t\t\t<superviseObject>\n" +
                "\t\t\t\t<superviseObjectType>监管对象类型2</superviseObjectType>\n" +
                "\t\t\t\t<superviseObjectName>监管对象名称2</superviseObjectName>\n" +
                "\t\t\t\t<superviseObjectCode>监管对象标识2</superviseObjectCode>\n" +
                "\t\t\t\t<credentialsType>证件类型2</credentialsType>\n" +
                "\t\t\t\t<credentialsValue>证件内容2</credentialsValue>\n" +
                "\t\t\t</superviseObject>\n" +
                "\t\t</superviseObjects>\n" +
                "\t</taskInfo>\n" +
                "\t\n";
        JSONObject jsonObject = JSONUtil.xmlToJson(text);
       // System.out.println(jsonObject.toString());
        String s = JSONUtil.toXmlStr(jsonObject);
       // System.out.println(s);
        Book book = new Book();
        book.setId("1");
        book.setTitle("一本很好的书");
        book.setPrice("15");
        JSONObject obj = JSONUtil.createObj();
        obj.put("book",book);
        System.out.println(JSONUtil.toXmlStr(obj));
    }

    @Test
    public void executor(){
        ExecutorService executorService = ThreadUtil.newExecutor();
        for (int i = 1; i <= 1000; i++) {
            int sum = (int) Math.sqrt(i * i - 1 + i);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"------------"+sum);
                }
                Integer i =null;
            });
        }
    }

    @Test
    public void test(){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        ExecutorService executorService = ThreadUtil.newExecutor(5);
        for (int i = 1; i <= 60000; i++) {
            int sum = (int) Math.sqrt(i * i - 1 + i);
            long id = idWorker.nextId();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"========================>"+sum+"------------"+id);
                }
            });
        }
    }

    @Test
    public void test2(){
    }

}
