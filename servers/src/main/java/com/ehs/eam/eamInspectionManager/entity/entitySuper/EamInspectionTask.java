package com.ehs.eam.eamInspectionManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.flow.entity.FlowBaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionTask.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:28:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class EamInspectionTask extends FlowBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String INSPTASK_CODE = "inspTaskCode";
	public static final String INSPTAS_KNAME = "inspTaskName";
	public static final String INSPTASK_TYPE = "inspTaskType";
	public static final String RESPONSIBLE_PERSON = "responsiblePerson";
	public static final String RESPONSIBLE_DEPT = "responsibleDept";
	public static final String START_DATE = "startDate";
	public static final String OVER_DATE = "overDate";
	public static final String STATUS = "status";
	public static final String WORKING_STANDARD = "workingStandard";
	public static final String REMARK = "remark";
	
	/**
	 * 检修设备关联表
	 */
	private String inspeDeviceKey;
	
	/**
	 * 检修编码
	 */
	private String inspTaskCode;
	
	/**
	 * 检修名称
	 */
	private String inspTaskName;
	
	/**
	 * 检修类型
	 */
	private String inspTaskType;
	
	/**
	 * 责任人
	 */
	private String responsiblePerson;
	
	/**
	 * 责任部门
	 */
	private String responsibleDept;
	
	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp startDate;
	
	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp overDate;
	
	/**
	 *申请状态
	 */
	private String status;
	
	/**
	 * 工作标准
	 */
	@Column(length = 3000)
	private String workingStandard;

	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	
	@Override
	public String getFlow() {
		return "EamInspectionTaskFlow";
	}

	@Override
	public String getEditPage() {
		return "EamInspectionTaskEditFlow";
	}

	@Override
	public String getViewPage() {
		return "EamInspectionTaskViewFlow";
	}
	
	public String getInspeDeviceKey() {
		return inspeDeviceKey;
	}

	public void setInspeDeviceKey(String inspeDeviceKey) {
		this.inspeDeviceKey = inspeDeviceKey;
	}

	public String getInspTaskCode() {
		return inspTaskCode;
	}

	public void setInspTaskCode(String inspTaskCode) {
		this.inspTaskCode = inspTaskCode;
	}

	public String getInspTaskName() {
		return inspTaskName;
	}

	public void setInspTaskName(String inspTaskName) {
		this.inspTaskName = inspTaskName;
	}

	public String getInspTaskType() {
		return inspTaskType;
	}

	public void setInspTaskType(String inspTaskType) {
		this.inspTaskType = inspTaskType;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getResponsibleDept() {
		return responsibleDept;
	}

	public void setResponsibleDept(String responsibleDept) {
		this.responsibleDept = responsibleDept;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getOverDate() {
		return overDate;
	}

	public void setOverDate(Timestamp overDate) {
		this.overDate = overDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkingStandard() {
		return workingStandard;
	}

	public void setWorkingStandard(String workingStandard) {
		this.workingStandard = workingStandard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
