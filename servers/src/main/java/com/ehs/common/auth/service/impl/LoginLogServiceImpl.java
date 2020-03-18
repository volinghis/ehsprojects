/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.service.impl 
 * @author: qjj   
 * @date: 2020年3月6日 下午1:44:13 
 */
package com.ehs.common.auth.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.LoginLogBean;
import com.ehs.common.auth.dao.LoginLogDao;
import com.ehs.common.auth.entity.SysLoginLog;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.service.LoginLogService;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.dao.OrgUserDao;
import com.ehs.common.organization.entity.entitysuper.OrgUser;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月6日 下午1:44:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     qjj           v1.0.0               修改原因
*/
@Service
public class LoginLogServiceImpl implements LoginLogService {

	@Resource
	private LoginLogDao loginLogDao;
	
	@Resource
	private OrgUserDao orgUserDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	/** 
	* @see com.ehs.common.auth.service.LoginLogService#findDataFilesByFileIds(com.ehs.common.auth.bean.LoginLogBean)  
	*/
	@Override
	public PageInfoBean findLoginLogList(LoginLogBean loginLogBean) {
		PageRequest pageRequest = PageRequest.of(loginLogBean.getPage() - 1, loginLogBean.getSize());
		Page<SysLoginLog> loginLog =loginLogDao.findAlLoginLogs(loginLogBean.getQuery(), pageRequest);
		if (loginLog != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(loginLog.getContent());
			pb.setTotalCount(loginLog.getTotalElements());
			return pb;
		}
		return null;
	}

	/** 
	* @see com.ehs.common.auth.service.LoginLogService#addLoginLog(java.lang.String, java.lang.String)  
	*/
	@Transactional
	@Override
	public void addLoginLog(String userKey, String ip) {
		SysLoginLog logEntity=new SysLoginLog();
		if (StringUtils.equals("adminKey", userKey)) {
			logEntity.setAccount("admin");
			logEntity.setIp(ip);
			logEntity.setTime(BaseUtils.getNow());
			logEntity.setName("系统管理员");
		}else {
			OrgUser curUser=orgUserDao.findOrgUserBySysUserKey(userKey);
			logEntity.setAccount(curUser.getDataCode());
			logEntity.setIp(ip);
			logEntity.setTime(BaseUtils.getNow());
			logEntity.setName(curUser.getName());
		}
		baseCommonService.saveOrUpdate(logEntity);
	}

	/** 
	* @see com.ehs.common.auth.service.LoginLogService#deleteLoginLogs(java.lang.String)  
	*/
	@Override
	@Transactional
	public void deleteLoginLogs(String keys) {
		String [] keyArr=keys.split(",");
		for (String key : keyArr) {
			baseCommonService.deleteByKey(SysLoginLog.class, key);
		}
	}
	

}
