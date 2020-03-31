package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckTaskAnalysisBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.entity.EamCheckTask;

public interface EamCheckTaskService {

	public PageInfoBean findAll(CheckTaskQueryBean query);
	
	public void saveTask(EamCheckTask t);
	
	public List<CheckTaskAnalysisBean> analysisTaskForOrg();

}
