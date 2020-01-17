package com.ehs.eam.eamInspectionManager.entity.entitySuper;

import javax.persistence.MappedSuperclass;

import com.ehs.eam.eamLedgerManager.entity.entitySuper.EamLedgerSuper;

@MappedSuperclass
public abstract class EamInspectionDevice extends EamLedgerSuper{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	/**
	 * 检修任务关联设备key
	 */
	private String deviceKey;

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}
	

}
