package com.ehs.eam.checks.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.entity.entitysuper.EamCheckTaskSuper;

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
	private FlowProcessInfo flowProcessInfo;
	
	
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
		return null;
	}

	@Override
	public String getEditPage() {
		return null;
	}

	@Override
	public String getViewPage() {
		return null;
	}

}
