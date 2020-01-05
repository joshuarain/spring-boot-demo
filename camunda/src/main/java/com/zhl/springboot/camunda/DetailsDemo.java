package com.zhl.springboot.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;

public class DetailsDemo implements JavaDelegate{
	
	private FixedValue field_var;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("details demo ......");
	}

	public FixedValue getField_var() {
		return field_var;
	}

	public void setField_var(FixedValue field_var) {
		this.field_var = field_var;
	}

}
