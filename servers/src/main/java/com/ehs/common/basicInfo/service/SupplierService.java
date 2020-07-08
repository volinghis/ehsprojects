package com.ehs.common.basicInfo.service;

import com.ehs.common.basicInfo.bean.SupplierBean;
import com.ehs.common.basicInfo.bean.SupplierQueryBean;
import com.ehs.common.basicInfo.entity.SupplierInfo;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:34:56 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
public interface SupplierService {

	/**
	 * 
	* @Function: SupplierService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月8日 上午9:35:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月8日     zhaol           v1.0.0               修改原因
	 */
	public void saveSupplier(SupplierBean supplierBean);

	/**
	 * 
	* @Function: SupplierService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月8日 上午9:35:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月8日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findSuppliers(SupplierQueryBean supplierQueryBean);
	
	/**
	 * 
	* @Function: SupplierService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月8日 上午9:35:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月8日     zhaol           v1.0.0               修改原因
	 */
	public void deleteSupplierByKey(String key);

	/**
	 * 
	* @Function: SupplierService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月8日 上午9:35:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月8日     zhaol           v1.0.0               修改原因
	 */
	public SupplierInfo changeState(SupplierInfo supplierInfo);
}
