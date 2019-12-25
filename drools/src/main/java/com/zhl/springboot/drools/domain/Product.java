package com.zhl.springboot.drools.domain;

import lombok.Data;

/**
 * @author Lenovo
 * @title: Product
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/24 17:26
 */
@Data
public class Product {

    private String productCode;

    private String productType;

    private Integer times;

}
