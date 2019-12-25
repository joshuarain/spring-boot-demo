package com.zhl.springboot.drools.entity;

import lombok.Data;

/**
 * @author Lenovo
 * @title: Address
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/25 10:11
 */
@Data
public class Address {

    private String postcode;

    private String street;

    private String state;

}