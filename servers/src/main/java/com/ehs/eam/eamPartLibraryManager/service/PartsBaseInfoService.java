package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:08:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
public interface PartsBaseInfoService {

	public void savePartsBaseInfo(PartsInfoBean partsBean);
	
	public PageInfoBean getPartsBaseInfos(QueryBean queryBean);

	public void deletePartsByKey(String key);
}
