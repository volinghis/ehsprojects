package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamPartLibraryManager.bean.WareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouseService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月22日 下午11:13:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月22日     zhaol          v1.0.0               修改原因
*/
public interface EnterWareHouseService {
	
	/**
	 * 
	* @Function: EnterWareHouseService.java
	* @Description: 保存入库信息，开始入库流程
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月22日 下午11:13:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月22日     zhaol           v1.0.0               修改原因
	 */
	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean);

	/**
	 * 
	* @Function: EnterWareHouseService.java
	* @Description: 流程回调
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月22日 下午11:14:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月22日     zhaol           v1.0.0               修改原因
	 */
	public void updatePartsAccount(FlowProcessInfo flowProcessInfo);

	/**
	 * 
	* @Function: EnterWareHouseService.java
	* @Description: 更新流程信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月22日 下午11:14:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月22日     zhaol           v1.0.0               修改原因
	 */
	public WareHouseFlowBean getEnterWareHouseFlowBean(String key);

}
