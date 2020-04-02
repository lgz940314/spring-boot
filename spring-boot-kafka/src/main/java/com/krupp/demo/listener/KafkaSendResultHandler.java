package com.krupp.demo.listener;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/17
 */
@Component
public class KafkaSendResultHandler implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println("消息发送成功");
        //成功时你的处理
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        System.out.println("消息发送失败");
        //失败时你的处理
    }
}
