/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2020年1月8日 下午6:53:09 
 */
package com.ehs.eam.eamLedgerManager.bean;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamLedgerManager.entity.EamAllocate;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamScrapRequestBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月8日 下午6:53:09
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月8日
 *        qjj v1.0.0 修改原因
 */
public class EamAllocateRequestBean {

	private FlowProcessInfo flowProcessInfo;

	private List<EamLedger> eamLedgerDatas;

	private EamAllocate allocateForm;

	/**
	 * @return the flowProcessInfo
	 */
	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}

	/**
	 * @param flowProcessInfo the flowProcessInfo to set
	 */
	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}

	/**
	 * @return the allocateForm
	 */
	public EamAllocate getAllocateForm() {
		return allocateForm;
	}

	/**
	 * @param allocateForm the allocateForm to set
	 */
	public void setAllocateForm(EamAllocate allocateForm) {
		this.allocateForm = allocateForm;
	}

	/**
	 * @return the eamLedgerDatas
	 */
	public List<EamLedger> getEamLedgerDatas() {
		return eamLedgerDatas;
	}

	/**
	 * @param eamLedgerDatas the eamLedgerDatas to set
	 */
	public void setEamLedgerDatas(List<EamLedger> eamLedgerDatas) {
		this.eamLedgerDatas = eamLedgerDatas;
	}

}
