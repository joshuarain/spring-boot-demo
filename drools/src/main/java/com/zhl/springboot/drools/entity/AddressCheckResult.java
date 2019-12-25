package com.zhl.springboot.drools.entity;

import lombok.Data;

/**
 * @author Lenovo
 * @title: AddressCheckResult
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/25 10:12
 */
@Data
public class AddressCheckResult {

    private boolean postCodeResult = false; // true:通过校验；false：未通过校验
}
