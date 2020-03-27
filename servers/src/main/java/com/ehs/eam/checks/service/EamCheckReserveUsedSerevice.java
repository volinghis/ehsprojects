package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;


/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsedSerevice.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月27日 上午10:19:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月27日     zhaol           v1.0.0               修改原因
*/
public interface EamCheckReserveUsedSerevice {

	/**
	 * 
	* @Function: EamCheckReserveUsedSerevice.java
	* @Description: 查询使用备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:19:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey);

	/**
	 * 
	* @Function: EamCheckReserveUsedSerevice.java
	* @Description: 流程结束回调
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:18:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	public void updatePartsAfterFlow(FlowProcessInfo flowProcessInfo);

}
