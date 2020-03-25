/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.controller 
 * @author: qjj   
 * @date: 2020年3月25日 下午1:52:33 
 */
package com.ehs.eam.checks.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.checks.bean.CheckRepairLedgerBean;
import com.ehs.eam.checks.service.EamCheckRepairLedgerService;



/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckRepairLedgerController.java
* @Description: 检修台账控制类
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月25日 下午1:52:33 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月25日     qjj           v1.0.0               修改原因
*/
@RestController
@RequestMapping("/eam/repairLedger")
public class EamCheckRepairLedgerController {

	private static final Logger logger=LoggerFactory.getLogger(EamCheckRepairLedgerController.class);
	
	@Resource
	private EamCheckRepairLedgerService ledgerService;
	
	/**
	 * 
	* @Function:getRepairLedgerList 
	* @Description: 获取所有检修台账
	* @param repairLedgerBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月25日 下午2:14:15 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月25日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getRepairLedgerList")
	public String getRepairLedgerList(@RequestBody CheckRepairLedgerBean repairLedgerBean) {
		try {
			PageInfoBean pb=ledgerService.findAllRepairLedger(repairLedgerBean);
			return pb!=null?JsonUtils.toJsonString(pb):"[]";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "[]";
	}
	
	/**
	 * 
	* @Function:updateAfterFlow 
	* @Description: 检修任务通过后的台账数据插入
	* @param flowProcessInfo
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月25日 下午2:25:43 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月25日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"repairLedger"})
	@RequestMapping(value = "/addDatasAfterFlow")
	public String addDatasAfterFlow(@RequestBody FlowProcessInfo flowProcessInfo) {
		try {
			ledgerService.addDatasAfterFlow(flowProcessInfo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(new ResultBean().ok("数据更新成功"));
	}
}
