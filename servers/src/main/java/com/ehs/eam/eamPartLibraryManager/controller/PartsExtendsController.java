package com.ehs.eam.eamPartLibraryManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService;

@RestController
@RequestMapping(value = "/eam/eamPartsExtends")
public class PartsExtendsController {

	private static final Logger logger = LoggerFactory.getLogger(PartsExtendsController.class);
	
	@Resource
	private PartsExtendsService partsExtendsService;	
	
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/getExtendsByKey")
	public String getExtendsByKey(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getEamPartLibrary方法=============");
		String key=request.getParameter("key");
		PageInfoBean pb = partsExtendsService.getExtendsByKey(queryBean,key);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
}
