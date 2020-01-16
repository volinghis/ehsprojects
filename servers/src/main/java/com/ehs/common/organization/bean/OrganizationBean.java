package com.ehs.common.organization.bean;

import java.util.List;

public class OrganizationBean {

	/**
	 * id
	 */
	private String id;
	
	private String value;
	
	
	/**
	 * 父节点Id
	 */
	private String parentId;
	
	
	/**
	 * 名称
	 */
	private String label;
	
	/**
	 * 是否编辑
	 */
//	private Boolean isEdit;
	
	/**
	 * 子节点
	 */
	private List<OrganizationBean> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
//	public Boolean getIsEdit() {
//		return isEdit;
//	}
//
//	public void setIsEdit(Boolean isEdit) {
//		this.isEdit = isEdit;
//	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<OrganizationBean> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationBean> children) {
		this.children = children;
	}
	
}
