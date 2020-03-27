package com.ehs.eam.checks.bean;

public class CheckDefectAnalysisBean {

	private String addressKey;
	private String addressName;
	private String objectKey;
	private String objectName;
	private long count;
	public CheckDefectAnalysisBean() {
		
	}
	public CheckDefectAnalysisBean(String addressKey,String addressName,String objectKey,String objectName,long count) {
		this.addressKey=addressKey;
		this.addressName=addressName;
		this.objectKey=objectKey;
		this.objectName=objectName;
		this.count=count;

	}
	public String getAddressKey() {
		return addressKey;
	}
	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	
	
}
