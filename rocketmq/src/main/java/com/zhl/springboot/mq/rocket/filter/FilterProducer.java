package com.zhl.springboot.mq.rocket.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author Lenovo
 * @title: FilterProducer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 14:40
 */
public class FilterProducer {
    /**
     * You can put properties in message through method putUserProperty when sending.
     */
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("my_producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message msg = new Message("TopicTest",
                "TagA",
                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // Set some properties.
        msg.putUserProperty("a", "3");

        SendResult sendResult = producer.send(msg);

        System.out.println(sendResult);

        producer.shutdown();
    }
}
