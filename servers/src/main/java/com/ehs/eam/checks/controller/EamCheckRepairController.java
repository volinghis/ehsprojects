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
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.service.EamCheckRepairService;

@RestController
public class EamCheckRepairController {

	private static final Logger logger=LoggerFactory.getLogger(EamCheckRepairController.class);
	
	@Resource
	private EamCheckRepairService eamCheckRepairService;
	/**
	 * 
	* @Function: EamCheckRepairController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月24日 上午9:32:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月24日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/repairs/getRepairsByTaskKey")
	public String getRepairsByTaskKey(@RequestBody(required = false) CheckTaskQueryBean queryBean, HttpServletRequest request) {
		try {
			String taskKey=request.getParameter("taskKey");
			List<EamCheckRepair> repairs  =eamCheckRepairService.findByTaskKey(taskKey);
			return (repairs==null?"[]":JsonUtils.toJsonString(repairs));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "[]";
	}
}
