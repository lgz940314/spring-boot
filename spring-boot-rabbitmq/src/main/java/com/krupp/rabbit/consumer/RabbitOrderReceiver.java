package com.krupp.rabbit.consumer;

import com.krupp.rabbit.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/2
 * 消费
 */
@Component
public class RabbitOrderReceiver {

    private static final Logger log = LoggerFactory.getLogger(RabbitOrderReceiver.class);

    //配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.QUEUE_NAME, durable = RabbitConfig.QUEUE_DURABLE),
            exchange = @Exchange(value = RabbitConfig.EXCHANGE_NAME, type = RabbitConfig.EXCHANGE_TYPE),
            key = RabbitConfig.ROUTING_KEY))

    /**
     * 如果有消息过来，在消费的时候调用这个方法
     */
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {

        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now());

        // 获取消息头信息和消息体
        MessageHeaders headers = message.getHeaders();

        log.info("msgInfo:{} ; payload:{} ", headers.get("msgInfo"), message.getPayload());

        /**
         * DELIVERY_TAG 代表 RabbitMQ 向该Channel投递的这条消息的唯一标识ID，是一个单调递增的正整数。
         * RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /**
         *  是否一次签收多条
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        boolean multiple = false;

        //ACK确认
        channel.basicAck(deliveryTag, multiple);
    }
}
