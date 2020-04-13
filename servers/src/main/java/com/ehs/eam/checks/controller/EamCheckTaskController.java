package com.ehs.eam.checks.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.checks.bean.CheckTaskAnalysisBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.entity.EamCheckTask;
import com.ehs.eam.checks.service.EamCheckTaskService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckTaskController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午11:08:49 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@RestController
public class EamCheckTaskController {

	private static final Logger logger=LoggerFactory.getLogger(EamCheckTaskController.class);
	
	@Resource
	private EamCheckTaskService eamCheckTaskService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function: EamCheckTaskController.java
	* @Description: 查询所有任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月17日 上午11:17:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月17日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/task/getAllTasks")
	public String getAllPlans(@RequestBody(required = false) CheckTaskQueryBean queryBean, HttpServletRequest request) {
		try {
			PageInfoBean pb = eamCheckTaskService.findAll(queryBean);
			return (pb==null?"[]":JsonUtils.toJsonString(pb));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "[]";
	}
	
	/**
	 * 
	* @Function: EamCheckTaskController.java
	* @Description: 任务统计分析
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午11:00:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/task/taskAnalysisForIndexPage")
	public String taskAnalysisForIndexPage() {
		List<CheckTaskAnalysisBean> analysisBeans=eamCheckTaskService.analysisTaskForOrg();
		if(analysisBeans!=null&&!analysisBeans.isEmpty()) {
			analysisBeans.forEach(s->{
				System.out.println(JsonUtils.toJsonString(s));
				s.setValue(Long.valueOf(""+((int)(Math.random()*100))));
			});
			//System.out.println(JsonUtils.toJsonString(analysisBeans));
			return JsonUtils.toJsonString(analysisBeans);
			
		}
		return "[]";
	}
	
	/**
	 * 
	* @Function: EamCheckTaskController.java
	* @Description: 查询所有任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午11:09:30 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/eam/checks/plan/getTask")
	public String getTask(HttpServletRequest request) {
		try {
			String key=request.getParameter("key");
			if(StringUtils.isBlank(key)) {
				return "{}";
			}
			EamCheckTask task=baseCommonService.findByKey(EamCheckTask.class, key);
			return (task==null?"{}":JsonUtils.toJsonString(task));
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return "{}";
	}
	
	/**
	 * 
	* @Function: EamCheckTaskController.java
	* @Description: 保存所有任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年3月24日 上午9:06:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月24日     chentm           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={"eamCheckTaskEdit"})
	@RequestMapping(value = "/eam/checks/task/saveTask")
	public String saveTask(@RequestBody EamCheckTask task, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			eamCheckTaskService.saveTask(task);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));

	}
}
