package com.ehs.eam.eamPartLibraryManager.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

public interface PartsExtendsService {

	public PageInfoBean getExtendsByKey(QueryBean queryBean,String key);

	public PageInfoBean getAllEnterWareHouseParts(EnterWarehouseQueryBean queryBean);

	public PageInfoBean getAllOutWareHouseParts(OutWarehouseQueryBean queryBean);

	public List<PartsExtends> findByWareHouseKey(String wareHoseKey);

}
