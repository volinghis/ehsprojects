package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsAccountQueryBean;
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
public interface PartsAccountService {
	
	/**
	 * 
	* @Function: PartsAccountService.java
	* @Description: 根据不同条件查找备件信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月22日 下午11:04:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月22日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findPartsAccountAll(PartsAccountQueryBean queryBean);

	/**
	 * 
	* @Function: PartsAccountService.java
	* @Description:查找备件台账
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月23日 下午4:05:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月23日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllPartsAccount(QueryBean queryBean);

}
