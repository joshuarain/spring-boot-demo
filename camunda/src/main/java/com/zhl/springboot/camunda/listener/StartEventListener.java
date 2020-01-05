package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class StartEventListener implements JavaDelegate{
	
	private FixedValue start_filed;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("开始流程。。。。。。。");
	}

	public FixedValue getStart_filed() {
		return start_filed;
	}

	public void setStart_filed(FixedValue start_filed) {
		this.start_filed = start_filed;
	}

}
