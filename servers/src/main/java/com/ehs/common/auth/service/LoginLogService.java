/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.service 
 * @author: qjj   
 * @date: 2020年3月6日 下午1:08:41 
 */
package com.ehs.common.auth.service;

import com.ehs.common.auth.bean.LoginLogBean;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月6日 下午1:08:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     qjj           v1.0.0               修改原因
*/
public interface LoginLogService {

	public PageInfoBean findLoginLogList(LoginLogBean loginLogBean);
	
	public 	void addLoginLog(String userKey, String ip,String browser,String system);

	/**   
	* @Function:deleteLoginLogs 
	* @Description: 该函数的功能描述
	* @param keys
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月6日 下午4:09:14 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月6日     qjj        v1.0.0            修改原因
	*/
	public void deleteLoginLogs(String keys);
}
