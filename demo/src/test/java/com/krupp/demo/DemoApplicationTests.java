package com.krupp.demo;

import com.alibaba.fastjson.JSONObject;
import com.krupp.demo.ioc.Person;
import com.krupp.demo.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext(
                "/beans.xml");
        System.out.println("容器初始化成功");
        // 得到Preson，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }

    @Test
    public void toxml() throws Exception {
        String json="{\"taskInfo\":{\"taskNo\":\"lhjg111\",\"taskName\":\"测试任务\",\"taskStartTime\":\"2019-08-02\",\"initiateUnitNo\":\"110000\",\"initiateUnitName\":\"XXX部\",\"initiateUnitType\":\"dept\",\"unionDeptNo\":\"110010\",\"unionDeptName\":\"卫健委\",\"unionRegionNo\":\"110020\",\"unionRegionName\":\"海淀区\",\"supervisionMatterCode\":\"监管事项目录编码\",\"superviseObjectNo\":\"监管对象行业分类编码\",\"inspectContent\":\"检查的主要内容\",\"inspectClaim\":\"检查要求\",\"remark\":\"备注\",\"superviseObjects\":{\"superviseObject\":[{\"superviseObjectType\":\"监管对象类型1\",\"superviseObjectName\":\"监管对象名称1\",\"superviseObjectCode\":\"监管对象标识1\",\"credentialsType\":\"证件类型1\",\"credentialsValue\":\"证件内容1\"},{\"superviseObjectType\":\"监管对象类型2\",\"superviseObjectName\":\"监管对象名称2\",\"superviseObjectCode\":\"监管对象标识2\",\"credentialsType\":\"证件类型2\",\"credentialsValue\":\"证件内容2\"}]}}}";
        Document document = DocumentHelper.createDocument();
        Element root =  document.addElement("taskInfo"); //默认根节点
        Element element = XmlUtils.jsonToXml(json, root);
        String s = element.asXML();
        System.out.println("asXML:"+element.asXML());
        System.out.println("toString:"+element.toString());
    }
    @Test
    public void ces() throws Exception {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
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
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        //System.out.println("编码:"+encodedText);
        //解码
        //System.out.println("解码:"+new String(decoder.decode(encodedText), "UTF-8"));
        String s = new String(decoder.decode("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPHN1cGVydmlzZURhdGE+Cgk8dGFza0luZm8+CgkJPHRhc2tObz5saGpnMTExPC90YXNrTm8+CgkJPHRhc2tOYW1lPua1i+ivleS7u+WKoTwvdGFza05hbWU+CgkJPHRhc2tTdGFydFRpbWU+MjAxOS0wOC0wMjwvdGFza1N0YXJ0VGltZT4KCQk8c3RhdHVzPuWIhuWPkS/orqTpooYv57uT5p2fL+WPjemmiDwvc3RhdHVzPgoJCTxpbml0aWF0ZVVuaXRObz48L2luaXRpYXRlVW5pdE5vPgoJCTxpbml0aWF0ZVVuaXROYW1lPjwvaW5pdGlhdGVVbml0TmFtZT4KCQk8aW5pdGlhdGVVbml0VHlwZT48L2luaXRpYXRlVW5pdFR5cGU+CgkJPHVuaW9uRGVwdE5vPjwvdW5pb25EZXB0Tm8+CgkJPHVuaW9uRGVwdE5hbWU+PC91bmlvbkRlcHROYW1lPgoJCTx1bmlvblJlZ2lvbk5vPjwvdW5pb25SZWdpb25Obz4KCQk8dW5pb25SZWdpb25OYW1lPjwvdW5pb25SZWdpb25OYW1lPgoJCTxzdXBlcnZpc2lvbk1hdHRlckNvZGU+PC9zdXBlcnZpc2lvbk1hdHRlckNvZGU+CgkJPHN1cGVydmlzZU9iamVjdE5vPjwvc3VwZXJ2aXNlT2JqZWN0Tm8+CgkJPGluc3BlY3RDb250ZW50PjwvaW5zcGVjdENvbnRlbnQ+CgkJPGluc3BlY3RDbGFpbT48L2luc3BlY3RDbGFpbT4KCQk8cmVtYXJrPjwvcmVtYXJrPgoJCTxzdXBlcnZpc2VPYmplY3RzPgoJCQk8c3VwZXJ2aXNlT2JqZWN0PgoJCQkJPHN1cGVydmlzZU9iamVjdFR5cGU+PC9zdXBlcnZpc2VPYmplY3RUeXBlPgoJCQkJPHN1cGVydmlzZU9iamVjdE5hbWU+PC9zdXBlcnZpc2VPYmplY3ROYW1lPgoJCQkJPHN1cGVydmlzZU9iamVjdENvZGU+PC9zdXBlcnZpc2VPYmplY3RDb2RlPgoJCQkJPGNyZWRlbnRpYWxzVHlwZT48L2NyZWRlbnRpYWxzVHlwZT4KCQkJCTxjcmVkZW50aWFsc1ZhbHVlPjwvY3JlZGVudGlhbHNWYWx1ZT4KCQkJCTxiYXNpc0RhdGE+CgkJCQkJPGNyZWRpdEFzc2Vzcz4KCQkJCQkJPGVudGVycHJpc2VOYW1lPjwvZW50ZXJwcmlzZU5hbWU+CgkJCQkJCTxlbnRlcnByaXNlSWRUeXBlPjwvZW50ZXJwcmlzZUlkVHlwZT4KCQkJCQkJPGVudGVycHJpc2VDcmVkaXRDb2RlPjwvZW50ZXJwcmlzZUNyZWRpdENvZGU+CgkJCQkJCTxlbnRlcnByaXNlTWFyaz48L2VudGVycHJpc2VNYXJrPgoJCQkJCQk8ZW50ZXJwcmlzZVR5cGU+PC9lbnRlcnByaXNlVHlwZT4KCQkJCQkJPGVzdGltYXRlVGltZT48L2VzdGltYXRlVGltZT4KCQkJCQk8L2NyZWRpdEFzc2Vzcz4KCQkJCQk8b3BlcmF0aW5nQWJub3JtYWw+CgkJCQkJCTxhYm5vcm1hbFB1dGRhdGU+PC9hYm5vcm1hbFB1dGRhdGU+CgkJCQkJCTxlbnRlcnByaXNlTmFtZT48L2VudGVycHJpc2VOYW1lPgoJCQkJCQk8ZW50ZXJwcmlzZUNyZWRpdENvZGU+PC9lbnRlcnByaXNlQ3JlZGl0Q29kZT4KCQkJCQkJPGFibm9ybWFsUHV0cnM+PC9hYm5vcm1hbFB1dHJzPgoJCQkJCQk8YWJub3JtYWxQdXRkcHRtPjwvYWJub3JtYWxQdXRkcHRtPgoJCQkJCQk8YWJub3JtYWxMaXN0RGF0ZT48L2Fibm9ybWFsTGlzdERhdGU+CgkJCQkJCTxhYm5vcm1hbExpc3RScmVhc29ucz48L2Fibm9ybWFsTGlzdFJyZWFzb25zPgoJCQkJCQk8YWJub3JtYWxMaXN0UHV0ZHB0bT48L2Fibm9ybWFsTGlzdFB1dGRwdG0+CgkJCQkJPC9vcGVyYXRpbmdBYm5vcm1hbD4KCQkJCQk8c2VyaW91c0lsbGVnYWw+CgkJCQkJCTxpbGxlZ2FsUHV0ZGF0ZT48L2lsbGVnYWxQdXRkYXRlPgoJCQkJCQk8ZW50ZXJwcmlzZU5hbWU+PC9lbnRlcnByaXNlTmFtZT4KCQkJCQkJPGVudGVycHJpc2VDcmVkaXRDb2RlPjwvZW50ZXJwcmlzZUNyZWRpdENvZGU+CgkJCQkJCTxpbGxlZ2FsUmVhc29uPjwvaWxsZWdhbFJlYXNvbj4KCQkJCQkJPGlsbGVnYWxEZXBhcnRtZW50PjwvaWxsZWdhbERlcGFydG1lbnQ+CgkJCQkJCTxpbGxlZ2FsTGlzdERhdGU+PC9pbGxlZ2FsTGlzdERhdGU+CgkJCQkJCTxpbGxlZ2FsTGlzdFJyZWFzb25zPjwvaWxsZWdhbExpc3RScmVhc29ucz4KCQkJCQkJPGlsbGVnYWxMaXN0UHV0ZHB0bT48L2lsbGVnYWxMaXN0UHV0ZHB0bT4JCgkJCQkJPC9zZXJpb3VzSWxsZWdhbD4KCQkJCQk8Y29tcGxhaW50SW5mbz4KCQkJCQkJPGNvbXBsYWludE9iamVjdD48L2NvbXBsYWludE9iamVjdD4KCQkJCQkJPGNvbXBsYWludE9iamVjdENlcnRDb2RlPjwvY29tcGxhaW50T2JqZWN0Q2VydENvZGU+CgkJCQkJCTxjb21wbGFpbnRUeXBlPjwvY29tcGxhaW50VHlwZT4KCQkJCQkJPGNvbXBsYWludFRlcnJpdG9yeT48L2NvbXBsYWludFRlcnJpdG9yeT4KCQkJCQkJPHNlcnZpY2VUeXBlPjwvc2VydmljZVR5cGU+CgkJCQkJCTxzZXJ2aWNlTmFtZT48L3NlcnZpY2VOYW1lPgoJCQkJCQk8ZGlzcHV0ZURhdGU+PC9kaXNwdXRlRGF0ZT4KCQkJCQkJPGNvbXBsYWludERldGFpbD48L2NvbXBsYWludERldGFpbD4KCQkJCQk8L2NvbXBsYWludEluZm8+CgkJCQkJPHRpcEluZm8+CgkJCQkJCTx0aXBPYmplY3Q+PC90aXBPYmplY3Q+CgkJCQkJCTxlbnRlcnByaXNlQ3JlZGl0Q29kZT48L2VudGVycHJpc2VDcmVkaXRDb2RlPgoJCQkJCQk8dGlwVHlwZT48L3RpcFR5cGU+CgkJCQkJCTx0aXBUZXJyaXRvcnk+PC90aXBUZXJyaXRvcnk+CgkJCQkJCTxzZXJ2aWNlVHlwZT48L3NlcnZpY2VUeXBlPgoJCQkJCQk8c2VydmljZU5hbWU+PC9zZXJ2aWNlTmFtZT4KCQkJCQkJPGRpc3B1dGVEYXRlPjwvZGlzcHV0ZURhdGU+CgkJCQkJCTx0aXBEZXRhaWw+PC90aXBEZXRhaWw+CgkJCQkJPC90aXBJbmZvPgoJCQkJCTxyaXNrV2FybmluZz4KCQkJCQkJPHJpc2tPYmplY3Q+PC9yaXNrT2JqZWN0PgoJCQkJCQk8cmlza09iamVjdENlcnRDb2RlPjwvcmlza09iamVjdENlcnRDb2RlPgoJCQkJCQk8Znh5aklkPjwvZnh5aklkPgoJCQkJCQk8ZG9tYWluPjwvZG9tYWluPgoJCQkJCQk8cHJvdmluY2U+PC9wcm92aW5jZT4KCQkJCQkJPHRpdGxlPjwvdGl0bGU+CgkJCQkJCTxsZXZlbD48L2xldmVsPgoJCQkJCQk8Y3JlYXRlZEF0PjwvY3JlYXRlZEF0PgoJCQkJCQk8c291cmNlPjwvc291cmNlPgoJCQkJCQk8cnVsZUlkPjwvcnVsZUlkPgoJCQkJCQk8cHJvcHM+PC9wcm9wcz4JCgkJCQkJPC9yaXNrV2FybmluZz4KCQkJCTwvYmFzaXNEYXRhPgoJCQk8L3N1cGVydmlzZU9iamVjdD4KCQk8L3N1cGVydmlzZU9iamVjdHM+Cgk8L3Rhc2tJbmZvPgoJPHN1cGVydmlzaW9uUHJvY2Vzcz4KCQk8dGFza0RldGFpbHM+CgkJCTx0YXNrTm8+PC90YXNrTm8+CgkJCTx1bmlvblVuaXRUeXBlPjwvdW5pb25Vbml0VHlwZT4KCQkJPHVuaW9uVW5pdE5vPjwvdW5pb25Vbml0Tm8+CgkJCTx1bmlvblVuaXROYW1lPjwvdW5pb25Vbml0TmFtZT4KCQkJPHN0YXR1cz48L3N0YXR1cz4KCQkJPGNsYWltVGltZT48L2NsYWltVGltZT4KCQkJPHN1cGVydmlzaW9uTWF0dGVyQ29kZT48L3N1cGVydmlzaW9uTWF0dGVyQ29kZT4KCQkJPGVuZFRpbWU+PC9lbmRUaW1lPgoJCQk8ZW5kUmVhc29uPjwvZW5kUmVhc29uPgoJCQk8ZmVlZGJhY2tUaW1lPjwvZmVlZGJhY2tUaW1lPgoJCQk8ZmVlZGJhY2tTdWdnZXN0aW9uPjwvZmVlZGJhY2tTdWdnZXN0aW9uPgkKCQkJPHRhc2tObz48L3Rhc2tObz4KCQkJPHVuaW9uVW5pdFR5cGU+PC91bmlvblVuaXRUeXBlPgoJCQk8dW5pb25Vbml0Tm8+PC91bmlvblVuaXRObz4KCQkJPHVuaW9uVW5pdE5hbWU+PC91bmlvblVuaXROYW1lPgoJCQk8c3RhdHVzPjwvc3RhdHVzPgoJCQk8Y2xhaW1UaW1lPjwvY2xhaW1UaW1lPgoJCQk8c3VwZXJ2aXNpb25NYXR0ZXJDb2RlPjwvc3VwZXJ2aXNpb25NYXR0ZXJDb2RlPgoJCQk8ZW5kVGltZT48L2VuZFRpbWU+CgkJCTxlbmRSZWFzb24+PC9lbmRSZWFzb24+CgkJCTxmZWVkYmFja1RpbWU+PC9mZWVkYmFja1RpbWU+CgkJCTxmZWVkYmFja1N1Z2dlc3Rpb24+PC9mZWVkYmFja1N1Z2dlc3Rpb24+CQoJCTwvdGFza0RldGFpbHM+CgkJPGZlZWRiYWNrVW5pdEluZm8+CgkJCTxhZG1JbnNwZWN0aW9uPgoJCQkJPHN1cGVydmlzaW9uTWF0dGVyQ29kZT48L3N1cGVydmlzaW9uTWF0dGVyQ29kZT4KCQkJCTxjaGVja0FjdGlvbk5hbWU+PC9jaGVja0FjdGlvbk5hbWU+CgkJCQk8Y2hlY2tBY3Rpb25Db2RlPjwvY2hlY2tBY3Rpb25Db2RlPgoJCQkJPGltcGxlbWVudEluc3RpdHV0aW9uPjwvaW1wbGVtZW50SW5zdGl0dXRpb24+CgkJCQk8aW1wbGVtZW50SW5zdGl0dXRpb25Db2RlPjwvaW1wbGVtZW50SW5zdGl0dXRpb25Db2RlPgoJCQkJPHN1cGVydmlzZU9iamVjdENvZGU+PC9zdXBlcnZpc2VPYmplY3RDb2RlPgoJCQkJPHN1cGVydmlzZU9iamVjdE5hbWU+PC9zdXBlcnZpc2VPYmplY3ROYW1lPgoJCQkJPGNoZWNrRm9ybT48L2NoZWNrRm9ybT4KCQkJCTxjaGVja1R5cGU+PC9jaGVja1R5cGU+CgkJCQk8Y2hlY2tNb2RlPjwvY2hlY2tNb2RlPgoJCQkJPGNoZWNrUmVzdWx0PjwvY2hlY2tSZXN1bHQ+CgkJCQk8Y2hlY2tEYXRlPjwvY2hlY2tEYXRlPgoJCQkJPGNoZWNrUGVyc29ubmVsPjwvY2hlY2tQZXJzb25uZWw+CgkJCTwvYWRtSW5zcGVjdGlvbj4KCQkJPGFkbVB1bmlzaG1lbnQ+CgkJCQk8c3VwZXJ2aXNpb25NYXR0ZXJDb2RlPjwvc3VwZXJ2aXNpb25NYXR0ZXJDb2RlPgoJCQkJPHB1bmlzaEFjdGlvbk5hbWU+PC9wdW5pc2hBY3Rpb25OYW1lPgoJCQkJPHN1cGVydmlzZU9iamVjdENvZGU+PC9zdXBlcnZpc2VPYmplY3RDb2RlPgoJCQkJPHN1cGVydmlzZU9iamVjdE5hbWU+PC9zdXBlcnZpc2VPYmplY3ROYW1lPgoJCQkJPGlsbGVnYWxGYWN0PjwvaWxsZWdhbEZhY3Q+CgkJCQk8cHVuaXNoQWNjb3JkPjwvcHVuaXNoQWNjb3JkPgoJCQkJPHB1bmlzaFByb2NlZHVyZT48L3B1bmlzaFByb2NlZHVyZT4KCQkJCTxwdW5pc2hEb2N1bWVudENvZGU+PC9wdW5pc2hEb2N1bWVudENvZGU+CgkJCQk8c2V0RGF0ZT48L3NldERhdGU+CgkJCQk8cGVuYWx0eVJlc3VsdD48L3BlbmFsdHlSZXN1bHQ+CgkJCQk8cHVuaXNoQWN0aW9uQ29kZT48L3B1bmlzaEFjdGlvbkNvZGU+CgkJCTwvYWRtUHVuaXNobWVudD4KCQkJPGFkbUZvcmNlPgoJCQkJPHN1cGVydmlzaW9uTWF0dGVyQ29kZT48L3N1cGVydmlzaW9uTWF0dGVyQ29kZT4KCQkJCTxmb3JjZUFjdGlvbk5hbWU+PC9mb3JjZUFjdGlvbk5hbWU+CgkJCQk8aWxsZWdhbEZhY3Q+PC9pbGxlZ2FsRmFjdD4KCQkJCTxmb3JjZVR5cGU+PC9mb3JjZVR5cGU+CgkJCQk8c3VwZXJ2aXNlT2JqZWN0Q29kZT48L3N1cGVydmlzZU9iamVjdENvZGU+CgkJCQk8c3VwZXJ2aXNlT2JqZWN0TmFtZT48L3N1cGVydmlzZU9iamVjdE5hbWU+CgkJCQk8Y29lcmNpdmVNZWFzdXJlVHlwZT48L2NvZXJjaXZlTWVhc3VyZVR5cGU+CgkJCQk8Zm9yY2VFeGVjdXRpb25UeXBlPjwvZm9yY2VFeGVjdXRpb25UeXBlPgoJCQkJPGZvcmNlUmVzdWx0PjwvZm9yY2VSZXN1bHQ+CgkJCQk8bWFuZGF0b3J5RGVjaXNpb25TZXJ2aWNlRGF0ZT48L21hbmRhdG9yeURlY2lzaW9uU2VydmljZURhdGU+CgkJCQk8Zm9yY2VFeHRlbmREYXRlPjwvZm9yY2VFeHRlbmREYXRlPgkJCgkJCQk8Zm9yY2VBY3Rpb25Db2RlPjwvZm9yY2VBY3Rpb25Db2RlPgoJCQk8L2FkbUZvcmNlPgoJCQk8b3RoZXI+CgkgICAgICAgIAk8c3VwZXJ2aXNpb25NYXR0ZXJDb2RlPjwvc3VwZXJ2aXNpb25NYXR0ZXJDb2RlPgoJCQkJPGFjdGlvbk5hbWU+PC9hY3Rpb25OYW1lPgoJCQkJPGl0ZW1Tb3VyY2U+PC9pdGVtU291cmNlPgoJCQkJPHN1cGVydmlzZU1lYXN1cmU+PC9zdXBlcnZpc2VNZWFzdXJlPgoJCQkJPHN1cGVydmlzZU9iamVjdENvZGU+PC9zdXBlcnZpc2VPYmplY3RDb2RlPgoJCQkJPHN1cGVydmlzZU9iamVjdE5hbWU+PC9zdXBlcnZpc2VPYmplY3ROYW1lPgoJCQkJPGNsb3NpbmdDYXNlPjwvY2xvc2luZ0Nhc2U+CgkJCQk8c3RhcnREYXRlPjwvc3RhcnREYXRlPgoJCQkJPGVuZERhdGU+PC9lbmREYXRlPgoJCQkJPGFjdGlvbkNvZGU+PC9hY3Rpb25Db2RlPgoJCQk8L290aGVyPgoJCTwvZmVlZGJhY2tVbml0SW5mbz4KCTwvc3VwZXJ2aXNpb25Qcm9jZXNzPgo8L3N1cGVydmlzZURhdGE+Cg=="), "UTF-8");
        JSONObject jsonObject = XmlUtils.documentToJSONObject(s);
        System.out.println("jsonObject："+jsonObject.toString());
        Object taskInfo = jsonObject.get("taskInfo");
        System.out.println("taskInfo："+taskInfo.toString());
    }
}

