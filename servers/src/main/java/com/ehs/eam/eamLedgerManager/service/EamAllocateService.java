/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:45:33 
 */
package com.ehs.eam.eamLedgerManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamAllocateService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月7日 下午7:45:33 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月7日     qjj           v1.0.0               修改原因
*/
public interface EamAllocateService {

	/**   
	* @Function:saveEamAllocate 
	* @Description: 该函数的功能描述
	* @param reqBean
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月8日 下午7:08:05 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月8日     qjj        v1.0.0            修改原因
	*/
	void saveEamAllocate(EamAllocateRequestBean reqBean);

	/**   
	* @Function:findEamAllocateList 
	* @Description: 该函数的功能描述
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	 * @param scrapQueryBean 
	* @date: 2020年1月9日 上午10:55:04 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月9日     qjj        v1.0.0            修改原因
	*/
	PageInfoBean findEamAllocateList(EamAllocateQueryBean scrapQueryBean);

	/**   
	* @Function:deleteEamAllocates 
	* @Description: 删除设备调拨申请
	* @param keys
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月10日 下午3:20:24 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月10日     qjj        v1.0.0            修改原因
	*/
	void deleteEamAllocates(String keys);


}
