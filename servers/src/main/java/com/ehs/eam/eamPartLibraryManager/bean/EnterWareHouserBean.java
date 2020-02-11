package com.ehs.eam.eamPartLibraryManager.bean;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouserBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午11:16:00 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
public class EnterWareHouserBean {

	/**
	 * 入库信息
	 */
	private EnterWareHouse enterWareHouse;
	
	/**
	 * 流程实体
	 */
	private FlowProcessInfo flowProcessInfo;
	
	/**
	 * 备件扩展表
	 */
	private List<PartsExtends> partsExtends;

	public EnterWareHouse getEnterWareHouse() {
		return enterWareHouse;
	}

	public void setEnterWareHouse(EnterWareHouse enterWareHouse) {
		this.enterWareHouse = enterWareHouse;
	}

	public List<PartsExtends> getPartsExtends() {
		return partsExtends;
	}

	public void setPartsExtends(List<PartsExtends> partsExtends) {
		this.partsExtends = partsExtends;
	}

	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}

	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}
	
}
