/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity.entitySuper 
 * @author: qjj   
 * @date: 2020年1月2日 下午3:04:57 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamInspectors.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月2日 下午3:04:57
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月2日
 *        qjj v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamInspectorsSuper extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	public static final String NAME = "name" ;
	public static final String DEPARTMENT = "department" ;
	public static final String SERVER_TIME = "serverTime" ;
	public static final String DEPARTURE_TIME = "departureTime" ;
	public static final String DEVICE_KEY = "deviceKey" ;

	private String name;

	private String department;

	/**
	 * 担任时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp serverTime;

	/**
	 * 离任时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp departureTime;
	
	private String remark;
	
	private String deviceKey;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the serverTime
	 */
	public Timestamp getServerTime() {
		return serverTime;
	}

	/**
	 * @param serverTime the serverTime to set
	 */
	public void setServerTime(Timestamp serverTime) {
		this.serverTime = serverTime;
	}

	/**
	 * @return the departureTime
	 */
	public Timestamp getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the deviceKey
	 */
	public String getDeviceKey() {
		return deviceKey;
	}

	/**
	 * @param deviceKey the deviceKey to set
	 */
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
