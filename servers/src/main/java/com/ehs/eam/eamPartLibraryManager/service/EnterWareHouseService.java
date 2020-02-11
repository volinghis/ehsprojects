package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;

public interface EnterWareHouseService {

	public PageInfoBean findAll(QueryBean queryBean);

	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean);

	public void updatePartsAccount(FlowProcessInfo flowProcessInfo);

	public EnterWareHouseFlowBean getEnterWareHouseFlowBean(String key);

	public EnterWareHouse getEnterWareHouseByKey(String key);

}
