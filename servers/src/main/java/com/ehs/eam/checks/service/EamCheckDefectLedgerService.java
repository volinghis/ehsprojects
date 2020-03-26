/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.service 
 * @author: qjj   
 * @date: 2020年3月26日 上午10:50:32 
 */
package com.ehs.eam.checks.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckDefectLedgerBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckDefectLedgerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月26日 上午10:50:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月26日     qjj           v1.0.0               修改原因
*/
public interface EamCheckDefectLedgerService {

	/**   
	* @Function:findAllDefectLedgers 
	* @Description: 该函数的功能描述
	* @param defectLedgerBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月26日 上午11:05:20 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月26日     qjj        v1.0.0            修改原因
	*/
	PageInfoBean findAllDefectLedgers(CheckDefectLedgerBean defectLedgerBean);

	/**   
	* @Function:addDatasAfterFlow 
	* @Description: 该函数的功能描述
	* @param flowProcessInfo
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月26日 上午11:14:15 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月26日     qjj        v1.0.0            修改原因
	*/
	void addDatasAfterFlow(FlowProcessInfo flowProcessInfo);

}
