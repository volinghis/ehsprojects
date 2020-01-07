package com.ehs.common.flow.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;

public interface FlowProcessInfoService {
	public FlowProcessInfo findProcessInfoByProcessInstanceId(String processInstanceId);

}
