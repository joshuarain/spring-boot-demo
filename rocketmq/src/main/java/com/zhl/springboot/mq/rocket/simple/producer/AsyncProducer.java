package com.zhl.springboot.mq.rocket.simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;


/**
 * @author Lenovo
 * @title: AsyncProducer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/22 9:54
 */
public class AsyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("my_group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i=0;i<100;i++){
            final int index = i;
            Message msg = new Message("TopicTest","TagA","OrderID188","Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
                }

                public void onException(Throwable throwable) {
                    System.out.printf("%-10d Exception %s %n",index,throwable);
                    throwable.printStackTrace();
                }
            });
        }
//        producer.shutdown();  //不注释掉会跑no route for Topic
    }
}
