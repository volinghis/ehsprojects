package com.ehs.eam.checks.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.SortBean;
import com.ehs.eam.checks.bean.CheckPlanQueryBean;
import com.ehs.eam.checks.dao.EamCheckPlanDao;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.service.EamCheckPlanService;

@Service
public class EamCheckPlanServiceImpl implements EamCheckPlanService {

	@Resource
	private EamCheckPlanDao eamCheckPlanDao;

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

}
