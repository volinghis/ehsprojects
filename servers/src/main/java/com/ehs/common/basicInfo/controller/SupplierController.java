package com.ehs.common.basicInfo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.basicInfo.bean.SupplierBean;
import com.ehs.common.basicInfo.bean.SupplierQueryBean;
import com.ehs.common.basicInfo.entity.SupplierInfo;
import com.ehs.common.basicInfo.service.SupplierService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.controller.EnterWareHouseController;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:32:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/basicInfo/supplierManager")
public class SupplierController {

private static final Logger logger = LoggerFactory.getLogger(EnterWareHouseController.class);
	
	@Resource
	private SupplierService supplierService;
	
	@RequestAuth(menuKeys = {"supplier"})
	@RequestMapping(value = "/saveSupplier")
	public String saveSupplier(@RequestBody SupplierBean supplierBean){
		logger.info("开始保存客户信息");
		ResultBean resultBean=new ResultBean();
		try {
			supplierService.saveSupplier(supplierBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，供应商信息创建成功 ！"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，供应商信息创建失败！"));
	}
	
	@RequestAuth(menuKeys = {"supplier"})
	@RequestMapping(value = "/getSuppliers")
	public String getSuppliers(@RequestBody(required = false) SupplierQueryBean supplierQueryBean){
		 PageInfoBean pb = supplierService.findSuppliers(supplierQueryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"supplier"})
	@RequestMapping(value = "/deleteSupplier")
	public String deleteSupplier(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		supplierService.deleteSupplierByKey(key);
		return JsonUtils.toJsonString(resultBean.ok("客户删除成功"));
	}
	
	@RequestAuth(menuKeys = {"supplier"})
	@RequestMapping(value = "/changeState")
	@ResponseBody
	public String changeState(@RequestBody SupplierInfo supplierInfo,HttpServletRequest request) {
		SupplierInfo c =supplierService.changeState(supplierInfo);
		return JsonUtils.toJsonString(c);
	}
}
