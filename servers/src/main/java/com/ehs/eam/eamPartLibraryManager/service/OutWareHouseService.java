package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;

public interface OutWareHouseService {

	public PageInfoBean findAll(QueryBean queryBean);

	public void saveOutWareHouse(OutWareHouserBean wareHouserBean);

	public int validAmount(String amount, String deviceCode, String price);

	public void updatePartsAccount(FlowProcessInfo flowProcessInfo);

	public EnterWareHouseFlowBean getOutWareHouseFlowBean(String key);

	public OutWareHouse getOutWareHouseByKey(String key);

}
