package com.zhl.springboot.mq.rocket.simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;


/**
 * @author Lenovo
 * @title: OneWayProducer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/22 10:05
 */
public class OneWayProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("my_producer_2");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i <100 ; i++) {
            Message msg = new Message("TopicTest","TagA",("Hello World"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));;
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }
}
