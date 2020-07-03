package com.ehs.eam.checks.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.entity.entitysuper.EamCheckTaskSuper;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckTask.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午11:11:21 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_CHECK_TASK",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckTask extends EamCheckTaskSuper{

	private static final long serialVersionUID = 1L;

	@Transient
	private List<EamCheckReserveUsed> eamCheckReserveUsed;
	
	@Transient
	private List<EamCheckRepair> eamCheckRepair;
	
	@Transient
	private List<EamCheckDefect> eamCheckDefect;
	
	@Transient
	private EamCheckPlan eamCheckPlan;
	
	@Transient
	private FlowProcessInfo flowProcessInfo;

	public EamCheckPlan getEamCheckPlan() {
		return eamCheckPlan;
	}

	public void setEamCheckPlan(EamCheckPlan eamCheckPlan) {
		this.eamCheckPlan = eamCheckPlan;
	}

	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}

	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}

	public List<EamCheckReserveUsed> getEamCheckReserveUsed() {
		return eamCheckReserveUsed;
	}

	public void setEamCheckReserveUsed(List<EamCheckReserveUsed> eamCheckReserveUsed) {
		this.eamCheckReserveUsed = eamCheckReserveUsed;
	}

	public List<EamCheckRepair> getEamCheckRepair() {
		return eamCheckRepair;
	}

	public void setEamCheckRepair(List<EamCheckRepair> eamCheckRepair) {
		this.eamCheckRepair = eamCheckRepair;
	}

	public List<EamCheckDefect> getEamCheckDefect() {
		return eamCheckDefect;
	}

	public void setEamCheckDefect(List<EamCheckDefect> eamCheckDefect) {
		this.eamCheckDefect = eamCheckDefect;
	}

	@Override
	public String getFlow() {
		return "EamCheckTask";
	}

	@Override
	public String getEditPage() {
		return "eamCheckTaskEdit";
	}

	@Override
	public String getViewPage() {
		return "eamCheckTaskView";
	}

}