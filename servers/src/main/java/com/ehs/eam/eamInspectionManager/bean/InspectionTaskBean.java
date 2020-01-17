package com.ehs.eam.eamInspectionManager.bean;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionDevice;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;

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
