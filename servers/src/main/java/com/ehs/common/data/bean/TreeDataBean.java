/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.data.bean 
 * @author: qjj   
 * @date: 2020年3月17日 下午2:37:13 
 */
package com.ehs.common.data.bean;

import java.util.List;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: TreeDataBean.java
* @Description: 该类的功能描述
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
	public void setId(String id) {
		this.id = id;
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
	public void setLabel(String label) {
		this.label = label;
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
	public void setChildren(List<TreeDataBean> children) {
		this.children = children;
	}
	
}
