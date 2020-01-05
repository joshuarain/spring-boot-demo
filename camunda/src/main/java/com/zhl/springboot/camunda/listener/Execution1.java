package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class Execution1 implements ExecutionListener{
	
	private FixedValue username;
	private FixedValue password;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("task start event...");
	}

	public FixedValue getUsername() {
		return username;
	}

	public void setUsername(FixedValue username) {
		this.username = username;
	}

	public FixedValue getPassword() {
		return password;
	}

	public void setPassword(FixedValue password) {
		this.password = password;
	}

}
