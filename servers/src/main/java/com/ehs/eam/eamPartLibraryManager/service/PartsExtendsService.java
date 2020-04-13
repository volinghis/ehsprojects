package com.ehs.eam.eamPartLibraryManager.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtendsService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午9:47:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
public interface PartsExtendsService {

	/**
	 * 
	* @Function: PartsExtendsService.java
	* @Description: 根据入库key查询所有备件信息并分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:48:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getExtendsByKey(QueryBean queryBean,String key);

	/**
	 * 
	* @Function: PartsExtendsService.java
	* @Description: 查询所有入库备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:49:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllEnterWareHouseParts(EnterWarehouseQueryBean queryBean);

	/**
	 * 
	* @Function: PartsExtendsService.java
	* @Description: 查询所有出库备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:49:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllOutWareHouseParts(OutWarehouseQueryBean queryBean);

	/**
	 * 
	* @Function: PartsExtendsService.java
	* @Description: 查询所有备件信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:49:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public List<PartsExtends> findByWareHouseKey(String wareHoseKey);

}
