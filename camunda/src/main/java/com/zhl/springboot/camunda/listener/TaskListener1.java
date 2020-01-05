package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class TaskListener1 implements TaskListener{
	
	private FixedValue username;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("task complete......");
	}

	public FixedValue getUsername() {
		return username;
	}

	public void setUsername(FixedValue username) {
		this.username = username;
	}

}
