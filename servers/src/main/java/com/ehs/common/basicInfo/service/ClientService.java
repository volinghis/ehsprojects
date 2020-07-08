package com.ehs.common.basicInfo.service;

import com.ehs.common.basicInfo.bean.ClientBean;
import com.ehs.common.basicInfo.bean.ClientQueryBean;
import com.ehs.common.basicInfo.entity.ClientInfo;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientService.java
* @Description: 客户信息接口
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 上午11:12:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
public interface ClientService {

	/**
	 * 
	* @Function: ClientService.java
	* @Description: 保存客户信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月7日 上午11:13:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月7日     zhaol           v1.0.0               修改原因
	 */
	public void saveClient(ClientBean clientBean);

	/**
	 * 
	* @Function: ClientService.java
	* @Description: 查看所有客户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月7日 下午2:04:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月7日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findClients(ClientQueryBean clientQueryBean);

	/**
	 * 
	* @Function: ClientService.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月7日 下午2:52:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月7日     zhaol           v1.0.0               修改原因
	 */
	public void deleteClientByKey(String key);

	/**
	 * 
	* @Function: ClientService.java
	* @Description: 修改状态
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月7日 下午4:36:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月7日     zhaol           v1.0.0               修改原因
	 */
	public ClientInfo changeState(ClientInfo clientInfo);

}
