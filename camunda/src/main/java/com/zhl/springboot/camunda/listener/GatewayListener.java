package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class GatewayListener implements ExecutionListener{
	
	private FixedValue gateway_var;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
			System.out.println("gateway start listener......");
	}

	public FixedValue getGateway_var() {
		return gateway_var;
	}

	public void setGateway_var(FixedValue gateway_var) {
		this.gateway_var = gateway_var;
	}

}
