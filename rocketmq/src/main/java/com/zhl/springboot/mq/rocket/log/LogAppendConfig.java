package com.zhl.springboot.mq.rocket.log;

/**
 * @author Lenovo
 * @title: LogAppendConfig
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 14:46
 */
public class LogAppendConfig {
    //log4j
    /**
     * properties config like below:
     *
     * log4j.appender.mq=org.apache.rocketmq.logappender.log4j.RocketmqLog4jAppender
     * log4j.appender.mq.Tag=yourTag
     * log4j.appender.mq.Topic=yourLogTopic
     * log4j.appender.mq.ProducerGroup=yourLogGroup
     * log4j.appender.mq.NameServerAddress=yourRocketmqNameserverAddress
     * log4j.appender.mq.layout=org.apache.log4j.PatternLayout
     * log4j.appender.mq.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-4r [%t] (%F:%L) %-5p - %m%n
     */

    /**
     * xml config like below:
     *
     * <appender name="mqAppender1" class="org.apache.rocketmq.logappender.log4j.RocketmqLog4jAppender">
     *     <param name="Tag" value="yourTag" />
     *     <param name="Topic" value="yourLogTopic" />
     *     <param name="ProducerGroup" value="yourLogGroup" />
     *     <param name="NameServerAddress" value="yourRocketmqNameserverAddress"/>
     *     <layout class="org.apache.log4j.PatternLayout">
     *         <param name="ConversionPattern" value="%d{yyyy-MM-dd HH:mm:ss}-%p %t %c - %m%n" />
     *     </layout>
     * </appender>
     *
     * <appender name="mqAsyncAppender1" class="org.apache.log4j.AsyncAppender">
     *     <param name="BufferSize" value="1024" />
     *     <param name="Blocking" value="false" />
     *     <appender-ref ref="mqAppender1"/>
     * </appender>
     */

    //log4j2
    /**
     * <RocketMQ name="rocketmqAppender" producerGroup="yourLogGroup" nameServerAddress="yourRocketmqNameserverAddress"
     *      topic="yourLogTopic" tag="yourTag">
     *     <PatternLayout pattern="%d [%p] hahahah %c %m%n"/>
     * </RocketMQ>
     */

    //logback
    /**
     * <appender name="mqAppender1" class="org.apache.rocketmq.logappender.logback.RocketmqLogbackAppender">
     *     <tag>yourTag</tag>
     *     <topic>yourLogTopic</topic>
     *     <producerGroup>yourLogGroup</producerGroup>
     *     <nameServerAddress>yourRocketmqNameserverAddress</nameServerAddress>
     *     <layout>
     *         <pattern>%date %p %t - %m%n</pattern>
     *     </layout>
     * </appender>
     *
     * <appender name="mqAsyncAppender1" class="ch.qos.logback.classic.AsyncAppender">
     *     <queueSize>1024</queueSize>
     *     <discardingThreshold>80</discardingThreshold>
     *     <maxFlushTime>2000</maxFlushTime>
     *     <neverBlock>true</neverBlock>
     *     <appender-ref ref="mqAppender1"/>
     * </appender>
     */
}
