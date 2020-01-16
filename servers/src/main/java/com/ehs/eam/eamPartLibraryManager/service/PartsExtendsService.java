package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

public interface PartsExtendsService {

	public PageInfoBean getExtendsByKey(QueryBean queryBean,String key);

}
