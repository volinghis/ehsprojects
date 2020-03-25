/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.service 
 * @author: qjj   
 * @date: 2020年3月25日 下午2:07:43 
 */
package com.ehs.eam.checks.service;

import org.springframework.data.domain.Page;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckRepairLedgerBean;
import com.ehs.eam.checks.entity.EamCheckRepairLedger;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckRepairLedgerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月25日 下午2:07:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月25日     qjj           v1.0.0               修改原因
*/
public interface EamCheckRepairLedgerService{

	/**   
	* @Function:findAllRepairLedger 
	* @Description: 该函数的功能描述
	* @param repairLedgerBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月25日 下午2:08:47 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月25日     qjj        v1.0.0            修改原因
	*/
	PageInfoBean findAllRepairLedger(CheckRepairLedgerBean queryBean);


	/**   
	* @Function:addDatasAfterFlow 
	* @Description: 该函数的功能描述
	* @param flowProcessInfo
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月25日 下午2:27:12 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月25日     qjj        v1.0.0            修改原因
	*/
	void addDatasAfterFlow(FlowProcessInfo flowProcessInfo);
	
}
