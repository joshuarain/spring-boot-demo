package com.zhl.springboot.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Lenovo
 * @title: DroolsApplication
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/24 17:17
 */
@SpringBootApplication
//@ImportResource("classpath:/spring-drools.xml") //方式一
public class DroolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsApplication.class, args);
    }
}
