package com.ehs.eam.eamPartLibraryManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;
import com.ehs.eam.eamPartLibraryManager.service.PartsParamsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsParamsController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月3日 下午1:37:18 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月3日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/partsParams")
public class PartsParamsController {
	
	@Resource
	private PartsParamsService partsParamsService;

	/**
	 * 
	* @Function: PartsParamsController.java
	* @Description:  根据paramKey查找所有params
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 下午1:37:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "getAllPartsParamByKey")
	public String getAllPartsParamByKey(HttpServletRequest request) {
		String key = request.getParameter("key");
		List<PartsParam> params =partsParamsService.getAllPartsParamByKey(key);
		if (params != null && params.size() > 0) {
			return JsonUtils.toJsonString(params);
		}
		return null;
	}
}
