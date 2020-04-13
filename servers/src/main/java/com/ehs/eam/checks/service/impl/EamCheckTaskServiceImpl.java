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
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckTaskAnalysisBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.dao.EamCheckTaskDao;
import com.ehs.eam.checks.entity.EamCheckDefect;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;
import com.ehs.eam.checks.entity.EamCheckTask;
import com.ehs.eam.checks.service.EamCheckTaskService;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

@Service
public class EamCheckTaskServiceImpl implements EamCheckTaskService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private EamCheckTaskDao eamCheckTaskDao;
	
	@Resource
	private PartsAccountDao partsAccountDao;
	
	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckTaskService#saveTask(com.ehs.eam.checks.entity.EamCheckTask)  
	* @Function: EamCheckTaskServiceImpl.java
	* @Description: 该函数的功能描述
	*
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
		if(StringUtils.isBlank(t.getUser())) {
			t.setUser(SysAccessUser.get().getUserKey());
			t.setUserName(SysAccessUser.get().getUsername());
			t.setOrg(SysAccessUser.get().getOrgKey());
			t.setOrgName(SysAccessUser.get().getOrgName());
		}
		t.setDefects(false);
		t.setRepairs(false);
		t.setReserves(false);
		if(des!=null&&!des.isEmpty()) {
			for(EamCheckDefect d :des) {
				if(!StringUtils.equals(t.getResult(), "NORMAL")) {
					if(StringUtils.isNotBlank(d.getId())) {
						baseCommonService.deleteByKey(EamCheckDefect.class, d.getKey());
					}
					continue;
				}
				
				if(StringUtils.isNotBlank(d.getId())&&d.isDeleted()) {
					baseCommonService.deleteByKey(EamCheckDefect.class,d.getKey());
				}
				if(!d.isDeleted()) {
					t.setDefects(true);
					if(StringUtils.isBlank(d.getTaskKey())) {
						d.setTaskKey(t.getKey());
					}
					baseCommonService.saveOrUpdate(d);
				}
			}
		}
		
		List<EamCheckRepair> ecr=t.getEamCheckRepair();
		if(ecr!=null&&!ecr.isEmpty()) {
			for(EamCheckRepair r:ecr) {
				if(!StringUtils.equals(t.getResult(), "NORMAL")) {
					if(StringUtils.isNotBlank(r.getId())) {
						baseCommonService.deleteByKey(EamCheckRepair.class, r.getKey());
					}
					continue;
				}
				if(StringUtils.equals(r.getUserType(), "OWNER")) {
					r.setUser(SysAccessUser.get().getUserKey());
					r.setUserName(SysAccessUser.get().getUsername());
					r.setOrg(SysAccessUser.get().getOrgKey());
					r.setOrgName(SysAccessUser.get().getOrgName());
				}
				if(StringUtils.isNotBlank(r.getId())&&r.isDeleted()) {
					baseCommonService.deleteByKey(EamCheckRepair.class,r.getKey());
				}
				if(!r.isDeleted()) {
					t.setRepairs(true);
					if(StringUtils.isBlank(r.getTaskKey())) {
						r.setTaskKey(t.getKey());
					}
					baseCommonService.saveOrUpdate(r);
				}
			}
		}

		List<EamCheckReserveUsed> eru=t.getEamCheckReserveUsed();
		if(eru!=null&&!eru.isEmpty()) {
			for(EamCheckReserveUsed u:eru) {
				PartsAccount partsAccount =partsAccountDao.getAccountBywareHouseAndDeviceCode(u.getWareHouse(), u.getDeviceCode());
				partsAccount.setDummyAmount(partsAccount.getDummyAmount()-u.getAmount());
				baseCommonService.saveOrUpdate(partsAccount);
				
				if(!StringUtils.equals(t.getResult(), "NORMAL")) {
					if(StringUtils.isNotBlank(u.getId())) {
						baseCommonService.deleteByKey(EamCheckReserveUsed.class, u.getKey());
					}
					continue;
				}
	
				if(StringUtils.isNotBlank(u.getId())&&u.isDeleted()) {
					baseCommonService.deleteByKey(EamCheckReserveUsed.class,u.getKey());
				}
				if(!u.isDeleted()) {
					t.setReserves(true);
					if(StringUtils.isBlank(u.getTaskKey())) {
						u.setTaskKey(t.getKey());
					}
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
		Page<EamCheckTask> plans= eamCheckTaskDao.findAllPlan(
				SysAccessUser.get().getUserKey(),
				query.getTimes(),
				query.getOwners(),
				query.getChecks(),
				query.getDefects(),
				query.getRevers(),
				query.getFlowstatus(),
				query.getExecuteResult(),
				pb);
		if(plans!=null) {
			PageInfoBean pib=new PageInfoBean();
			pib.setDataList(plans.getContent());
			pib.setTotalCount(plans.getTotalElements());
			return pib;
		}
		return null;
	}

	@Override
	public List<CheckTaskAnalysisBean> analysisTaskForOrg() {
		return eamCheckTaskDao.analysisTaskForOrg();
	}

}
