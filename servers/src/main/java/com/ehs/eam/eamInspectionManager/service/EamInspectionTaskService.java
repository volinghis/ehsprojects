package com.ehs.eam.eamInspectionManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionTaskService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:28:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
public interface EamInspectionTaskService {

	/**
	 * 
	* @Function: EamInspectionTaskService.java
	* @Description: 保存任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:29:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	public void saveTask(InspectionTaskBean taskBean);

	/**
	 * 
	* @Function: EamInspectionTaskService.java
	* @Description: 查找任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:29:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findAllTask(QueryBean queryBean);

}
