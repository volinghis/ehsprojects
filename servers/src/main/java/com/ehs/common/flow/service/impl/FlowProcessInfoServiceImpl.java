package com.ehs.common.flow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.common.flow.dao.FlowProcessInfoDao;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;

@Service
public class FlowProcessInfoServiceImpl implements FlowProcessInfoService {

	
	@Resource
	private FlowProcessInfoDao flowProcessInfoDao;
	@Override
	public FlowProcessInfo findProcessInfoByProcessInstanceId(String processInstanceId) {
		return flowProcessInfoDao.findByFlowProcessInstanceId(processInstanceId);
	}

}
