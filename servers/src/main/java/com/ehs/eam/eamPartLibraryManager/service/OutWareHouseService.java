package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamPartLibraryManager.bean.WareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWareHouseService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月22日 下午11:18:59 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月22日     zhaol          v1.0.0               修改原因
*/
public interface OutWareHouseService {

	/**
	 * 
	* @Function: OutWareHouseService.java
	* @Description: 开始出库流程
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:40:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public void saveOutWareHouse(OutWareHouserBean wareHouserBean);

	/**
	 * 
	* @Function: OutWareHouseService.java
	* @Description: 出库成功流程回调
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:41:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public void updatePartsAccount(FlowProcessInfo flowProcessInfo);

	/**
	 * 
	* @Function: OutWareHouseService.java
	* @Description: 更新流程信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:41:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public WareHouseFlowBean getOutWareHouseFlowBean(String key);

}
