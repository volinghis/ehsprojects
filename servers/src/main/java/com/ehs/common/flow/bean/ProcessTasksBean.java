package com.ehs.common.flow.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcessTasksBean {

	private String processName;
	private String currentStep;
	private String processCreateUser;
	
	private String processInstanceId;
	
	private String taskId;
	private String processPage;
	
	private String businessKey;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Date processCreateTime;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}


	public String getProcessCreateUser() {
		return processCreateUser;
	}

	public void setProcessCreateUser(String processCreateUser) {
		this.processCreateUser = processCreateUser;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessPage() {
		return processPage;
	}

	public void setProcessPage(String processPage) {
		this.processPage = processPage;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public Date getProcessCreateTime() {
		return processCreateTime;
	}

	public void setProcessCreateTime(Date processCreateTime) {
		this.processCreateTime = processCreateTime;
	}

	
	
	
}
