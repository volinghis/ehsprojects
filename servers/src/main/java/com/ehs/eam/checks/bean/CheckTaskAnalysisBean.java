package com.ehs.eam.checks.bean;

public class CheckTaskAnalysisBean {

	private String name;
	private Long value;
	private String orgKey;
	
	public CheckTaskAnalysisBean() {
		
	}
	public CheckTaskAnalysisBean(String orgKey,String name,Long value) {
		this.name=name;
		this.value=value;
		this.orgKey=orgKey;
	}
	
	
	
	public String getOrgKey() {
		return orgKey;
	}
	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	
	
}
