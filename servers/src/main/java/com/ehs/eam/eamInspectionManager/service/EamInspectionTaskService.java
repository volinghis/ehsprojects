package com.ehs.eam.eamInspectionManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

public interface EamInspectionTaskService {

	public void saveTask(InspectionTaskBean taskBean);

	public PageInfoBean findAllTask(QueryBean queryBean);

}
