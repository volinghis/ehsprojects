package com.ehs.eam.checks.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.dao.EamCheckTaskDao;
import com.ehs.eam.checks.entity.EamCheckDefect;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;
import com.ehs.eam.checks.entity.EamCheckTask;
import com.ehs.eam.checks.service.EamCheckTaskService;

@Service
public class EamCheckTaskServiceImpl implements EamCheckTaskService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private EamCheckTaskDao eamCheckTaskDao;
	
	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckTaskService#saveTask(com.ehs.eam.checks.entity.EamCheckTask)  
	* @Function: EamCheckTaskServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月17日 上午11:17:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     chentm           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveTask(EamCheckTask t) {
		if(StringUtils.isBlank(t.getKey())) {
			String key=UUID.randomUUID().toString();
			t.setKey(key);
		}
		List<EamCheckDefect> des= t.getEamCheckDefect();
		if(des!=null&&!des.isEmpty()) {
			for(EamCheckDefect d :des) {
				if(StringUtils.isBlank(d.getTaskKey())) {
					d.setTaskKey(t.getKey());
					baseCommonService.saveOrUpdate(d);
				}
			}
		}
		List<EamCheckRepair> ecr=t.getEamCheckRepair();
		if(ecr!=null&&!ecr.isEmpty()) {
			for(EamCheckRepair r:ecr) {
				if(StringUtils.isBlank(r.getTaskKey())) {
					r.setTaskKey(t.getKey());
					baseCommonService.saveOrUpdate(r);
				}
			}
		}
		
		List<EamCheckReserveUsed> eru=t.getEamCheckReserveUsed();
		if(eru!=null&&!eru.isEmpty()) {
			for(EamCheckReserveUsed u:eru) {
				if(StringUtils.isBlank(u.getTaskKey())) {
					u.setTaskKey(t.getKey());
					baseCommonService.saveOrUpdate(u);
				}
			}
		}
		
		flowBaseService.startProcess(t, t.getFlowProcessInfo());
	}

	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckTaskService#findAll(com.ehs.eam.checks.bean.CheckTaskQueryBean)  
	* @Function: EamCheckTaskServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月17日 上午11:17:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     chentm           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findAll(CheckTaskQueryBean query) {
		Pageable pb = PageRequest.of(query.getPage() - 1, query.getSize(), query.getSortForJpaQuery());
//		Page<EamCheckTask> plans= eamCheckTaskDao.findAllPlan(
//				new DataModel[] {DataModel.CREATE,DataModel.UPDATE},
//				SysAccessUser.get().getUserKey(),
//				query.getTimes(),
//				query.getOwners(),
//				query.getChecks(),
//				query.getDefects(),
//				query.getRevers(),
//				query.getFlowstatus(),
//				pb);
//		if(plans!=null) {
//			PageInfoBean pib=new PageInfoBean();
//			pib.setDataList(plans.getContent());
//			pib.setTotalCount(plans.getTotalElements());
//			return pib;
//		}
		return null;
	}

}
