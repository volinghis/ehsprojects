/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2020年1月2日 下午3:20:43 
 */
package com.ehs.eam.eamLedgerManager.bean;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamRequestBean.java
 * @Description: 设备台账请求封装
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月2日 下午3:20:43
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月2日
 *        qjj v1.0.0 修改原因
 */
public class EamRequestBean {

	private EamLedger eamLedger;
	
	private EamLedgerLast eamLedgerLast;

	private String deviceKeys;

	private List<EamParameters> paramsList;

	private List<EamInspectors> inspectorsList;
	
	private FlowProcessInfo flowProcessInfo;

	/**
	 * @return the eamLedger
	 */
	public EamLedger getEamLedger() {
		return eamLedger;
	}

	/**
	 * @param eamLedger the eamLedger to set
	 */
	public void setEamLedger(EamLedger eamLedger) {
		this.eamLedger = eamLedger;
	}

	/**
	 * @return the deviceKeys
	 */
	public String getDeviceKeys() {
		return deviceKeys;
	}

	/**
	 * @param deviceKeys the deviceKeys to set
	 */
	public void setDeviceKeys(String deviceKeys) {
		this.deviceKeys = deviceKeys;
	}

	/**
	 * @return the paramsList
	 */
	public List<EamParameters> getParamsList() {
		return paramsList;
	}

	/**
	 * @param paramsList the paramsList to set
	 */
	public void setParamsList(List<EamParameters> paramsList) {
		this.paramsList = paramsList;
	}

	/**
	 * @return the inspectorsList
	 */
	public List<EamInspectors> getInspectorsList() {
		return inspectorsList;
	}

	/**
	 * @param inspectorsList the inspectorsList to set
	 */
	public void setInspectorsList(List<EamInspectors> inspectorsList) {
		this.inspectorsList = inspectorsList;
	}

	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}

	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}

	public EamLedgerLast getEamLedgerLast() {
		return eamLedgerLast;
	}

	public void setEamLedgerLast(EamLedgerLast eamLedgerLast) {
		this.eamLedgerLast = eamLedgerLast;
	}

}
