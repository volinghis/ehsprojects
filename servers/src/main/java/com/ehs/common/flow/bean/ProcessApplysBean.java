package com.ehs.common.flow.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcessApplysBean {

	private String processName;
	private String currentStep;
	private String currentUser;
	private String processInstanceId;
	
	private String processPage;
	
	
	
	
	
	
	private String businessKey;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Date createTime;
	
	
	
	
		
	
	
	
	
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
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

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
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
	
}
