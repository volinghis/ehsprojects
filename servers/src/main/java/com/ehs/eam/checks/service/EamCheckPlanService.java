package com.ehs.eam.checks.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckPlanQueryBean;

public interface EamCheckPlanService {

	public PageInfoBean findPlans(CheckPlanQueryBean queryBean);
}
