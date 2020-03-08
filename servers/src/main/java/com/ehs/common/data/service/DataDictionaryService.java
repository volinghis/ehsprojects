package com.ehs.common.data.service;


import java.util.List;


import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.OrgQueryBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月6日 下午3:19:53 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     zhaol           v1.0.0               修改原因
*/
public interface DataDictionaryService {

	/**
	 * 
	* @Function: DataDictionaryService.java
	* @Description:查找父节点为空的数据，也就是一级菜单
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	 * @param key 
	* @date: 2020年3月6日 下午3:31:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	public List<DataDictionary> getFirstNode(String parentCode);

	/**
	 * 根据父节点查找子节点数据
	* @Function: DataDictionaryService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午3:32:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllDatasTable(String parentCode, OrgQueryBean queryBean);

	/**
	 * 
	* @Function: DataDictionaryService.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午4:25:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	public void saveDataDictionary(DataDictionary data);

	/**
	 * 
	* @Function: DataDictionaryService.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午4:39:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	public void deleteOrgByKey(String key);


}
