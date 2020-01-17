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
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamInspectionManager.dao.EamInspectionTaskDao;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionDevice;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;
import com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

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
	
	@Override
	public PageInfoBean findAllTask(QueryBean queryBean) {
		// TODO Auto-generated method stub
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

	@Override
	@Transactional
	public void saveTask(InspectionTaskBean taskBean) {
		// TODO Auto-generated method stub
		try {
			if (taskBean != null) {
				if(StringUtils.isBlank(taskBean.getInspectionTask().getInspeDeviceKey())) {
					taskBean.getInspectionTask().setInspeDeviceKey(UUID.randomUUID().toString());
				}
				ProcessInstance pi = flowBaseService.startProcess(taskBean.getInspectionTask(), taskBean.getFlowProcessInfo());
				System.out.println("pi====================="+pi);
				if (pi != null) {
					System.out.println("pi====================="+taskBean.getInspectionTask().getInspeDeviceKey());
					if(!CollectionUtils.isEmpty(taskBean.getInspectionDevice())) {
						for (EamInspectionDevice device : taskBean.getInspectionDevice()) {
							device.setDeviceKey(taskBean.getInspectionTask().getInspeDeviceKey());
							baseCommonService.saveOrUpdate(device);
							System.out.println("保存成功=================");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
