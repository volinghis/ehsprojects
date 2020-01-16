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
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.service.OutWareHouseService;

@RestController
@RequestMapping(value = "/eam/eamOutWarehouse")
public class OutWareHouseController {
	
	@Resource
	private OutWareHouseService owsService;
	
	@RequestAuth(menuKeys = {"outWarehouse"})
	@RequestMapping(value = "/getList")
	public String getList(@RequestBody QueryBean queryBean ,HttpServletRequest request) {
		PageInfoBean pb = owsService.findAll(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/saveOutWareHouse")
	public String saveWareEnterHouse(@RequestBody OutWareHouserBean wareHouserBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
//		System.out.println(JsonUtils.toJsonString(wareHouserBean));
		try {
			owsService.saveOutWareHouse(wareHouserBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，备件出库成功 ！"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，备件出库失败！"));
	}

	@RequestAuth(menuKeys = {"outWarehouseEdit"})
	@RequestMapping(value = "/validAmount")
	public String validAmount(HttpServletRequest request) {
		String amount = request.getParameter("amount");
		String deviceCode = request.getParameter("deviceCode");
		String price = request.getParameter("price");
		System.out.println("amount========="+amount);
		System.out.println("deviceCode========="+deviceCode);
		System.out.println("price========="+price);
		int amountNew= owsService.validAmount(amount,deviceCode,price);
		return JsonUtils.toJsonString(String.valueOf(amountNew));
	}
	
}
