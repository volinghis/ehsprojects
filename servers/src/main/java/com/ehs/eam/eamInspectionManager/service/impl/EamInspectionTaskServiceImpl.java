package com.ehs.eam.eamInspectionManager.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamInspectionManager.dao.EamInspectionTaskDao;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionDevice;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;
import com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionTaskServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:26:05 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
@Service
public class EamInspectionTaskServiceImpl implements EamInspectionTaskService {
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Resource
	private EamInspectionTaskDao taskDao;
	
	/**
	 * 
	* @see com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService#findAllTask(com.ehs.eam.eamPartLibraryManager.bean.QueryBean)  
	* @Function: EamInspectionTaskServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:26:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findAllTask(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<EamInspectionTask> tasks = taskDao.findAllTask(pageRequest);
		if (tasks!=null) {
			List<EamInspectionTask> eamInspectionTasks  = tasks.getContent();
			for (EamInspectionTask task : eamInspectionTasks) {
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(task.getKey());
				if(fpi!=null) {
					task.setStatus(fpi.getFlowCurrentStepName());
				}
			}
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(eamInspectionTasks);
			pb.setTotalCount(tasks.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService#saveTask(com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean)  
	* @Function: EamInspectionTaskServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:26:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveTask(InspectionTaskBean taskBean) {
		try {
			if (taskBean != null) {
				if(StringUtils.isBlank(taskBean.getInspectionTask().getInspeDeviceKey())) {
					taskBean.getInspectionTask().setInspeDeviceKey(UUID.randomUUID().toString());
				}
				OrganizationInfo org = baseCommonService.findByKey(OrganizationInfo.class, taskBean.getInspectionTask().getResponsibleDept());
				taskBean.getInspectionTask().setResponsibleDept(org.getName());
				OrgUser user = baseCommonService.findByKey(OrgUser.class, taskBean.getInspectionTask().getResponsiblePerson());
				taskBean.getInspectionTask().setResponsiblePerson(user.getName());
				ProcessInstance pi = flowBaseService.startProcess(taskBean.getInspectionTask(), taskBean.getFlowProcessInfo());
				if (pi != null) {
					if(!CollectionUtils.isEmpty(taskBean.getInspectionDevice())) {
						for (EamInspectionDevice device : taskBean.getInspectionDevice()) {
							device.setDeviceKey(taskBean.getInspectionTask().getInspeDeviceKey());
//							baseCommonService.saveOrUpdate(device);
							System.out.println("保存成功=================");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
