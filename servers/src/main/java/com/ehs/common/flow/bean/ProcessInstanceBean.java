package com.ehs.common.flow.bean;

import java.util.List;




public class ProcessInstanceBean {


	

	private String processInstanceId;
	

	
	private List<ProcessStepBean> nextStep;
	
	

	private boolean candoCanel=false;
	
	
    private String activeTaskId;
    
    
    


	public String getActiveTaskId() {
		return activeTaskId;
	}

	public void setActiveTaskId(String activeTaskId) {
		this.activeTaskId = activeTaskId;
	}

	public boolean isCandoCanel() {
		return candoCanel;
	}

	public void setCandoCanel(boolean candoCanel) {
		this.candoCanel = candoCanel;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}



	public List<ProcessStepBean> getNextStep() {
		return nextStep;
	}

	public void setNextStep(List<ProcessStepBean> nextStep) {
		this.nextStep = nextStep;
	}


	

	
}
