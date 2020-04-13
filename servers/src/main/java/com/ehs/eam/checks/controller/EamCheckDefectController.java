package com.ehs.eam.checks.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.eam.checks.entity.EamCheckDefect;
import com.ehs.eam.checks.service.EamCheckDefectService;

@RestController
public class EamCheckDefectController {
	private static final Logger logger=LoggerFactory.getLogger(EamCheckDefectController.class);
	
	@Resource
	private EamCheckDefectService eamCheckDefectService;
	/**
	 * 
	* @Function: EamCheckRepairController.java
	* @Description: 根据巡检任务获取其所有缺陷记录
	*
	* @param:taskKey 巡检任务唯一标识
	* @return：json格式缺陷记录集合
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
	@RequestMapping(value = "/eam/checks/defects/getDefectsByTaskKey")
	public String getDefectsByTaskKey( HttpServletRequest request) {
		try {
			String taskKey=request.getParameter("taskKey");
			List<EamCheckDefect> defects  =eamCheckDefectService.findByTaskKey(taskKey);
			return (defects==null?"[]":JsonUtils.toJsonString(defects));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "[]";
	}
}
