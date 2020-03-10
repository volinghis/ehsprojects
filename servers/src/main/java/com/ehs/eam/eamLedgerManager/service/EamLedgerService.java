/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:06:14 
 */
package com.ehs.eam.eamLedgerManager.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午4:06:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
public interface EamLedgerService {

	/**   
	* @Function:findEamLedgerList 
	* @Description: 该函数的功能描述
	* @param querybean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月30日 下午4:07:10 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月30日     qjj        v1.0.0            修改原因
	*/
    public PageInfoBean findEamLedgerList(EamLedgerQueryBean querybean);

	
	public void saveEamLedger(EamRequestBean eamRequestBean);
	
	public List<EamParameters> getEamParametersByKey(String code);


	/**   
	* @Function:getInspectorsByKey 
	* @Description: 该函数的功能描述
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月6日 下午1:29:26 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月6日     qjj        v1.0.0            修改原因
	*/
	List<EamInspectors> getInspectorsByKey(String key);


	/**   
	* @Function:getChildDevByKey 
	* @Description: 该函数的功能描述
	* @param deviceKey
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	 * @return 
	* @date: 2020年1月6日 下午8:47:11 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月6日     qjj        v1.0.0            修改原因
	*/
	List<EamLedger> getChildDevByKey(String deviceKey);

	/**   
	* @Function:saveRelatedDevices 
	* @Description: 该函数的功能描述
	* @param deviceKey
	* @param keys
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月7日 下午1:33:07 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月7日     qjj        v1.0.0            修改原因
	*/
	void saveRelatedDevices(String deviceKey, String keys);


	/**   
	* @Function:removeRelatedEamLedgers 
	* @Description: 该函数的功能描述
	* @param devicekey
	* @param keys
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月7日 下午4:44:04 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月7日     qjj        v1.0.0            修改原因
	*/
	void removeRelatedEamLedgers(String devicekey, String keys);


	public PageInfoBean findEamLedgersNotInFlow(EamLedgerQueryBean querybean);


	/**   
	* @Function:deleteEamLedger 
	* @Description: 该函数的功能描述
	* @param key
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月10日 上午11:14:58 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月10日     qjj        v1.0.0            修改原因
	*/
	public void deleteEamLedger(String key);
}
