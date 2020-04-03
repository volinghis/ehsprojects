/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.controller 
 * @author: qjj   
 * @date: 2019年12月24日 上午11:25:13 
 */
package com.ehs.common.auth.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.bean.PassWordBean;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.service.UserService;
import com.ehs.common.auth.utils.SessionBean;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.service.OrgUserService;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: UserController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月24日 上午11:25:13
 *
 *  Modification History: Date Author Version Description
 *  ---------------------------------------------------------* 2019年12月24日
 *  qjj v1.0.0 修改原因
 */
@RestController
public class UserController {

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private SessionBean sessionBean;

	@Resource
	private UserService userService;

	@Resource
	private OrgUserService orgUserService;

	/**
	 * 
	 * @Function:changPassword
	 * @Description: 修改密码
	 * @param request
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年12月24日 上午9:35:20
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2019年12月24日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/auth/userManager/changPassword")
	public String changPassword(HttpServletRequest request, @RequestBody PassWordBean passWordBean) {
		ResultBean resultBean = new ResultBean();
		String oldPwd = passWordBean.getPass();
		// 当前用户
		SysUser sysUser = baseCommonService.findByKey(SysUser.class, sessionBean.getSession(request));
		oldPwd = BaseUtils.string2MD5(sysUser.getAccount() + oldPwd + sysUser.getSalt());
		if (StringUtils.equals(sysUser.getPassword(), oldPwd)) {
			if (userService.changePassWord(sysUser, passWordBean.getNewPass())) {
				return JsonUtils.toJsonString(resultBean.ok("密码修改成功"));
			}
		}
		return JsonUtils.toJsonString(resultBean.error("用户密码输入错误"));
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/auth/userManager/saveCurrentUser")
	public String saveCurrentUser(@RequestBody OrgUser orgUser, HttpServletRequest request,
			HttpServletResponse response) {
		ResultBean resultBean = new ResultBean();
		try {
			orgUserService.saveUser(orgUser);
			return JsonUtils.toJsonString(resultBean.ok("用户信息保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("保存用户失败"));
	}
}
