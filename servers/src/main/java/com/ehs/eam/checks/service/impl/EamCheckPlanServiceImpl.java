package com.ehs.eam.checks.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrgUserService;
import com.ehs.common.organization.service.OrganizationService;
import com.ehs.eam.checks.bean.CheckPlanQueryBean;
import com.ehs.eam.checks.dao.EamCheckPlanDao;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.entity.EamCheckTask;
import com.ehs.eam.checks.service.EamCheckPlanService;

@Service
public class EamCheckPlanServiceImpl implements EamCheckPlanService {

	
	@Resource
	private BaseCommonService baseCommonService;
	
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private EamCheckPlanDao eamCheckPlanDao;


	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckPlanService#findPlans(com.ehs.eam.checks.bean.CheckPlanQueryBean)  
	* @Function: EamCheckPlanServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月16日 下午3:30:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月16日     chentm           v1.0.0               修改原因
	 */

	@Override
	public PageInfoBean findPlans(CheckPlanQueryBean query) {
		Pageable pb = PageRequest.of(query.getPage() - 1, query.getSize(), query.getSortForJpaQuery());
		Page<EamCheckPlan> plans= eamCheckPlanDao.findAllPlan(
				new DataModel[] {DataModel.CREATE,DataModel.UPDATE},
				query.getQuery(),
				SysAccessUser.get().getUserKey(),
				SysAccessUser.get().getOrgKey(),
				query.isByowner(),
				query.isEffective(),
				query.isEnable(),
				pb);
		if(plans!=null) {
			PageInfoBean pib=new PageInfoBean();
			pib.setDataList(plans.getContent());
			pib.setTotalCount(plans.getTotalElements());
			return pib;
		}
		return null;
	}

	/**
	 * 

	* @see com.ehs.eam.checks.service.EamCheckPlanService#sendTask(com.ehs.eam.checks.entity.EamCheckPlan)  
	* @Function: EamCheckPlanServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public EamCheckPlan changeState(EamCheckPlan plan) {
		try {
			EamCheckPlan eamCheckPlan = baseCommonService.findByKey(EamCheckPlan.class, plan.getKey());
			eamCheckPlan.setEnable(plan.getEnable());
			return baseCommonService.saveOrUpdate(eamCheckPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckPlanService#delayDate(java.lang.String, java.lang.String)  
	* @Function: EamCheckPlanServiceImpl.java
	* @Description: 时间延期
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月17日 上午10:40:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void delayDate(String key, String newDate) {
		try {
			EamCheckPlan plan = baseCommonService.findByKey(EamCheckPlan.class, key);
			Timestamp t = Timestamp.valueOf(newDate);
			plan.setEndTime(t);
			baseCommonService.saveOrUpdate(plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional
	@Override
	public void sendTask(EamCheckPlan ecp) {
		String checkor= ecp.getCheckor();
		String[] cs=StringUtils.split(checkor, ",");
		for(String s:cs) {
			OrganizationInfo oi= baseCommonService.findByKey(OrganizationInfo.class, s);
			if(oi!=null) {
			  List<OrgUser> orgUsers=	orgUserService.findUserByOrgKey(oi.getKey());
			  if(orgUsers!=null&&!orgUsers.isEmpty()) {
				  for(OrgUser o:orgUsers) {
					  EamCheckTask ect=new EamCheckTask();
					  ect.setName(ecp.getName()+o.getName()+"的巡检任务");
					  ect.setUser(o.getKey());
					  ect.setOrg(oi.getKey());
					  baseCommonService.saveOrUpdate(ect);
				  }
			  }
			}
		}

}
}
	