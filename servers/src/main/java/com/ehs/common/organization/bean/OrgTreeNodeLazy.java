package com.ehs.common.organization.bean;

import java.util.List;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OrgTreeNodeLazy.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月20日 下午3:44:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月20日     zhaol          v1.0.0               修改原因
*/
public class OrgTreeNodeLazy {

	/**
	 * 当前节点id
	 */
	private String id;
	
	/**
	 * 父id
	 */
	private String pid;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 是否是叶子
	 */
	private boolean isLeaf;

	/**
	 * 子数据
	 */
	private List<OrgTreeNodeLazy> children;
	
	private String sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<OrgTreeNodeLazy> getChildren() {
		return children;
	}

	public void setChildren(List<OrgTreeNodeLazy> children) {
		this.children = children;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
