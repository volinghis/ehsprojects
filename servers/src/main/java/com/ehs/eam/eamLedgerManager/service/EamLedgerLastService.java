package com.ehs.eam.eamLedgerManager.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.TreeDataBean;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;

public interface EamLedgerLastService {
	
	public PageInfoBean findEamLedgerLastList(EamLedgerQueryBean querybean);

	public PageInfoBean findLeftEamLedgerList(EamLedgerQueryBean querybean);

	/**   
	* @Function:findTreeForDevice 
	* @Description: 该函数的功能描述
	* @param parentKey
	* @param subKey
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月18日 下午5:12:58 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月18日     qjj        v1.0.0            修改原因
	*/
	public List<TreeDataBean> findTreeForDevice(String parentKey, String subKey);

	/**   
	* @Function:getChildDevByKey 
	* @Description: 该函数的功能描述
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月23日 下午7:49:40 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月23日     qjj        v1.0.0            修改原因
	*/
	public List<EamLedgerLast> getChildDevByKey(String key);

	/**   
	* @Function:findEamLedgerByProcessKey 
	* @Description: 该函数的功能描述
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月24日 下午5:48:42 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月24日     qjj        v1.0.0            修改原因
	*/
	public EamLedgerLast findEamLedgerByRefKey(String key);

}
