/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.controller 
 * @author: qjj   
 * @date: 2020年3月6日 上午11:48:08 
 */
package com.ehs.common.auth.controller;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.bean.LoginLogBean;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.service.LoginLogService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: LoginLogController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年3月6日 上午11:48:08
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年3月6日
 *        qjj v1.0.0 修改原因
 */
@RestController
public class LoginLogController {

	@Resource
	private LoginLogService loginLogService;

	/**
	 * 
	* @Function:getLoginLogList 
	* @Description: 获取登录日志列表
	* @param loginLogBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月6日 下午2:41:48 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月6日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "loginLog" })
	@RequestMapping("/auth/loginLog/getLoginLogList")
	public String getLoginLogList(@RequestBody LoginLogBean loginLogBean) {
		PageInfoBean pb = loginLogService.findLoginLogList(loginLogBean);
		return (pb == null ? "[]" : JsonUtils.toJsonString(pb));
	}

	@RequestAuth(menuKeys = { "loginLog" })
	@RequestMapping("/auth/loginLog/deleteLoginLog")
	public String deleteLoginLog(@RequestParam String keys) {
		ResultBean resultBean=new ResultBean();
		try {
			loginLogService.deleteLoginLogs(keys);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}
}
