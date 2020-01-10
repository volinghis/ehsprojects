package com.ehs.common.flow.bean;

import java.util.List;

public class ProcessDefineBean {


	
	private String key;
	private String name;
	private List<ProcessStepBean> steps;
	private Integer currentStepNum=0;
	
	private boolean start;
	
	
	
	
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public Integer getCurrentStepNum() {
		return currentStepNum;
	}
	public void setCurrentStepNum(Integer currentStepNum) {
		this.currentStepNum = currentStepNum;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProcessStepBean> getSteps() {
		return steps;
	}
	public void setSteps(List<ProcessStepBean> steps) {
		this.steps = steps;
	}
	
	
}
