/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.data.bean 
 * @author: qjj   
 * @date: 2020年3月17日 下午2:37:13 
 */
package com.ehs.eam.eamLedgerManager.bean;

import java.util.List;



/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: TreeDataBean.java
* @Description:封装树结构的数据对象
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月17日 下午2:37:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月17日     qjj           v1.0.0               修改原因
*/
public class TreeDataBean {

	private String id;
	
	private String pid;
	
	private String label;
	
	private List<TreeDataBean> children;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public TreeDataBean setId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public TreeDataBean setLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * @return the children
	 */
	public List<TreeDataBean> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public TreeDataBean setChildren(List<TreeDataBean> children) {
		this.children = children;
		return this;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public TreeDataBean setPid(String pid) {
		this.pid = pid;
		return this;
	}
	
}
