package com.ehs.common.flow.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.entity.impl.FlowSample;
import com.ehs.common.flow.service.FlowBaseService;

@Service
public class FlowSampleServiceImpl implements com.ehs.common.flow.service.FlowSampleService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService; 
	
	@Transactional
	@Override
	public FlowSample saveFlowSample(FlowSample sample, FlowProcessInfo processInfo) {
		if(StringUtils.isBlank(sample.getKey())) {
			sample.setKey(UUID.randomUUID().toString());
		}
		flowBaseService.startProcess(sample, processInfo);
		return sample;
	}

}
