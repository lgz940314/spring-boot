package com.krupp.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/2
 * 发布
 */
@Component
public class RabbitOrderSender {

    private static final Logger log = LoggerFactory.getLogger(RabbitOrderSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息方法调用: 构建自定义对象消息
    public void sendSimpleMessage(Map<String, Object> headers, Object message, String messageId, String exchangeName, String key) {
        // 自定义消息头
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        // 创建消息
        Message<Object> msg = MessageBuilder.createMessage(message, messageHeaders);

        // 确认的回调
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，
        // 确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange（交换器）中
        // 如果发送时候指定的交换器不存在 ack就是false 代表消息不可达
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //Confirm回调
            if (ack) {
                //如果confirm返回成功 进行下一步操作
                System.out.println("confirm确认");
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        });


        /* 消息失败的回调
         * 例如消息已经到达交换器上，但路由键匹配任何绑定到该交换器的队列，会触发这个回调，此时 replyText: NO_ROUTE
         */
        rabbitTemplate.setReturnCallback((message1, replyCode, replyText, exchange, routingKey) -> {
            //log.info("message:{}; replyCode: {}; replyText: {} ; exchange:{} ; routingKey:{}",message1, replyCode, replyText, exchange, routingKey);
        });

        // 在实际中ID 应该是全局唯一 能够唯一标识消息 消息不可达的时候
        // 触发ConfirmCallback回调方法时可以获取该值，进行对应的错误处理
        CorrelationData correlationData = new CorrelationData(messageId);

        rabbitTemplate.convertAndSend(exchangeName, key, msg, correlationData);
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }
}
