package com.ehs.common.organization.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.OrgQueryBean;
import com.ehs.common.organization.entity.OrganizationInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月20日 上午8:59:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月20日     zhaol           v1.0.0               修改原因
*/
public interface OrganizationService {
	
	/**
	 * 
	* @Function: OrganizationService.java
	* @Description: 保存部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:20:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public void saveOrg(OrganizationInfo orgInfo);

	/**
	 * 
	* @Function: OrganizationService.java
	* @Description: 删除部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:20:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public void deleteOrgByKey(String key);

	/**
	 * 
	* @Function: OrganizationService.java
	* @Description: 查询所有部门并分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:20:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllOrgsTable(String orgParentKey, OrgQueryBean queryBean);

	/**
	 * 
	* @Function: OrganizationService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月27日 下午9:15:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月27日     zhaol           v1.0.0               修改原因
	 */
	public OrganizationInfo getFirstNode();

	/**
	 * 
	* @Function: OrganizationService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月27日 下午9:15:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月27日     zhaol           v1.0.0               修改原因
	 */
	public List<OrganizationInfo> findIdByChildren(String id);

}
