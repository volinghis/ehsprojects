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
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;

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
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2020年1月8日
 * qjj v1.0.0 修改原因
 */
public class EamScrapRequestBean {

	
	/**
	 * 关联设备
	 */
	private List<EamLedger> scrapDatas;

	/**
	 * 报废申请表单
	 */
	private EamScrap eamScrap;
	
	private FlowProcessInfo flowProcessInfo;

	/**
	 * @return the scrapDatas
	 */
	public List<EamLedger> getScrapDatas() {
		return scrapDatas;
	}

	/**
	 * @param scrapDatas the scrapDatas to set
	 */
	public void setScrapDatas(List<EamLedger> scrapDatas) {
		this.scrapDatas = scrapDatas;
	}


	/**
	 * @return the eamScrap
	 */
	public EamScrap getEamScrap() {
		return eamScrap;
	}

	/**
	 * @param eamScrap the eamScrap to set
	 */
	public void setEamScrap(EamScrap eamScrap) {
		this.eamScrap = eamScrap;
	}

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

}
