package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

public interface EnterWareHouseService {

	public PageInfoBean findAll(QueryBean queryBean);

	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean);

}
