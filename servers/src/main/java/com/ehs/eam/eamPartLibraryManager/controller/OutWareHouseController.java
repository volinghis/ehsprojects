package com.ehs.eam.eamPartLibraryManager.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.WareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.OutWareHouseService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWareHouseController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2020年2月11日 下午7:49:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月11日     Administrator           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/eamOutWarehouse")
public class OutWareHouseController {
	
	public static final Logger logger = LoggerFactory.getLogger(OutWareHouseController.class);
	
	@Resource
	private OutWareHouseService owhService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/saveOutWareHouse")
	public String saveWareEnterHouse(@RequestBody OutWareHouserBean wareHouserBean, HttpServletRequest request) {
		logger.info("=======准备进入出库流程==========");
		ResultBean resultBean=new ResultBean();
		try {
			owhService.saveOutWareHouse(wareHouserBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，备件出库流程创建成功 ！"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，备件出库流程创建失败！"));
	}

	@RequestAuth(menuKeys = {"outWarehouseEdit",AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/updateAfterFlow")
	public String updatePartAccount(@RequestBody FlowProcessInfo flowProcessInfo) {
		logger.info("========出库回调开始==========");
		ResultBean resultBean = new ResultBean();
		try {
			owhService.updatePartsAccount(flowProcessInfo);
			logger.info("========出库回调结束==========");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("数据更新失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("数据更新成功"));
		
	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/getOutWareHouseFlowBean")
	public String getOutWareHouseFlowBean(@RequestParam String key) {
		logger.info("======出库流程查看======");
		WareHouseFlowBean ewhFlowBean=	owhService.getOutWareHouseFlowBean(key);
		return ewhFlowBean != null ? JsonUtils.toJsonString(ewhFlowBean) : "{}";
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getOutWareHouseByKey")
	public String getOutWareHouseByKey(@RequestParam String key) {
		logger.info("===首页查看出库流程===");
		try {
			if(StringUtils.isBlank(key)) {
				return "{}";
			}
			OutWareHouse outWareHouse=baseCommonService.findByKey(OutWareHouse.class, key);
			return (outWareHouse==null?"{}":JsonUtils.toJsonString(outWareHouse));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "{}";
	}
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getLaveAmount")
	public String getLaveAmount(@RequestBody PartsExtends partsAccount) {
		logger.info("===查询剩余库存===");
		try {
			List<PartsAccount> partsAccounts = (List<PartsAccount>) baseCommonService.findAll(PartsAccount.class);
			List<PartsAccount> accounts =partsAccounts.stream().filter(s -> StringUtils.equals(s.getDeviceCode(), partsAccount.getDeviceCode())).collect(Collectors.toList());
			if (accounts != null && accounts.size() > 0) {
				for (PartsAccount account : accounts) {
					if(account.getPrice().compareTo(partsAccount.getPrice()) == 0) {
						return JsonUtils.toJsonString(account);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
}
