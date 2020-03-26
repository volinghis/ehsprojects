package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckPlanQueryBean;
import com.ehs.eam.checks.entity.EamCheckPlan;

public interface EamCheckPlanService {

	public PageInfoBean findPlans(CheckPlanQueryBean queryBean);

	public EamCheckPlan changeState(EamCheckPlan plan);

	public void delayDate(String key, String newDate);
	
	public void sendTask(EamCheckPlan ecp);
	
	public List<EamCheckPlan> getAllPlanOfEnable();
}
