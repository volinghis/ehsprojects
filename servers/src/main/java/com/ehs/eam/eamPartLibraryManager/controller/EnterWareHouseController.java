package com.ehs.eam.eamPartLibraryManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.service.EnterWareHouseService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouseController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午9:23:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/eamEnterWareHouse")
public class EnterWareHouseController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnterWareHouseController.class);
	
	@Resource
	private EnterWareHouseService ewhService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@RequestAuth(menuKeys = {"enterWarehouse"})
	@RequestMapping(value = "/getAll")
	public String getAll(@RequestBody(required = false) QueryBean queryBean, HttpServletRequest request) {
		logger.info("查询所有入库");
		PageInfoBean pb = ewhService.findAll(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/saveEnterWareHouse")
	public String saveWareEnterHouse(@RequestBody EnterWareHouserBean wareHouserBean, HttpServletRequest request) {
		logger.info("开始入库流程");
		ResultBean resultBean=new ResultBean();
		try {
			ewhService.saveEnterWareHouse(wareHouserBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，备件入库成功 ！"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，备件入库失败！"));
	}
	
	
	@RequestAuth(menuKeys = {"enterWarehouseEdit",AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/updateAfterFlow")
	public String updatePartAccount(@RequestBody FlowProcessInfo flowProcessInfo) {
		ResultBean resultBean = new ResultBean();
		try {
			ewhService.updatePartsAccount(flowProcessInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("数据更新失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("数据更新成功"));
		
	}
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/getEnterWareHouseFlowBean")
	public String getEnterWareHouseFlowBean(@RequestParam String key) {
		EnterWareHouseFlowBean ewhFlowBean=	ewhService.getEnterWareHouseFlowBean(key);
		return ewhFlowBean!=null?JsonUtils.toJsonString(ewhFlowBean):"{}";
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getEnterWareHouseByKey")
	public String getEnterWareHouseByKey(@RequestParam String key) {
		try {
			if(StringUtils.isBlank(key)) {
				return "{}";
			}
			EnterWareHouse enterWareHouse=baseCommonService.findByKey(EnterWareHouse.class, key);
			return (enterWareHouse==null?"{}":JsonUtils.toJsonString(enterWareHouse));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "{}";
	}
	
}
