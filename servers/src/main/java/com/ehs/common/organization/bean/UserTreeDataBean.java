package com.ehs.common.organization.bean;

import java.util.List;

public class UserTreeDataBean {

	private boolean disabled=false;
	private boolean org=false;
	private boolean leaf=true;
	
	
	private String value;
	private String label;
	private List<UserTreeDataBean> children;
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isOrg() {
		return org;
	}
	public void setOrg(boolean org) {
		this.org = org;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<UserTreeDataBean> getChildren() {
		return children;
	}
	public void setChildren(List<UserTreeDataBean> children) {
		this.children = children;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	
	
	
}
