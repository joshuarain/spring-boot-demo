package com.zhl.springboot.mq.rocket.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Lenovo
 * @title: FilterConsumer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 14:39
 */
public class FilterConsumer {
    /**
     * RocketMQ only defines some basic grammars to support this feature. You could also extend it easily.
     *
     * Numeric comparison, like >, >=, <, <=, BETWEEN, =;
     * Character comparison, like =, <>, IN;
     * IS NULL or IS NOT NULL;
     * Logical AND, OR, NOT;
     * Constant types are:
     *
     * Numeric, like 123, 3.1415;
     * Character, like ‘abc’, must be made with single quotes;
     * NULL, special constant;
     * Boolean, TRUE or FALSE;
     * Usage constraints
     * Only push consumer could select messages by SQL92. The interface is:
     *
     * public void subscribe(final String topic, final MessageSelector messageSelector)
     */
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_consumer");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 5 "));
        consumer.setMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
