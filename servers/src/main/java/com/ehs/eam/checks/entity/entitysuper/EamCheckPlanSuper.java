/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 下午4:22:40 
 */
package com.ehs.eam.checks.entity.entitysuper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: FileInfo.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年6月26日 下午4:22:40
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年6月26日
 * chentm v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamCheckPlanSuper extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String NAME="name";
	public static final String YEAR="year";
	public static final String ENABLE="enable";
	public static final String CHECK_SCOPE="checkScope";
	public static final String NOTES="notes";
	public static final String CHECK_SCOPE_TYPE="checkScopeType";
	public static final String CHECKOR="checkor";
	public static final String START_TIME="startTime";
	public static final String END_TIME="endTime";
	public static final String VIEWOR="viewor";
	public static final String VIEW_TYPE="viewType";
	public static final String RATE="rate";
	

	private String name;
	private Integer year;
	private String rate;
	private Boolean enable=true;
	@Transient
	private String enableLabel;
	
	@Column(length = 4000)
	private String checkScopeStr;
	@Column(length = 4000)
	private String notes;
	
	private String checkScopeType;
	
	
	private String checkor;
	
	private String checkorName;
	@JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
	private Timestamp startTime;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp endTime;
    
	private String viewType;

	private String deviceAddress;
	@Transient
	private String deviceAddressName;
	
	
	
	


	public String getCheckScopeType() {
		return checkScopeType;
	}

	public void setCheckScopeType(String checkScopeType) {
		this.checkScopeType = checkScopeType;
	}

	public String getViewType() {
		return viewType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}



	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDeviceAddress() {
		return deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public String getDeviceAddressName() {
		return deviceAddressName;
	}

	public void setDeviceAddressName(String deviceAddressName) {
		this.deviceAddressName = deviceAddressName;
	}

	public String getEnableLabel() {
		return enable?"启用":"停用";
	}

	public void setEnableLabel(String enableLabel) {
		this.enableLabel = enableLabel;
	}


	

	



	public String getCheckScopeStr() {
		return checkScopeStr;
	}

	public void setCheckScopeStr(String checkScopeStr) {
		this.checkScopeStr = checkScopeStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getCheckor() {
		return checkor;
	}

	public void setCheckor(String checkor) {
		this.checkor = checkor;
	}

	public String getCheckorName() {
		return checkorName;
	}

	public void setCheckorName(String checkorName) {
		this.checkorName = checkorName;
	}





	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}



	
	


	
	
	
	
}
