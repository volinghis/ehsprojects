package com.ehs.common.basicInfo.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientInfo.java
* @Description: 客户实体
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 上午11:01:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class ClientInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String CLIENT_CODE = "clientCode";
	public static final String CLIENT_NAME = "clientName";
	public static final String CLIENT_TYPE = "clientType";
	public static final String CLIENT_TYPE_NAME = "clientTypeName";
	public static final String CLIENT_LEVEL = "clientLevel";
	public static final String CLIENT_LEVEL_NAME = "clientLevelName";
	public static final String SORT = "sort";
	public static final String REMARK = "remark";
	public static final String STATE="state";
	
	/**
	 * 客户编号
	 */
	public String clientCode;
	
	/**
	 * 客户名称
	 */
	public String clientName;
	
	/**
	 * 客户类型
	 */
	public String clientType;
	public String clientTypeName;
	
	/**
	 * 客户等级
	 */
	public String clientLevel;
	public String clientLevelName;
	
	/**
	 * 排序
	 */
	public String sort;
	
	/**
	 * 备注
	 */
	public String remark;
	
	/**
	 * 0正常，1锁定
	 */
	private Integer state;

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getClientTypeName() {
		return clientTypeName;
	}

	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}

	public String getClientLevel() {
		return clientLevel;
	}

	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}

	public String getClientLevelName() {
		return clientLevelName;
	}

	public void setClientLevelName(String clientLevelName) {
		this.clientLevelName = clientLevelName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
