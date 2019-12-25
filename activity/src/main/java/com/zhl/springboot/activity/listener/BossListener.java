package com.zhl.springboot.activity.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author Lenovo
 * @title: BossListener
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 17:52
 */
@Component
public class BossListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("bossListener");
    }
}
