package com.ehs.common.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.bean.ProcessApplysBean;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.flow.utils.FlowConstans;
import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;

@RestController
public class FlowTaskController {
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private FlowProcessInfoService  flowProcessInfoService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/task/findApplys")
	public String save(HttpServletRequest request, @RequestBody PageBody pageBody) {
		PageInfoBean pib=new PageInfoBean();
		long count=runtimeService.createProcessInstanceQuery().startedBy(SysAccessUser.get().getUserKey()).count();
		pib.setTotalCount(count);
		List<ProcessApplysBean> applys=new ArrayList<ProcessApplysBean>();
		List<ProcessInstance> pis= runtimeService.createProcessInstanceQuery().startedBy(SysAccessUser.get().getUserKey()).includeProcessVariables().listPage((pageBody.getPage()-1)*pageBody.getSize(), pageBody.getSize());
		if(pis!=null&&!pis.isEmpty()) {
			for(ProcessInstance pi:pis) {
				ProcessApplysBean ab=new ProcessApplysBean();
				ab.setProcessName(pi.getProcessDefinitionName());
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByProcessInstanceId(pi.getId());
				ab.setCurrentStep(fpi.getFlowCurrentStepName());
				ab.setCurrentUser(fpi.getFlowCurrentPersonName());
				ab.setBusinessKey(pi.getBusinessKey());
				
				String currentStep=fpi.getFlowCurrentStep();
				if(StringUtils.equals(currentStep, String.valueOf(pi.getProcessVariables().get(FlowConstans.FLOW_START_ACTIVITY_ID)))) {
					ab.setProcessPage(String.valueOf(pi.getProcessVariables().get(FlowConstans.FLOW_FORM_EDIT_PAGE)));
				}else {
					ab.setProcessPage(String.valueOf(pi.getProcessVariables().get(FlowConstans.FLOW_FORM_VIEW_PAGE)));
				}
				
				ab.setProcessInstanceId(pi.getId());
				ab.setCreateTime(pi.getStartTime());
				applys.add(ab);
				
			}
		}
		pib.setDataList(applys);
		return (pib==null)?"{}":JsonUtils.toJsonString(pib);
	}
	
}
