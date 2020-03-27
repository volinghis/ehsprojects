package com.ehs.eam.checks.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;
import com.ehs.eam.checks.service.EamCheckReserveUsedSerevice;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsedController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月25日 下午3:08:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月25日     zhaol           v1.0.0               修改原因
*/
@RestController
public class EamCheckReserveUsedController {
	
	private static final Logger logger=LoggerFactory.getLogger(EamCheckReserveUsedController.class);
	
	@Resource
	private EamCheckReserveUsedSerevice reserveUsedSerevice;

	/**
	 * 
	* @Function: EamCheckReserveUsedController.java
	* @Description: 查询任务领用所有备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:43:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/reserveUsed/getReservUsedByTaskKey")
	public String getReservUsedByTaskKey( HttpServletRequest request) {
		try {
			String taskKey=request.getParameter("taskKey");
			List<EamCheckReserveUsed> reserveUseds  =reserveUsedSerevice.findReserveUsedByTaskKey(taskKey);
			return (reserveUseds==null?"[]":JsonUtils.toJsonString(reserveUseds));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "[]";
	}
	
	/**
	 * 
	* @Function: EamCheckReserveUsedController.java
	* @Description: 巡检任务领用备件回调
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:43:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/reserveUsed/updatePartsAfterFlow")
	public void updatePartsAfterFlow(@RequestBody FlowProcessInfo flowProcessInfo) {
		try {
			reserveUsedSerevice.updatePartsAfterFlow(flowProcessInfo);
		} catch (Exception e) {
			logger.error("巡检任务--领用备件--回调错误："+e.getMessage());
		}
	}
	
}
