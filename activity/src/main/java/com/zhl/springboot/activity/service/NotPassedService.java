package com.zhl.springboot.activity.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * @title: NotPassedService
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 16:28
 */
@Service
public class NotPassedService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("审核未通过。");
    }
}
