package com.ehs.common.flow.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.Event;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.bean.ProcessCommentBean;
import com.ehs.common.flow.bean.ProcessDefineBean;
import com.ehs.common.flow.bean.ProcessInstanceBean;
import com.ehs.common.flow.bean.ProcessQueryBody;
import com.ehs.common.flow.bean.ProcessStepBean;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.enums.FlowStatus;
import com.ehs.common.flow.enums.FlowTaskOper;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.organization.entity.OrgUser;

@RestController
public class FlowBaseController {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private org.flowable.engine.TaskService taskService;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Resource
	private HistoryService historyService;

	/**
	 * 
	 * @Function: ProcessInfoController.java
	 * @Description: 获取流程信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年12月25日 下午4:32:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessDefine")
	public String getProcessByProcessDefineKey(HttpServletRequest request,@RequestBody ProcessQueryBody processQueryBody, HttpServletResponse response) {

		String processInstanceId = processQueryBody.getProcessInstanceId();
		String taskId = processQueryBody.getTaskId();
		String processDefineKey = processQueryBody.getProcessDefineKey();
		String processDefineId = "";
		if (StringUtils.isNotBlank(taskId)) {
			Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
			if(t==null) {
				HistoricTaskInstance hti= historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
				processDefineId=hti.getProcessDefinitionId();
				processInstanceId=hti.getProcessInstanceId();
			}else {
				processDefineId = t.getProcessDefinitionId();
				processInstanceId = t.getProcessInstanceId();
			}

		} else {
			if (StringUtils.isNotBlank(processInstanceId)) {
				
				ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
						.singleResult();
				if(pi==null) {
					processDefineId=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
				}else {
					processDefineId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
							.singleResult().getProcessDefinitionId();
				}

			} else {
				processDefineId = repositoryService.createProcessDefinitionQuery()
						.processDefinitionKey(processDefineKey).latestVersion().singleResult().getId();
			}
		}

		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefineId);
		Assert.notNull(bpmnModel, "bpmnModel can not be find");

		org.flowable.bpmn.model.Process process = bpmnModel.getMainProcess();
		ProcessDefineBean pdb = new ProcessDefineBean();
		
		
		
		if(StringUtils.isBlank(taskId)&&StringUtils.isBlank(processInstanceId)) {
			pdb.setStart(true);
		}
				
		pdb.setName(process.getName());
		pdb.setKey(process.getId());

		List<ProcessStepBean> steps = new ArrayList<ProcessStepBean>();
		Collection<FlowElement> flowElements = process.getFlowElements();
		if (flowElements != null) {
			for (FlowElement flowElement : flowElements) {
				if (flowElement instanceof UserTask) {
					ProcessStepBean psb = new ProcessStepBean();
					psb.setStepKey(flowElement.getId());
					psb.setStepName(flowElement.getName());
					steps.add(psb);
				}

			}
			for (FlowElement flowElement : flowElements) {
				if (flowElement instanceof EndEvent) {
					ProcessStepBean psb = new ProcessStepBean();
					psb.setStepKey(flowElement.getId());
					psb.setStepName(flowElement.getName());
					steps.add(psb);
					break;
				}

			}
		}

		pdb.setSteps(steps);

		if (StringUtils.isNotBlank(processInstanceId)) {
			int num = 0;
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByProcessInstanceId(processInstanceId);
			if(StringUtils.equalsIgnoreCase(fpi.getFlowCurrentStep(), FlowStatus.END.name())||StringUtils.equalsIgnoreCase(fpi.getFlowCurrentStep(), FlowStatus.CANCELED.name())) {
				num=steps.size()-1;
				pdb.setCurrentStepNum(num);
			}else {
				for (ProcessStepBean psb : steps) {
					if(StringUtils.equalsIgnoreCase(psb.getStepKey(), fpi.getFlowCurrentStep())) {
						pdb.setCurrentStepNum(num);
						break;
					}
					num++;
				}	
			}
			

		}
		return JsonUtils.toJsonString(pdb);
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessComments")
	public String getProcessCommentsByProcessInstanceKey(HttpServletRequest request,@RequestBody ProcessQueryBody processQueryBody, HttpServletResponse response) {

		String processInstanceId = processQueryBody.getProcessInstanceId();
		String taskId = processQueryBody.getTaskId();
		List<ProcessCommentBean> pcbList = new ArrayList<ProcessCommentBean>();

		if(StringUtils.isBlank(taskId)&&StringUtils.isBlank(processInstanceId)) {
			return JsonUtils.toJsonString(pcbList);
		}
		
		Task t = null;
		if (StringUtils.isNotBlank(taskId)) {
			t = taskService.createTaskQuery().taskId(taskId).singleResult();
			if(t==null) {
				processInstanceId=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getProcessInstanceId();
			}else {
				processInstanceId = t.getProcessInstanceId();
			}
		}
		List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
		if (comments != null && !comments.isEmpty()) {
			for (Comment c : comments) {
				ProcessCommentBean pcb = new ProcessCommentBean();
				pcb.setContent(c.getFullMessage());
				pcb.setCreationTime(c.getTime());
				pcb.setCreation(c.getUserId());
				if (StringUtils.isNotBlank(c.getUserId())) {
					OrgUser ou = baseCommonService.findByKey(OrgUser.class, c.getUserId());
					if (ou != null) {
						pcb.setCreationName(ou.getName());
					}
				}
				String oper = "";
				if (StringUtils.equals(FlowTaskOper.AGREE.name(), c.getType())) {
					oper = "同意";
				} else if (StringUtils.equals(FlowTaskOper.COMMIT.name(), c.getType())) {
					oper = "提交";
				} else if (StringUtils.equals(FlowTaskOper.REJECT.name(), c.getType())) {
					oper = "驳回";
				}else if (StringUtils.equals(FlowTaskOper.CANCELD.name(), c.getType())) {
					oper = "撤回";
				}
				pcb.setOperType(oper);
				pcbList.add(pcb);
			}
		}
		return JsonUtils.toJsonString(pcbList);
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessInfo")
	public String getProcessInfo(HttpServletRequest request,@RequestBody ProcessQueryBody processQueryBody, HttpServletResponse response) {

		String processInstanceId = processQueryBody.getProcessInstanceId();
		String taskId = processQueryBody.getTaskId();

		if(StringUtils.isBlank(taskId)&&StringUtils.isBlank(processInstanceId)) {
			return JsonUtils.toJsonString(new FlowProcessInfo());
		}else {
			if (StringUtils.isNotBlank(taskId)) {
				Task t=taskService.createTaskQuery().taskId(taskId).singleResult();
				if(t==null) {
					HistoricTaskInstance hti= historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
					processInstanceId=hti.getProcessInstanceId();
				}else {
					processInstanceId=t.getProcessInstanceId();
				}
			} 
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByProcessInstanceId(processInstanceId);
			return JsonUtils.toJsonString(fpi);
		}
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessInstance")
	public String getProcessInstanceByKey(HttpServletRequest request,@RequestBody ProcessQueryBody processQueryBody, HttpServletResponse response) {

		String processInstanceId = processQueryBody.getProcessInstanceId();
		String taskId = processQueryBody.getTaskId();

		ProcessInstanceBean pib = new ProcessInstanceBean();
		if(StringUtils.isBlank(taskId)&&StringUtils.isBlank(processInstanceId)) {
			return JsonUtils.toJsonString(pib);
		}
		Task t = null;
		if (StringUtils.isNotBlank(taskId)) {
			t = taskService.createTaskQuery().taskId(taskId).singleResult();
			if(t!=null) {
				processInstanceId=runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getId();
			}else {
				processInstanceId=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getProcessInstanceId();
			}
		} else {
			ProcessInstance p= runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(p!=null) {
				t = taskService.createTaskQuery().processInstanceId(processInstanceId)
						.taskAssignee(SysAccessUser.get().getUserKey()).active().singleResult();
			}

		}
		
		FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByProcessInstanceId(processInstanceId);
		if(StringUtils.equalsIgnoreCase(fpi.getFlowCurrentStep(), FlowStatus.CANCELED.name())||StringUtils.equalsIgnoreCase(fpi.getFlowCurrentStep(), FlowStatus.END.name())) {
			return JsonUtils.toJsonString(pib);
		}
		
		if (StringUtils.equals(SysAccessUser.get().getUserKey(), fpi.getOwner())) {
			pib.setCandoCanel(true);
		}


		List<ProcessStepBean> psblist = new ArrayList<ProcessStepBean>();
		if (t != null) {
			pib.setActiveTaskId(t.getId());
			BpmnModel bpmnModel = repositoryService.getBpmnModel(t.getProcessDefinitionId());
			FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(t.getTaskDefinitionKey());
			// 输出连线
			List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();

			// 遍历返回下一个节点信息
			for (SequenceFlow outgoingFlow : outgoingFlows) {
				// 类型自己判断
				FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
				// 用户任务
				if (targetFlowElement instanceof EndEvent) {
					ProcessStepBean p = new ProcessStepBean();
					p.setStepKey(FlowStatus.END.name());
					p.setStepName("通过");
					psblist.add(p);
				} else {
					ProcessStepBean p = new ProcessStepBean();
					p.setStepKey(targetFlowElement.getId());
					p.setStepName(targetFlowElement.getName());
					psblist.add(p);
				}
			}
			pib.setNextStep(psblist);
		}

		return JsonUtils.toJsonString(pib);
	}

}
