package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsParam.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月3日 上午9:44:52 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月3日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class PartsParam extends BaseEntity {
	
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String PARAM_KEY="paramKey";
	public static final String PARAM_NAME="paramName";
	public static final String PARAM_VAL="paramVal";
	public static final String REMARK="remark";

	/**
	 * 参数key
	 */
	private String paramKey;
	
	/**
	 * 参数名称
	 */
	private String paramName;
	
	/**
	 * 参数值
	 */
	private String paramVal;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
