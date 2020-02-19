package com.ehs.eam.eamDataBase.bean;

import java.util.List;


public class DataDictTreeBean {

	/**
	 * id
	 */
	private String id;
	
	
	/**
	 * 父节点Id
	 */
	private String parentId;
	
	
	/**
	 * 名称
	 */
	private String label;
	
	/**
	 * 子节点
	 */
	private List<DataDictTreeBean> children;

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

	public List<DataDictTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<DataDictTreeBean> children) {
		this.children = children;
	}
	
}
