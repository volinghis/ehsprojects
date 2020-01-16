package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

public interface OutWareHouseService {

	public PageInfoBean findAll(QueryBean queryBean);

	public void saveOutWareHouse(OutWareHouserBean wareHouserBean);

	public int validAmount(String amount, String deviceCode, String price);

}
