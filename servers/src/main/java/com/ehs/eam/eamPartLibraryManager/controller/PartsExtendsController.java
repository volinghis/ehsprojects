package com.ehs.eam.eamPartLibraryManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
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
	
	@Resource
	private PartsExtendsDao partsDao;
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getExtendsByKey")
	public String getExtendsByKey(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getExtendsByKey方法=============");
		String key=request.getParameter("key");
		PageInfoBean pb = partsExtendsService.getExtendsByKey(queryBean,key);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getExtendsByWarehouseKey")
	public String getExtendsByWarehouseKey(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入---getExtendsByWarehouseKey方法=============");
		try {
			String key=request.getParameter("key");
			List<PartsExtends> parts = partsDao.getAllByWareHouseKey(key);
			logger.info("===========退出---getExtendsByWarehouseKey方法=============");
			return (parts==null?"[]":JsonUtils.toJsonString(parts));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "[]";
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getAllEnterWareHouseParts")
	public String getAllEnterWareHouseParts(@RequestBody EnterWarehouseQueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入---getAllEnterWareHouseParts方法=============");
		PageInfoBean pb;
		try {
			pb = partsExtendsService.getAllEnterWareHouseParts(queryBean);
			logger.info("===========退出---getAllEnterWareHouseParts方法=============");
			return (pb==null?"[]":JsonUtils.toJsonString(pb));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "[]";
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getAllOutWareHouseParts")
	public String getAllOutWareHouseParts(@RequestBody OutWarehouseQueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入---getAllOutWareHouseParts方法=============");
		try {
			PageInfoBean pb = partsExtendsService.getAllOutWareHouseParts(queryBean);
			logger.info("===========退出---getAllOutWareHouseParts方法=============");
			return (pb==null?"[]":JsonUtils.toJsonString(pb));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "[]";
	}
	
}
