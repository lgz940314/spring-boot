package com.krupp.rabbit.config;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/2
 */
public class RabbitConfig {

    /**
     * queue 配置
     */
    public static final String QUEUE_NAME = "spring.boot.bean.queue";
    public static final String QUEUE_DURABLE = "true";

    /**
     * EXCHANGE配置
     * 交换机，生产者生产的消息先经过交换机，再路由到一个或多个Queue，这个过程通过binding key完成
     */
    public static final String EXCHANGE_NAME = "spring.boot.bean.exchange";

    /**
     * fanout：会把所有发到Exchange的消息路由到所有和它绑定的Queue
     * direct：会把消息路由到routing key和binding key完全相同的Queue，不相同的丢弃
     * topic：direct是严格匹配，那么topic就算模糊匹配，routing key和binding key都用.来区分单词串，比如A.B.C，匹配任意单词，#匹配任意多个或0个单词，比如。B.*可以匹配到A.B.C
     * headers：不依赖routing key和binding key，通过对比消息属性中的headers属性，对比Exchange和Queue绑定时指定的键值对，相同就路由过来
     */
    public static final String EXCHANGE_TYPE = "direct";

    /**
     * routing key
     */
    public static final String ROUTING_KEY = "springboot.bean";
}
