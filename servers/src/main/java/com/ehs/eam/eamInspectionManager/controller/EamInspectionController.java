package com.ehs.eam.eamInspectionManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:24:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/eamInspection")
public class EamInspectionController {
	private static final Logger logger = LoggerFactory.getLogger(EamInspectionController.class);
	
	@Resource
	private EamInspectionTaskService inspectionTaskService;
	
	/**
	 * 
	* @Function: EamInspectionController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:25:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"eamInspectionTask"})
	@RequestMapping(value = "/getAllTask")
	public String getAll(@RequestBody(required = false) QueryBean queryBean, HttpServletRequest request) {
		PageInfoBean pb = inspectionTaskService.findAllTask(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: EamInspectionController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月19日 上午10:25:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"eamInspectionEdit"})
	@RequestMapping(value = "/saveTask")
	public String saveTask(@RequestBody InspectionTaskBean taskBean) {
		logger.info("=====进入巡检任务保存=======");
		ResultBean resultBean = new ResultBean();
		try {
			inspectionTaskService.saveTask(taskBean);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}

}
