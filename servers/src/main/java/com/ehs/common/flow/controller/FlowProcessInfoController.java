package com.ehs.common.flow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;

@RestController
public class FlowProcessInfoController {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @Function: FlowProcessInfoController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月17日 下午3:45:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/flow/flowProcessInfo/findFlowProcessInfoByKey")
	public String findFlowProcessInfoByKey(HttpServletRequest request) {
		String key=request.getParameter("key");
		FlowProcessInfo ou=baseCommonService.findByKey(FlowProcessInfo.class, key);
		return ou==null?"{}":JsonUtils.toJsonString(ou);
	}
	
}
