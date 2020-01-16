package com.ehs.eam.eamPartLibraryManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
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
	
	@Resource
	private EnterWareHouseService ewhService;

	@RequestAuth(menuKeys = {"enterWarehouse"})
	@RequestMapping(value = "/getAll")
	public String getAll(@RequestBody(required = false) QueryBean queryBean, HttpServletRequest request) {
		PageInfoBean pb = ewhService.findAll(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"enterWarehouseEdit"})
	@RequestMapping(value = "/saveEnterWareHouse")
	public String saveWareEnterHouse(@RequestBody EnterWareHouserBean wareHouserBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			ewhService.saveEnterWareHouse(wareHouserBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，备件入库成功 ！"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，备件入库失败！"));
	}
	
}
