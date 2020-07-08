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
import com.ehs.common.basicInfo.bean.ClientBean;
import com.ehs.common.basicInfo.bean.ClientQueryBean;
import com.ehs.common.basicInfo.entity.ClientInfo;
import com.ehs.common.basicInfo.service.ClientService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.controller.EnterWareHouseController;

@RestController
@RequestMapping(value = "/basicInfo/clientManager")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(EnterWareHouseController.class);
	
	@Resource
	private ClientService clientService;
	
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/saveClient")
	public String saveClient(@RequestBody ClientBean clientBean){
		logger.info("开始保存客户信息");
		ResultBean resultBean=new ResultBean();
		try {
			clientService.saveClient(clientBean);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，客户信息创建成功 ！"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，客户信息创建失败！"));
	}
	
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/getClients")
	public String getClients(@RequestBody(required = false) ClientQueryBean clientQueryBean){
		 PageInfoBean pb = clientService.findClients(clientQueryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/deleteClient")
	public String deleteClient(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		clientService.deleteClientByKey(key);
		return JsonUtils.toJsonString(resultBean.ok("客户删除成功"));
	}
	
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/changeState")
	@ResponseBody
	public String changeState(@RequestBody ClientInfo clientInfo,HttpServletRequest request) {
		ClientInfo c =clientService.changeState(clientInfo);
		return JsonUtils.toJsonString(c);
	}
}
