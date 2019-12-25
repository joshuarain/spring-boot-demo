package com.zhl.springboot.mq.rocket.batch;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @title: BatchProducer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 14:27
 */
public class BatchProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my_producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String topic = "BatchTest";
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        try {
            producer.send(messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

    /**
     * 批量发送注意大小，单个消息体最大256kb。批量发送，不超过1MB。此时，最好split list。如下类
     */
    public class ListSplitter implements Iterator<List<Message>> {
        private final int SIZE_LIMIT = 1000 * 1000;
        private final List<Message> messages;
        private int currIndex;

        public ListSplitter(List<Message> messages) {
            this.messages = messages;
        }

        public boolean hasNext() {
            return currIndex < messages.size();
        }

        public List<Message> next() {
            int nextIndex = currIndex;
            int totalSize = 0;
            for (; nextIndex < messages.size(); nextIndex++) {
                Message message = messages.get(nextIndex);
                int tmpSize = message.getTopic().length() + message.getBody().length;
                Map<String, String> properties = message.getProperties();
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    tmpSize += entry.getKey().length() + entry.getValue().length();
                }
                tmpSize = tmpSize + 20; //for log overhead
                if (tmpSize > SIZE_LIMIT) {
                    //it is unexpected that single message exceeds the SIZE_LIMIT
                    //here just let it go, otherwise it will block the splitting process
                    if (nextIndex - currIndex == 0) {
                        //if the next sublist has no element, add this one and then break, otherwise just break
                        nextIndex++;
                    }
                    break;
                }
                if (tmpSize + totalSize > SIZE_LIMIT) {
                    break;
                } else {
                    totalSize += tmpSize;
                }

            }
            List<Message> subList = messages.subList(currIndex, nextIndex);
            currIndex = nextIndex;
            return subList;
        }

        public void remove() {

        }
    }

    //then you could split the large list into small ones:
//    ListSplitter splitter = new ListSplitter(messages);
//    while(splitter.hasNext())
//    {
//        try {
//            List<Message> listItem = splitter.next();
//            producer.send(listItem);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //handle the error
//        }
//    }
}
