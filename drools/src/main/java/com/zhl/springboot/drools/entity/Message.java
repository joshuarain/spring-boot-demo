package com.zhl.springboot.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lenovo
 * @title: Message
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/25 14:40
 */
@Setter
@Getter
@AllArgsConstructor
public class Message {

    public enum MessageType {
        HI,
        GOODBYE,
        CHAT
    }

    private MessageType messageType;
    private String target;

}