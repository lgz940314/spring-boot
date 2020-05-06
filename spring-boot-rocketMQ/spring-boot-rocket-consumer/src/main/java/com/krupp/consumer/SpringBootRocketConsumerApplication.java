package com.krupp.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootRocketConsumerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootRocketConsumerApplication.class, args);
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mygroup");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("106.13.170.34:9876");
        //3.订阅主题Topic和Tag
        consumer.subscribe("OrderTopic", "Order");


        //4.注册消息监听器

        //负载均衡模式(默认)
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                System.out.println(list);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });


        //顺序消费模式
        consumer.registerMessageListener(new MessageListenerOrderly() {
            /**
             * 1. 消息消费处理失败或者处理出现异常，返回 OrderAction.Suspend<br>
             * 2. 消息处理成功，返回 OrderAction.Success
             */
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("线程名称：【" + Thread.currentThread().getName() + "】:" + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        //5.启动消费者
        consumer.start();

        System.out.println("消费者启动");

    }

}
