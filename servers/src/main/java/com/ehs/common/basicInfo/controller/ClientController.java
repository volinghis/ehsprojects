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

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月9日 上午11:03:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月9日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/basicInfo/clientManager")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(EnterWareHouseController.class);
	
	@Resource
	private ClientService clientService;
	
	/**
	 * 
	* @Function: ClientController.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:03:15 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
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
	
	/**
	 * 
	* @Function: ClientController.java
	* @Description: 查找
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:03:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/getClients")
	public String getClients(@RequestBody(required = false) ClientQueryBean clientQueryBean){
		 PageInfoBean pb = clientService.findClients(clientQueryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: ClientController.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:03:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/deleteClient")
	public String deleteClient(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		clientService.deleteClientByKey(key);
		return JsonUtils.toJsonString(resultBean.ok("客户删除成功"));
	}
	
	/**
	 * 
	* @Function: ClientController.java
	* @Description: 修改状态
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:03:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/changeState")
	@ResponseBody
	public String changeState(@RequestBody ClientInfo clientInfo,HttpServletRequest request) {
		ClientInfo c =clientService.changeState(clientInfo);
		return JsonUtils.toJsonString(c);
	}
}
