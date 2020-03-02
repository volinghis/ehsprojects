package com.ehs.eam.eamInspectionManager.bean;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionDevice;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: InspectionTaskBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:26:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
public class InspectionTaskBean {
	
	/**
	 * 流程实体
	 */
	public FlowProcessInfo flowProcessInfo;
	
	/**
	 * 巡检任务实体
	 */
	public EamInspectionTask inspectionTask;
	
	/**
	 * 巡检设备实体
	 */
	public List<EamInspectionDevice> inspectionDevice;
	
	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}

	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}

	public EamInspectionTask getInspectionTask() {
		return inspectionTask;
	}

	public void setInspectionTask(EamInspectionTask inspectionTask) {
		this.inspectionTask = inspectionTask;
	}

	public List<EamInspectionDevice> getInspectionDevice() {
		return inspectionDevice;
	}

	public void setInspectionDevice(List<EamInspectionDevice> inspectionDevice) {
		this.inspectionDevice = inspectionDevice;
	}
	
}
