/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:45:33 
 */
package com.ehs.eam.eamLedgerManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamScrapService.java
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
public interface EamScrapService {

	/**   
	* @Function:saveEamScrap 
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
	void saveEamScrap(EamScrapRequestBean reqBean);

	/**   
	* @Function:findEamScrapList 
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
	PageInfoBean findEamScrapList(EamScrapQueryBean scrapQueryBean);

	/**   
	* @Function:deleteEamScraps 
	* @Description: 删除设备报废申请
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
	void deleteEamScraps(String keys);


	/**   
	* @Function:findScrapFlowBean 
	* @Description: 该函数的功能描述
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月14日 下午8:12:19 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月14日     qjj        v1.0.0            修改原因
	*/
    public EamFlowBean findScrapFlowBean(String key);

    /**
            *  流程走完后数据的更新处理
     * @param flowProcessInfo 
     */
	public void updateRelatedAfterFlow(FlowProcessInfo flowProcessInfo);
}
