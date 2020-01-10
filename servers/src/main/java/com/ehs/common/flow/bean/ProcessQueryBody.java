package com.ehs.common.flow.bean;

public class ProcessQueryBody {

	private String processDefineKey;
	
	private String processInstanceId;
	
	private String taskId;

	public String getProcessDefineKey() {
		return processDefineKey;
	}

	public void setProcessDefineKey(String processDefineKey) {
		this.processDefineKey = processDefineKey;
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
	
	
}
