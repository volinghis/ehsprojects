/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity.entitySuper 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:23:56 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.ehs.common.flow.entity.FlowBaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: Eamallocate.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月7日 下午7:23:56
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月7日
 *        qjj v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamAllocateSuper extends FlowBaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	public static final String ALLOCATE_NUM = "allocateNum" ;
	public static final String APPLICATION_NAME = "applicationName" ;
	public static final String APPLICATION_TIME = "applicationTime" ;
	public static final String ALLOCATE_DATE = "allocateDate" ;
	public static final String APPLICANT = "applicant" ;
	public static final String ALLOCATE_DEPT = "allocateDept" ;
	public static final String TARGET_DEPT = "targetDept" ;
	public static final String ALLOCATE_REASON = "allocateReason" ;
	public static final String STATUS = "status" ;
	public static final String INSTALL_LOCATION = "installLocation" ;
	public static final String PROFESSION = "profession" ;
	public static final String TARGET_POSITION = "targetPosition" ;
	public static final String AMOUNT = "amount" ;
	public static final String CURRENT_STEP_PERSON = "currentStepPerson";
	public static final String UNIT = "unit" ;
	


	/**
	 * 调拨编号
	 */
	private String allocateNum;

	/**
	 * 申请名称
	 */
	private String applicationName;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp applicationTime;

	/**
	 * 调拨时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp allocateDate;

	/**
	 * 申请人
	 */
	private String applicant;
	
	/**
	 * 申请部门
	 */
	private String allocateDept;

	/**
	 * 调入部门
	 */
	private String targetDept;

	/**
	 * 调拨原因
	 */
	@Column(length = 3000)
	private String allocateReason;

	/**
	 * 申请状态
	 */
	@Transient
	private String status;
	
	/**
	 * 审核人
	 */
	@Transient
	private String currentStepPerson;

	/**
	 * 调出位置
	 */
	private String installLocation;
	
	/**
	 * 调出部门
	 */
	private String profession;
	
	/**
	 * 调入位置
	 */
	private String targetPosition;
	
	/**
	 * 调拨数量
	 */
	private String amount;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * @return the allocateNum
	 */
	public String getAllocateNum() {
		return allocateNum;
	}

	/**
	 * @param allocateNum the allocateNum to set
	 */
	public void setAllocateNum(String allocateNum) {
		this.allocateNum = allocateNum;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the applicationTime
	 */
	public Timestamp getApplicationTime() {
		return applicationTime;
	}

	/**
	 * @param applicationTime the applicationTime to set
	 */
	public void setApplicationTime(Timestamp applicationTime) {
		this.applicationTime = applicationTime;
	}

	/**
	 * @return the allocateDate
	 */
	public Timestamp getAllocateDate() {
		return allocateDate;
	}

	/**
	 * @param allocateDate the allocateDate to set
	 */
	public void setAllocateDate(Timestamp allocateDate) {
		this.allocateDate = allocateDate;
	}

	/**
	 * @return the applicant
	 */
	public String getApplicant() {
		return applicant;
	}

	/**
	 * @param applicant the applicant to set
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	/**
	 * @return the targetDept
	 */
	public String getTargetDept() {
		return targetDept;
	}

	/**
	 * @param targetDept the targetDept to set
	 */
	public void setTargetDept(String targetDept) {
		this.targetDept = targetDept;
	}

	/**
	 * @return the allocateReason
	 */
	public String getAllocateReason() {
		return allocateReason;
	}

	/**
	 * @param allocateReason the allocateReason to set
	 */
	public void setAllocateReason(String allocateReason) {
		this.allocateReason = allocateReason;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the installLocation
	 */
	public String getInstallLocation() {
		return installLocation;
	}

	/**
	 * @param installLocation the installLocation to set
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the targetPosition
	 */
	public String getTargetPosition() {
		return targetPosition;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	public void setTargetPosition(String targetPosition) {
		this.targetPosition = targetPosition;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override

	public String getFlow() {
		return "EamAllocateFlow";
	}

	@Override
	public String getEditPage() {
		return "eamAllocateBaseForm";
	}

	@Override
	public String getViewPage() {
		return "eamAllocateDetails";
	}

	public String getCurrentStepPerson() {
		return currentStepPerson;
	}

	public void setCurrentStepPerson(String currentStepPerson) {
		this.currentStepPerson = currentStepPerson;
	}

	public String getAllocateDept() {
		return allocateDept;
	}

	public void setAllocateDept(String allocateDept) {
		this.allocateDept = allocateDept;
	}
}
