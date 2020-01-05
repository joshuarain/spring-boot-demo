package com.zhl.springboot.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;
@Component
public class SequenceFlowListener implements ExecutionListener{
	
	private FixedValue seq_var;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("take listener......");
	}

	public FixedValue getSeq_var() {
		return seq_var;
	}

	public void setSeq_var(FixedValue seq_var) {
		this.seq_var = seq_var;
	}

}
