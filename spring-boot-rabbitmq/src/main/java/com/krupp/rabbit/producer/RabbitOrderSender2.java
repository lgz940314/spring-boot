package com.krupp.rabbit.producer;

import com.krupp.rabbit.entity.Book;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/2
 * 发布
 */
@Component
public class RabbitOrderSender2 {

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if (ack) {
                //如果confirm返回成功 则进行更新

            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息方法调用: 构建自定义对象消息
    public void sendOrder(Book book) throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(book.getId());
        rabbitTemplate.convertAndSend("exchange", "routingKey", book, correlationData);
    }
}