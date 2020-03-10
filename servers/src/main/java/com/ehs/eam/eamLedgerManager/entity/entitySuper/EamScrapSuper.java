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
 * @ClassName: EamScrap.java
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
public abstract class EamScrapSuper extends FlowBaseEntity{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String SCRAP_NUM = "scrapNum";
	public static final String APPLICATION_NAME = "applicationName";
	public static final String APPLICATION_TIME = "applicationTime";
	public static final String SCRAP_DATE = "scrapDate";
	public static final String APPLICANT = "applicant";
	public static final String SCRAP_DEPT = "scrapDept";
	public static final String CURRENT_STEP_PERSON = "currentStepPerson";
	public static final String SCRAP_REASON = "scrapReason";
	public static final String STATUS = "status";
	public static final String DEVICE_KEY = "deviceKey";

	/**
	 * 报废编号
	 */
	private String scrapNum;

	/**
	 * 申请名称
	 */
	private String applicationName;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp applicationTime;

	/**
	 * 报废时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp scrapDate;

	/**
	 * 申请人
	 */
	private String applicant;

	/**
	 * 报废部门
	 */
	private String scrapDept;

	/**
	 * 报废原因
	 */
	@Column(length = 3000)
	private String scrapReason;

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
	   * 关联设备
	 */
	private String deviceKey;

	/**
	 * @return the scrapNum
	 */
	public String getScrapNum() {
		return scrapNum;
	}

	/**
	 * @param scrapNum the scrapNum to set
	 */
	public void setScrapNum(String scrapNum) {
		this.scrapNum = scrapNum;
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
	 * @return the scrapDate
	 */
	public Timestamp getScrapDate() {
		return scrapDate;
	}

	/**
	 * @param scrapDate the scrapDate to set
	 */
	public void setScrapDate(Timestamp scrapDate) {
		this.scrapDate = scrapDate;
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
	 * @return the scrapDept
	 */
	public String getScrapDept() {
		return scrapDept;
	}

	/**
	 * @param scrapDept the scrapDept to set
	 */
	public void setScrapDept(String scrapDept) {
		this.scrapDept = scrapDept;
	}

	/**
	 * @return the scrapReason
	 */
	public String getScrapReason() {
		return scrapReason;
	}

	/**
	 * @param scrapReason the scrapReason to set
	 */
	public void setScrapReason(String scrapReason) {
		this.scrapReason = scrapReason;
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
	@Override

	public String getFlow() {
		return "EamScrapFlow";
	}

	@Override
	public String getEditPage() {
		return "eamScrapBaseForm";
	}

	@Override
	public String getViewPage() {
		return "eamScrapDetails";
	}

	public String getCurrentStepPerson() {
		return currentStepPerson;
	}

	public void setCurrentStepPerson(String currentStepPerson) {
		this.currentStepPerson = currentStepPerson;
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
}
