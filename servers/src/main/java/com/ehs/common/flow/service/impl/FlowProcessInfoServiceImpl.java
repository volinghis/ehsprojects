package com.ehs.common.flow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.bean.ApplysQueryBean;
import com.ehs.common.flow.dao.FlowProcessInfoDao;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrgUser;

@Service
public class FlowProcessInfoServiceImpl implements FlowProcessInfoService {

	
	@Resource
	private FlowProcessInfoDao flowProcessInfoDao;
	@Override
	public FlowProcessInfo findProcessInfoByProcessInstanceId(String processInstanceId) {
		return flowProcessInfoDao.findByFlowProcessInstanceId(processInstanceId);
	}
	
	
	@Override
	public PageInfoBean findProcessInfo(ApplysQueryBean applysQueryBean) {
		PageRequest pageRequest =PageRequest.of(applysQueryBean.getPage()-1, applysQueryBean.getSize());
		Page<FlowProcessInfo> infos=flowProcessInfoDao.findInfos(applysQueryBean.getUserKey(), new DataModel[] {DataModel.CREATE,DataModel.UPDATE}, pageRequest);
		if(infos!=null) {
			PageInfoBean pi=new PageInfoBean();
			pi.setDataList(infos.getContent());
			pi.setTotalCount(infos.getTotalElements());
			return pi;
		}
		return null;
	}

}
