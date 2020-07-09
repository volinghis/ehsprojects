package com.ehs.common.basicInfo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.basicInfo.dao.ContactInfoDao;
import com.ehs.common.basicInfo.entity.ContactInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ContactController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 下午4:55:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/basicInfo/contactManager")
public class ContactController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private ContactInfoDao contactInfoDao;
	
	/**
	 * 
	* @Function: ContactController.java
	* @Description: 查找
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:04:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"clientManager"})
	@RequestMapping(value = "/getContacts")
	public String getContacts(HttpServletRequest request){
		String key = request.getParameter("key");
		List<ContactInfo> contactInfos = contactInfoDao.findByCode(key);
		return (contactInfos==null?"[]":JsonUtils.toJsonString(contactInfos));
	}

}
