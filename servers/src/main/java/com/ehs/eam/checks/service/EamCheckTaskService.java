package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckTaskAnalysisBean;
import com.ehs.eam.checks.bean.CheckTaskQueryBean;
import com.ehs.eam.checks.entity.EamCheckTask;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckTaskService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:40:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
public interface EamCheckTaskService {

	/**
	 * 
	* @Function: EamCheckTaskService.java
	* @Description: 查询所有检修任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:40:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findAll(CheckTaskQueryBean query);
	
	/**
	 * 
	* @Function: EamCheckTaskService.java
	* @Description:保存检修任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:41:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public void saveTask(EamCheckTask t);
	
	/**
	 * 
	* @Function: EamCheckTaskService.java
	* @Description: 通过部门查看所有检修任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:41:15 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public List<CheckTaskAnalysisBean> analysisTaskForOrg();

}
