package com.ehs.eam.eamDataBase.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamDataBase.bean.EamDataBaseQuery;
import com.ehs.eam.eamDataBase.bean.EamDataReqBean;

public interface EamDataBaseServie {

	PageInfoBean findEamDataBaseList(EamDataBaseQuery querybean);

	/**   
	* @Function: EamDataBaseServie.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2020年2月19日 下午3:59:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月19日     Administrator           v1.0.0               修改原因
	*/
	void saveDataFileInfo(EamDataReqBean eamDataReqBean);
	
}
