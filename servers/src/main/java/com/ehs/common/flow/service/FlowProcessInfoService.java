package com.ehs.common.flow.service;

import java.util.List;

import com.ehs.common.flow.bean.ApplysQueryBean;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;

public interface FlowProcessInfoService {
	public FlowProcessInfo findProcessInfoByProcessInstanceId(String processInstanceId);
	public PageInfoBean findProcessInfo(ApplysQueryBean applysQueryBean);
	public List<FlowProcessInfo> findProcessInfoNotScore();

}
