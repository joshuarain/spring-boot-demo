package com.zhl.springboot.drools.controller;

import com.zhl.springboot.drools.entity.Address;
import com.zhl.springboot.drools.entity.AddressCheckResult;
import com.zhl.springboot.drools.entity.Message;
import com.zhl.springboot.drools.utils.KieUtils;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张雷
 * @date 2019/12/25 11:13
 */
@RestController
public class TestController {

    @RequestMapping("/address")
    public void test() {
//        KieServices kieServices = KieServices.Factory.get();
//        KieContainer kContainer = kieServices.getKieClasspathContainer();
////        StatelessKieSession statelessKsession = kContainer.newStatelessKieSession("KSession1");
//        KieSession kieSession = kContainer.newKieSession("ksession-rule");
//        Address address = new Address();
//        address.setPostcode("99425");
//
//        AddressCheckResult result = new AddressCheckResult();
//        kieSession.insert(address);
//        kieSession.insert(result);
//
//        kieSession.insert(new Address());
//        int ruleFiredCount = kieSession.fireAllRules();
//        System.out.println("触发了" + ruleFiredCount + "条规则");
//
//        if(result.isPostCodeResult()){
//            System.out.println("规则校验通过");
//        }
        KieContainer kContainer = null;
        try {
            KieServices ks = KieServices.Factory.get();
            kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-hello");

            Message message1 = new Message(Message.MessageType.HI, "杨过");
            kSession.insert(message1);
            kSession.fireAllRules();

            Message message2 = new Message(Message.MessageType.GOODBYE, "姑姑");
            kSession.insert(message2);
            kSession.fireAllRules();

            Message message3 = new Message(Message.MessageType.CHAT, "美羊羊");
            kSession.insert(message3);
            kSession.fireAllRules();

            Message message4 = new Message(null, "beggar");
            kSession.setGlobal("temp", "我是谁？我在哪？我要干什么？");
            kSession.insert(message4);
            kSession.fireAllRules();

            Message message5 = new Message(null, "loop");
            kSession.setGlobal("count", new AtomicInteger(0));
            kSession.insert(message5);
            kSession.fireAllRules();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (kContainer != null) {
                kContainer.dispose();
            }
        }

    }
}



