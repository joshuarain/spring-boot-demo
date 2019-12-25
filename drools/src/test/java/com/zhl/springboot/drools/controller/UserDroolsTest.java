package com.zhl.springboot.drools.controller;

/**
 * @author Lenovo
 * @title: UserDroolsTest
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/25 9:25
 */

import com.zhl.springboot.drools.DroolsApplication;
import com.zhl.springboot.drools.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsApplication.class)
@Slf4j
public class UserDroolsTest {

    @Autowired
    //@KBase("kbase")
    //@KReleaseId(groupId = "com.pingan.core",artifactId = "drools",version = "LATEST")
    private KieBase kieBase;

    @Test
    public void rule1(){
        //StatefulKnowledgeSession
        KieSession kieSession = kieBase.newKieSession();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("superbing");
        userInfo.setTelephone("13618607409");

        kieSession.insert(userInfo);
        //kieSession.setGlobal("log",log);
        int firedCount = kieSession.fireAllRules();
        //kieSession.dispose();
        System.out.println("触发了" + firedCount + "条规则");
    }
}
