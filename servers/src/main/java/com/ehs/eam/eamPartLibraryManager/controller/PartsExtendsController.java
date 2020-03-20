package com.ehs.eam.eamPartLibraryManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtendsController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月18日 下午9:14:22 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月18日     zhaol          v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/eamPartsExtends")
public class PartsExtendsController {

	private static final Logger logger = LoggerFactory.getLogger(PartsExtendsController.class);
	
	@Resource
	private PartsExtendsService partsExtendsService;	
	
	@RequestAuth(menuKeys = {"enterWarehouseEdit",AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getExtendsByKey")
	public String getExtendsByKey(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getExtendsByKey方法=============");
		String key=request.getParameter("key");
		PageInfoBean pb = partsExtendsService.getExtendsByKey(queryBean,key);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/getAllEnterWareHouseParts")
	public String getAllEnterWareHouseParts(@RequestBody QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getAllEnterWareHouseParts方法=============");
		PageInfoBean pb = partsExtendsService.getAllEnterWareHouseParts(queryBean);
		System.out.println("pb====="+JsonUtils.toJsonString(pb));
		logger.info("pb====="+JsonUtils.toJsonString(pb));
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/getAllOutWareHouseParts")
	public String getAllOutWareHouseParts(@RequestBody QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getAllOutWareHouseParts方法=============");
//		String key=request.getParameter("key");
		PageInfoBean pb = partsExtendsService.getAllOutWareHouseParts(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
}
