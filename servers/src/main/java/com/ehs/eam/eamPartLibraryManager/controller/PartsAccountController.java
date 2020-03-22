package com.ehs.eam.eamPartLibraryManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsAccountQueryBean;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:11:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/partsAccount")
public class PartsAccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartsAccountController.class);
	
	@Resource
	private PartsAccountService partsAccountService;
	
	/**
	 * 
	* @Function: PartsAccountController.java
	* @Description: 查找备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 上午10:27:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "/getPartsAccountAll")
	public String getPartsAccountAll(@RequestBody(required = false) PartsAccountQueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getPartsAccountAll方法=============");
		PageInfoBean pb = partsAccountService.findPartsAccountAll(queryBean);
		logger.info("===========退出getPartsAccountAll方法=============");
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}

}
