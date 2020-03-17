package com.ehs.eam.checks.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.entity.EamCheckTask;

public interface EamCheckTaskService {

	public PageInfoBean findAll(CheckTaskQueryBean query);
	
	public void saveTask(EamCheckTask t);
}
