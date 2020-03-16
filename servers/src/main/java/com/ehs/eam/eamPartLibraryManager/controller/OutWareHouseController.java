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

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
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
	
	@RequestAuth(menuKeys = {"outWarehouse"})
	@RequestMapping(value = "/getList")
	public String getList(@RequestBody QueryBean queryBean ,HttpServletRequest request) {
		PageInfoBean pb = owhService.findAll(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/saveOutWareHouse")
	public String saveWareEnterHouse(@RequestBody OutWareHouserBean wareHouserBean, HttpServletRequest request) {
		logger.info("=======准备进入出库流程==========");
		ResultBean resultBean=new ResultBean();
		try {
			owhService.saveOutWareHouse(wareHouserBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，备件出库成功 ！"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，备件出库失败！"));
	}

//	@RequestAuth(menuKeys = {"outWarehouseEdit"})
//	@RequestMapping(value = "/validAmount")
//	public String validAmount(HttpServletRequest request) {
//		String amount = request.getParameter("amount");
//		String deviceCode = request.getParameter("deviceCode");
//		String price = request.getParameter("price");
//		System.out.println("amount========="+amount);
//		System.out.println("deviceCode========="+deviceCode);
//		System.out.println("price========="+price);
//		int amountNew= owhService.validAmount(amount,deviceCode,price);
//		return JsonUtils.toJsonString(String.valueOf(amountNew));
//	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/updateAfterFlow")
	public String updatePartAccount(@RequestBody FlowProcessInfo flowProcessInfo) {
		ResultBean resultBean = new ResultBean();
		try {
			owhService.updatePartsAccount(flowProcessInfo);
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
		EnterWareHouseFlowBean ewhFlowBean=	owhService.getOutWareHouseFlowBean(key);
		return ewhFlowBean != null ? JsonUtils.toJsonString(ewhFlowBean) : "{}";
	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/getOutWareHouseByKey")
	public String getOutWareHouseByKey(@RequestParam String key) {
		logger.info("===登录页面查看流程进度===");
		OutWareHouse ewh= owhService.getOutWareHouseByKey(key);
		return ewh !=null ? JsonUtils.toJsonString(ewh) : "{}";
	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/getLaveAmount")
	public String getLaveAmount(@RequestBody PartsExtends partsAccount) {
		logger.info("===查询剩余库存===");
		try {
			List<PartsAccount> partsAccounts = (List<PartsAccount>) baseCommonService.findAll(PartsAccount.class);
			List<PartsAccount> accounts =partsAccounts.stream().filter(s -> StringUtils.equals(s.getDeviceCode(), partsAccount.getDeviceCode())).collect(Collectors.toList());
			System.out.println(JsonUtils.toJsonString(accounts));
			if (accounts != null && accounts.size() > 0) {
				for (PartsAccount account : accounts) {
					if(account.getPrice().compareTo(partsAccount.getPrice()) == 0) {
						return JsonUtils.toJsonString(account);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
}
