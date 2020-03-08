/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:43:14 
 */
package com.ehs.common.data.entity.entitysuper;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionary.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:43:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class DataDictionary extends BaseEntity{

	public static final String DATA_CODE="dataCode";
	public static final String PARENT_CODE="parentCode";
	public static final String TEXT="text";
	public static final String SORT="sort";
	public static final String SYSTEM_CODE="systemCode";

	private static final long serialVersionUID = 1L;
	
	/**
	     * 数据字典编码
	*/
	@Column(nullable = false)
	private String dataCode;

	/**
	 * 父菜单
	 */
	private String parentCode;
	
	/**
	 * 名称
	 */
	private String text;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	private String systemCode;

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
   
}
