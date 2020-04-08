package com.ehs.common.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.bean.ApplysQueryBean;
import com.ehs.common.flow.bean.ProcessApplysBean;
import com.ehs.common.flow.bean.ProcessDonesBean;
import com.ehs.common.flow.bean.ProcessTasksBean;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.flow.utils.FlowConstans;
import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrgUser;

@RestController
public class FlowTaskController {
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private HistoryService historyService;
	
	@Resource
	private FlowProcessInfoService  flowProcessInfoService;
	
	@Resource
	private org.flowable.engine.TaskService taskService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/task/findApplys")
	public String findApplys(HttpServletRequest request, @RequestBody ApplysQueryBean pageBody) {
		if(StringUtils.isBlank(SysAccessUser.get().getUserKey())) {
			return "[]";
		}
		pageBody.setUserKey(SysAccessUser.get().getUserKey());
		PageInfoBean pib=new PageInfoBean();
		PageInfoBean pip=flowProcessInfoService.findProcessInfo(pageBody);
		pib.setTotalCount(pip.getTotalCount());
		List<ProcessApplysBean> applys=new ArrayList<ProcessApplysBean>();
		if(pip.getDataList()!=null) {
			for(FlowProcessInfo fpi:(List<FlowProcessInfo>)pip.getDataList()) {
				ProcessApplysBean ab=new ProcessApplysBean();
				ab.setProcessName(fpi.getFlowProcessName());
				ab.setCurrentStep(fpi.getFlowCurrentStepName());
				ab.setCurrentUser(fpi.getFlowCurrentPersonName());
				ab.setBusinessKey(fpi.getBusinessEntityKey());
				
				String currentStep=fpi.getFlowCurrentStep();
				if(StringUtils.equals(currentStep, fpi.getFlowStartActivityId())) {
					ab.setProcessPage(fpi.getFlowEditPage());
				}else {
					ab.setProcessPage(fpi.getFlowViewPage());
				}
				
				ab.setProcessInstanceId(fpi.getFlowProcessInstanceId());
				ab.setCreateTime(fpi.getOwnerCreationTime());
				applys.add(ab);
				
			}
		}
		pib.setDataList(applys);
		return (pib==null)?"{}":JsonUtils.toJsonString(pib);
	}
	
	
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/task/findTasks")
	public String findTasks(HttpServletRequest request, @RequestBody PageBody pageBody) {
		if(StringUtils.isBlank(SysAccessUser.get().getUserKey())) {
			return "[]";
		}
		PageInfoBean pib=new PageInfoBean();
		
		long count=taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getUserKey()).active().count();
		
		List<Task> tasks= taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getUserKey()).active().orderByTaskCreateTime().desc().listPage((pageBody.getPage()-1)*pageBody.getSize(), pageBody.getSize());
		
		
		pib.setTotalCount(count);
		List<ProcessTasksBean> taskBeans=new ArrayList<ProcessTasksBean>();
		
		if(tasks!=null&&!tasks.isEmpty()) {
			for(Task t:tasks) {
				ProcessTasksBean ptb=new ProcessTasksBean();
				ptb.setTaskId(t.getId());
				
				ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).includeProcessVariables().singleResult();
				ptb.setProcessName(pi.getProcessDefinitionName());
				ptb.setBusinessKey(pi.getBusinessKey());
				ptb.setProcessCreateTime(pi.getStartTime());
				
				String processCreateUser=pi.getStartUserId();
				if(StringUtils.isNotBlank(processCreateUser)) {
					OrgUser ou=baseCommonService.findByKey(OrgUser.class, processCreateUser);
					if(ou!=null) {
						ptb.setProcessCreateUser(ou.getName());
					}
				}
				ptb.setProcessInstanceId(pi.getId());
				
				
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByProcessInstanceId(pi.getId());
				String currentStep=fpi.getFlowCurrentStep();
				if(StringUtils.equals(currentStep, fpi.getFlowStartActivityId())) {
					ptb.setProcessPage(fpi.getFlowEditPage());
				}else {
					ptb.setProcessPage(fpi.getFlowViewPage());
				}
				ptb.setCurrentStep(fpi.getFlowCurrentStepName());
				taskBeans.add(ptb);
			}
		}

		pib.setDataList(taskBeans);
		return (pib==null)?"{}":JsonUtils.toJsonString(pib);
	}
	
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/task/findDones")
	public String findDones(HttpServletRequest request, @RequestBody PageBody pageBody) {
		if(StringUtils.isBlank(SysAccessUser.get().getUserKey())) {
			return "[]";
		}
		PageInfoBean pib=new PageInfoBean();
		
		long count=historyService.createHistoricTaskInstanceQuery().taskAssignee(SysAccessUser.get().getUserKey()).count();
		
		List<HistoricTaskInstance> tasks= historyService.createHistoricTaskInstanceQuery().taskAssignee(SysAccessUser.get().getUserKey()).orderByTaskCreateTime().desc().listPage((pageBody.getPage()-1)*pageBody.getSize(), pageBody.getSize());
		
		
		pib.setTotalCount(count);
		List<ProcessDonesBean> taskBeans=new ArrayList<ProcessDonesBean>();
		
		if(tasks!=null&&!tasks.isEmpty()) {
			for(HistoricTaskInstance t:tasks) {
				ProcessDonesBean ptb=new ProcessDonesBean();
				ptb.setTaskId(t.getId());
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByProcessInstanceId(t.getProcessInstanceId());
				ptb.setCreateTime(t.getEndTime());
				
				ptb.setProcessName(fpi.getFlowProcessName());
				ptb.setBusinessKey(fpi.getBusinessEntityKey());
				ptb.setProcessCreateTime(fpi.getOwnerCreationTime());
				
				String processCreateUser=fpi.getOwner();
				if(StringUtils.isNotBlank(processCreateUser)) {
					OrgUser ou=baseCommonService.findByKey(OrgUser.class, processCreateUser);
					if(ou!=null) {
						ptb.setProcessCreateUser(ou.getName());
					}
				}
				ptb.setProcessInstanceId(fpi.getFlowProcessInstanceId());
				
				
				String currentStep=fpi.getFlowCurrentStep();
				if(StringUtils.equals(currentStep, fpi.getFlowStartActivityId())) {
					ptb.setProcessPage(fpi.getFlowEditPage());
				}else {
					ptb.setProcessPage(fpi.getFlowViewPage());
				}
				ptb.setCurrentStep(fpi.getFlowCurrentStepName());
				taskBeans.add(ptb);
			}
		}

		pib.setDataList(taskBeans);
		return (pib==null)?"{}":JsonUtils.toJsonString(pib);
	}
	
}
