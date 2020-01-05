package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;

public class StartProcess implements ExecutionListener{
	
	private FixedValue process_starter;
	

	public FixedValue getProcess_starter() {
		return process_starter;
	}


	public void setProcess_starter(FixedValue process_starter) {
		this.process_starter = process_starter;
	}


	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("start event post process......");
	}

}
