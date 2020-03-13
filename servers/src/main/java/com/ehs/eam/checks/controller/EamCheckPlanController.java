package com.ehs.eam.checks.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.checks.bean.CheckPlanQueryBean;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.service.EamCheckPlanService;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

/**
 * 
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckPlanController.java
* @Description: 巡检计划
*
* @version: v1.0.0
* @author: chentm
* @date: 2020年3月1日 下午4:21:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月1日     chentm           v1.0.0               修改原因
 */
@RestController
public class EamCheckPlanController {
	
	@Resource
	private EamCheckPlanService eamCheckPlanService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	private static final Logger logger=LoggerFactory.getLogger(EamCheckPlanController.class);
	
	/**
	 * 
	* @Function: EamCheckPlanController.java
	* @Description: 查询所有计划
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月1日 下午4:24:13 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月1日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/plan/getAllPlans")
	public String getAllPlans(@RequestBody(required = false) CheckPlanQueryBean queryBean, HttpServletRequest request) {
		try {
			PageInfoBean pb = eamCheckPlanService.findPlans(queryBean);
			return (pb==null?"[]":JsonUtils.toJsonString(pb));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "[]";
	}
	/**
	 * 
	* @Function: EamCheckPlanController.java
	* @Description: 获取计划
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月10日 下午4:41:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月10日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/plan/getPlan")
	public String getPlan(HttpServletRequest request) {
		try {
			String key=request.getParameter("key");
			if(StringUtils.isBlank(key)) {
				return "{}";
			}
			EamCheckPlan plan=baseCommonService.findByKey(EamCheckPlan.class, key);
			return (plan==null?"{}":JsonUtils.toJsonString(plan));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "{}";
	}
	/**
	 * 
	* @Function: EamCheckPlanController.java
	* @Description: 保存计划
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月10日 下午4:40:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月10日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={"eamCheckPlan"})
	@RequestMapping(value = "/eam/checks/plan/savePlan")
	public String savePlan(@RequestBody EamCheckPlan plan, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			baseCommonService.saveOrUpdate(plan);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}
	
	
}
