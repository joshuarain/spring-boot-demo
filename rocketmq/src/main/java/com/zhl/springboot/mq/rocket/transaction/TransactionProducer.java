package com.zhl.springboot.mq.rocket.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lenovo
 * @title: TransactionProducer
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 14:57
 */
public class TransactionProducer {
    /**
     * Usage Constraint
     * (1) Messages of the transactional have no schedule and batch support.
     * (2) In order to avoid a single message being checked too many times and lead to half queue message accumulation， we limited the number of checks for a single message to 15 times by default, but users can change this limit by change the “transactionCheckMax” parameter in the configuration of the broker， if one message has been checked over “transactionCheckMax” times， broker will discard this message and print an error log at the same time by default. Users can change this behavior by override the “AbstractTransactionCheckListener” class.
     * (3) A transactional message will be checked after a certain period of time that determined by parameter “transactionTimeout” in the configuration of the broker. And users also can change this limit by set user property “CHECK_IMMUNITY_TIME_IN_SECONDS” when sending transactional message, this parameter takes precedence over the “transactionMsgTimeout” parameter.
     * (4) A transactional message maybe checked or consumed more than once.
     * (5) Committed message reput to the user’s target topic may fail. Currently, it depends on the log record. High availability is ensured by the high availability mechanism of RocketMQ itself. If you want to ensure that the transactional message isn’t lost and the transaction integrity is guaranteed, it is recommended to use synchronous double write. mechanism.
     * (6) Producer IDs of transactional messages cannot be shared with producer IDs of other types of messages. Unlike other types of message, transactional messages allow backward queries. MQ Server query clients by their Producer IDs.
     */

    /**
     * Application
     * 1、 Transactional status
     *
     * There are three states for transactional message:
     * (1) TransactionStatus.CommitTransaction: commit transaction，it means that allow consumers to consume this message.
     * (2) TransactionStatus.RollbackTransaction: rollback transaction，it means that the message will be deleted and not allowed to consume.
     * (3) TransactionStatus.Unknown: intermediate state，it means that MQ is needed to check back to determine the status.
     *
     * 2、Send transactional message
     *
     * （1）Create the transactional producer
     * Use TransactionMQProducer class to create producer client, and specify a unique producerGroup, and you can set up a custom thread pool to process check requests. After executing the local transaction, you need to reply to MQ according to the execution result，and the reply status is described in the above section.
     *
     * （2）Implement the TransactionListener interface
     * The “executeLocalTransaction” method is used to execute local transaction when send half message succeed. It returns one of three transaction status mentioned in the previous section.
     * The “checkLocalTransaction” method is used to check the local transaction status and respond to MQ check requests. It also returns one of three transaction status mentioned in the previous section.
     */

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListener() {
            private AtomicInteger transactionIndex = new AtomicInteger(0);

            private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                int value = transactionIndex.getAndIncrement();
                int status = value % 3;
                localTrans.put(msg.getTransactionId(), status);
                return LocalTransactionState.UNKNOW;
            }

            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                Integer status = localTrans.get(msg.getTransactionId());
                if (null != status) {
                    switch (status) {
                        case 0:
                            return LocalTransactionState.UNKNOW;
                        case 1:
                            return LocalTransactionState.COMMIT_MESSAGE;
                        case 2:
                            return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };
        TransactionMQProducer producer = new TransactionMQProducer("my_producer");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException   e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();

    }
}
