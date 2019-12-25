package com.zhl.springboot.activity.service;

import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * @title: PassService
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 16:25
 */
@Service
public class PassService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("通过申请并结束。可在此进行数据库操作。");
    }
}
