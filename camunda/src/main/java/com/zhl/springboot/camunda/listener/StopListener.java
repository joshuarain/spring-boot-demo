package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class StopListener implements ExecutionListener{
	
	private FixedValue field_var;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("service stop listener....");
	}

	public FixedValue getField_var() {
		return field_var;
	}

	public void setField_var(FixedValue field_var) {
		this.field_var = field_var;
	}

}
