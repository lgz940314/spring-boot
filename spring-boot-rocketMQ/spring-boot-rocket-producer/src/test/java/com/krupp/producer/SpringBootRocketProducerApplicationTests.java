package com.krupp.producer;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootRocketProducerApplicationTests {

   /* @Value("${spring.rocketmq.name-server}")
    private String nameServer;
    @Value("${spring.rocketmq.topic}")
    private String topic;
    @Value("${spring.rocketmq.producer.group}")
    private String group;*/


    /**
     * 同步消息
     *
     * @throws Exception
     */
    @Test
    void SynchMessage() throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("106.13.170.34:9876");
        //3.启动producer
        producer.start();

        for (int i = 0; i < 10; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message msg = new Message("topic", "tag", i + "", ("Hello World" + i).getBytes());
            //5.发送消息
            SendResult result = producer.send(msg);
            //发送状态
            SendStatus status = result.getSendStatus();

            System.out.println(i + "========>发送结果:" + result);
            System.out.println(i + "========>发送状态:" + status);
            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }

        //6.关闭生产者producer
        producer.shutdown();
    }

    /**
     * 异步消息
     *
     * @throws Exception
     */
    @Test
    void AsyncMessage() throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("106.13.170.34:9876");
        //3.启动producer
        producer.start();

        for (int i = 0; i < 10; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message msg = new Message("topic", "tag2", ("Hello World" + i).getBytes());
            //5.发送异步消息
            producer.send(msg, new SendCallback() {
                /**
                 * 发送成功回调函数
                 *
                 * @param sendResult
                 */
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送结果：" + sendResult);
                }

                /**
                 * 发送失败回调函数
                 *
                 * @param e
                 */
                public void onException(Throwable e) {
                    System.out.println("发送异常：" + e);
                }
            });
            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //6.关闭生产者producer
        producer.shutdown();
    }


    /**
     * 单向发送消息
     *
     * @throws Exception
     */
    @Test
    void UnidirectionalMessage() throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("106.13.170.34:9876");
        //3.启动producer
        producer.start();

        for (int i = 0; i < 3; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message msg = new Message("topic", "tag3", ("Hello World，单向消息" + i).getBytes());
            //5.发送单向消息
            producer.sendOneway(msg);

            //线程睡1秒
            TimeUnit.SECONDS.sleep(5);
        }

        //6.关闭生产者producer
        producer.shutdown();
    }

    /**
     * 多线程发送消息
     *
     * @throws Exception
     */
    @Test
    void threadMessage() throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("106.13.170.34:9876");
        //3.启动producer
        producer.start();

        //创建的 Producer 和 Consumer 对象为线程安全的，可以在多线程间进行共享，避免每个线程创建一个实例。

        //在 thread 和 anotherThread 中共享 Producer 对象，并发地发送消息至消息队列 RocketMQ 版。
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message msg = new Message( //
                            // Message 所属的 Topic
                            "TopicTestMQ",
                            // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版的服务器过滤
                            "TagA",
                            // Message Body 可以是任何二进制形式的数据，消息队列 RocketMQ 版不做任何干预，
                            // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                            "Hello MQ".getBytes());
                    SendResult sendResult = producer.send(msg);
                    // 同步发送消息，只要不抛异常就是成功
                    if (sendResult != null) {
                        System.out.println(new Date() + " Send mq message success.msgId is: " + sendResult.getMsgId());
                    }
                } catch (Exception e) {
                    // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
                    System.out.println(new Date() + " Send mq message failed.");
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        Thread anotherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message msg = new Message("TopicTestMQ", "TagA", "Hello MQ".getBytes());
                    SendResult sendResult = producer.send(msg);
                    // 同步发送消息，只要不抛异常就是成功
                    if (sendResult != null) {
                        System.out.println(new Date() + " Send mq message success.msgId is: " + sendResult.getMsgId());
                    }
                } catch (Exception e) {
                    // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
                    System.out.println(new Date() + " Send mq message failed.");
                    e.printStackTrace();
                }
            }
        });
        anotherThread.start();

        //6.关闭生产者producer
        producer.shutdown();
    }

    /**
     * 发送顺序消息
     *
     * @throws Exception
     */
    @Test
    void orderMessage() throws Exception {


        //构建消息集合
        List<OrderStep> orderList = new ArrayList<OrderStep>();

        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("mygroup");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("106.13.170.34:9876");
        //3.启动producer
        producer.start();


        for (int i = 0; i < orderList.size(); i++) {
            String body = orderList.get(i) + "";
            Message message = new Message("OrderTopic", "Order", "i" + i, body.getBytes());
            /**
             * 参数一：消息对象
             * 参数二：消息队列的选择器
             * 参数三：选择队列的业务标识（订单ID）
             */
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                /**
                 *
                 * @param mqs：队列集合
                 * @param msg：消息对象
                 * @param arg：业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long orderId = (long) arg;
                    long index = orderId % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderList.get(i).getOrderId());
            System.out.println("发送结果：" + sendResult);
        }
        // 在应用退出前，销毁 Producer 对象
        // 注意：如果不销毁也没有问题
        producer.shutdown();
    }

}

class OrderStep {
    private long orderId;
    private String desc;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }
}