package com.ehs.common.organization.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.dao.LoginDao;
import com.ehs.common.auth.dao.RoleDao;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.service.RoleService;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.OrgUserBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.bean.UserRolesBean;
import com.ehs.common.organization.dao.OrgUserDao;
import com.ehs.common.organization.dao.OrganizationDao;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrgUserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月20日 下午6:08:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月20日     zhaol           v1.0.0               修改原因
*/
@Service
public class OrgUserServiceImpl implements OrgUserService{

	@Resource
	private OrgUserDao orgUserDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private LoginDao loginDao;
	
	@Resource
	private OrganizationDao orgDao;
	
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#getAllUser(com.ehs.common.organization.bean.UserQueryBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:29 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllUser(UserQueryBean userQueryBean) {
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		if (StringUtils.isNotBlank(userQueryBean.getQuery())) {
			Page<OrgUser> users=orgUserDao.findUsers(userQueryBean.getQuery(), pageRequest);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements());
				return pb;
			}
			return null;
		}
		else {
			Page<OrgUser> users=orgUserDao.findUsers(pageRequest);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements());
				return pb;
			}
			return null;
		}
	}
	
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findUserByOrgKey(java.lang.String, com.ehs.common.organization.bean.UserQueryBean, com.ehs.common.organization.bean.UserQueryBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	
	@Override
	public PageInfoBean findUserByOrgKey(String orgKey,UserQueryBean userQueryBean) {
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		if (StringUtils.isNotBlank(orgKey) && StringUtils.isNotBlank(userQueryBean.getQuery())) {
			System.out.println("=============两个查询条件=============");
			Page<OrgUser> users=orgUserDao.findUserByOrgKey(orgKey,userQueryBean.getQuery(), pageRequest);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements()); 
				return pb;
			}
			return null;
		}else if (StringUtils.isNotBlank(orgKey)) {
			List list =getOrgUsers(orgKey);
//			List list = new ArrayList();
//			List<OrganizationInfo> orgList = orgDao.getFirstNode(orgKey);
//			if (orgList.size() > 0) {
//				List<OrganizationInfo> templist=new ArrayList<OrganizationInfo>();
//				filterItems(templist, orgList,orgKey);
//				for (OrganizationInfo organizationInfo : templist) {
//					List<OrgUser> users = orgUserDao.findUserByOrgKey(organizationInfo.getKey());
//					for (OrgUser user : users) {
//						list.add(user);
//					}
//				}
//			}else {
//				List<OrgUser> users = orgUserDao.findUserByOrgKey(orgKey);
//				for (OrgUser orgUser : users) {
//					list.add(orgUser);
//				}
//			}
			if (list!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(list);
				pb.setTotalCount(list.size()); 
				return pb;
			}
			return null;
		}
		else {
			return this.getAllUser(userQueryBean);
		}
	}
	
	private void filterItems(List<OrganizationInfo> list,List<OrganizationInfo> dataList,String parentKey) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentKey(),parentKey)).forEach(c->{
			list.add(c);
			filterItems(list,dataList,c.getKey());
		});
	}
	
	public List<OrgUser> getOrgUsers(String orgKey){
		List list = new ArrayList();
		List<OrganizationInfo> orgList = orgDao.getFirstNode(orgKey);
		if (orgList.size() > 0) {
			List<OrganizationInfo> templist=new ArrayList<OrganizationInfo>();
			filterItems(templist, orgList,orgKey);
			for (OrganizationInfo organizationInfo : templist) {
				List<OrgUser> users = orgUserDao.findUserByOrgKey(organizationInfo.getKey());
				for (OrgUser user : users) {
					list.add(user);
				}
			}
		}else {
			List<OrgUser> users = orgUserDao.findUserByOrgKey(orgKey);
			for (OrgUser orgUser : users) {
				list.add(orgUser);
			}
		}
		return list;
	}
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#saveUser(com.ehs.common.organization.entity.OrgUser)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public OrgUser saveUser(OrgUser orgUser) {
		if (StringUtils.isBlank(orgUser.getSysUserKey())) {
			String dataCode = orgUser.getDataCode();
			String salt = BaseUtils.getSalt();
			SysUser sysUser= new SysUser();
			sysUser.setAccount(dataCode);
			sysUser.setState(0);
			sysUser.setVersionId(0l);
			sysUser.setSalt(salt);
			sysUser.setPassword(BaseUtils.string2MD5(sysUser.getAccount()+"123456"+salt));
			baseCommonService.saveOrUpdate(sysUser);
			//保存用户信息
			orgUser.setSysUserKey(sysUser.getKey());
			orgUser.setState(sysUser.getState());
			OrgUser u =baseCommonService.saveOrUpdate(orgUser);
			SysUser sysUser2 =baseCommonService.findByKey(SysUser.class, sysUser.getKey());
			sysUser2.setUserKey(u.getKey());
			baseCommonService.saveOrUpdate(sysUser2);
			return u;
		}
		SysUser user= baseCommonService.findByKey(SysUser.class, orgUser.getSysUserKey());
		user.setAccount(orgUser.getDataCode());
		baseCommonService.saveOrUpdate(user);
		//保存用户信息
		return baseCommonService.saveOrUpdate(orgUser);
		
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#changeState(com.ehs.common.organization.entity.OrgUser)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public OrgUser changeState(OrgUser orgUser) {
		SysUser sysUser = baseCommonService.findByKey(SysUser.class, orgUser.getSysUserKey());
		orgUser.setState(orgUser.getState()== 0 ? 0: 1);
		sysUser.setState(orgUser.getState()== 0 ? 0: 1);
		baseCommonService.saveOrUpdate(sysUser);
		return baseCommonService.saveOrUpdate(orgUser);
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#deleteOrgUser(java.lang.String)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public OrgUser deleteOrgUser(String key) {
		OrgUser user=null;
		List<FlowProcessInfo> flowProcessInfos = (List<FlowProcessInfo>) baseCommonService.findAll(FlowProcessInfo.class);
		if(flowProcessInfos != null && flowProcessInfos.size() >0) {
			long count = flowProcessInfos.stream().filter(s -> !StringUtils.equals(s.getFlowCurrentStep(), "END") 
					&&(StringUtils.equals(s.getOwner(), key) || StringUtils.equals(s.getFlowCurrentPerson(), key))).count();
			if (count > 0) {
				return null;
			}else {
				OrgUser orgUser = baseCommonService.findByKey(OrgUser.class, key);
				user =baseCommonService.deleteByKey(OrgUser.class, key);
				baseCommonService.deleteByKey(SysUser.class, orgUser.getSysUserKey());
			}
		}
		return user;
	}
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findRolesByUserKey(java.lang.String)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<SysRole> findRolesByUserKey(String userKey) {
		OrgUser orgUser = baseCommonService.findByKey(OrgUser.class, userKey);
		List<SysRole> roles = new ArrayList<SysRole>();
		if (orgUser != null && orgUser.getRoles() != null) {
			List<RoleBean> roleBeans =JsonUtils.parseList(orgUser.getRoles(),RoleBean.class);
			for (RoleBean roleBean : roleBeans) {
				roles.add(baseCommonService.findByKey(SysRole.class, roleBean.getRoleKey()));
			}
			return roles; 
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#saveMenuRole(com.ehs.common.organization.bean.UserRolesBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 下午4:26:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveUserRole(UserRolesBean userRolesBean) {
		OrgUser user = baseCommonService.findByKey(OrgUser.class, userRolesBean.getUserKey());
		SysUser sysUser = loginDao.findByAccount(user.getDataCode());
		List<RoleBean> sysRoles = userRolesBean.getRoleList();
		StringBuffer sb = new StringBuffer();
		if(user.getRoles() != null) {
			List<RoleBean> roleBeans = JsonUtils.parseList(user.getRoles(), RoleBean.class);
			roleBeans.addAll(sysRoles);
			user.setRoles(JsonUtils.toJsonString(roleBeans));
			for (RoleBean roleBean : roleBeans) {
				sb.append(roleBean.getRoleKey()+",");
			}
			sysUser.setRoleKeys(sb.toString().substring(0,sb.toString().length()-1));
		}else {
			user.setRoles(JsonUtils.toJsonString(sysRoles));
			for (RoleBean roleBean : sysRoles) {
				sb.append(roleBean.getRoleKey()+",");
			}
			sysUser.setRoleKeys(sb.toString().substring(0,sb.toString().length()-1));
		}
		baseCommonService.saveOrUpdate(user);
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#deleteUserRole(com.ehs.common.organization.bean.UserRolesBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午3:31:03 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteUserRole(UserRolesBean userRolesBean) {
		OrgUser user = baseCommonService.findByKey(OrgUser.class, userRolesBean.getUserKey());
		SysUser sysUser = loginDao.findByAccount(user.getDataCode());
		List<String> roleKeys = new ArrayList(Arrays.asList(sysUser.getRoleKeys().split(",")));
		List<RoleBean> roles = userRolesBean.getRoleList();
		List<RoleBean> roleBeans = new ArrayList<RoleBean>();
		if (user.getRoles() != null) {
			roleBeans = JsonUtils.parseList(user.getRoles(), RoleBean.class);
			roleBeans.removeAll(roles);
			for (RoleBean rBean : roles) {
				roleKeys.remove(rBean.getRoleKey());
			}
			sysUser.setRoleKeys(roleKeys.toString() == ""? "":roleKeys.toString().substring(1,roleKeys.toString().length()-1));
		}
		user.setRoles(JsonUtils.toJsonString(roleBeans == null ? null : roleBeans));
		baseCommonService.saveOrUpdate(user);
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findUserByOrgKey(java.lang.String)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午3:30:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<OrgUser> findUserByOrgKey(String key) {
		return orgUserDao.findOrgUserByOrgKeyAndDataModelIn( key,new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findAllRolesByUserKey(java.lang.String, com.ehs.common.auth.bean.RoleQueryBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午3:30:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findAllRolesByUserKey(String userKey, RoleQueryBean queryBean) {
		Assert.notNull(userKey, "用户key不能为空");
		List<SysRole> roles=findRolesByUserKey(userKey);
		if(roles==null||roles.isEmpty()) {
			return roleService.findRoles(queryBean);
		}
		PageRequest pageRequest =PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		List<SysRole> allRoles=(List<SysRole>)baseCommonService.findAll(SysRole.class);
		if(allRoles==null||allRoles.isEmpty()) {
			return null;
		}
		List<SysRole> sysRoles= allRoles.stream().filter(s->roles.stream().allMatch(ss->(!StringUtils.equals(s.getKey(), ss.getKey())))).collect(Collectors.toList());
		if(sysRoles != null) {
			if (StringUtils.isNotBlank(queryBean.getQuery())) {
				Page<SysRole> sys_roles = roleDao.findRoles(queryBean.getQuery(), pageRequest);
				if (sys_roles != null) {
					PageInfoBean pb = new PageInfoBean();
					pb.setDataList(sys_roles.getContent());
					pb.setTotalCount(sys_roles.getTotalElements());
					return pb;
				}
			}
			Page<SysRole> sys_roles = roleDao.findRoles(queryBean.getQuery(), pageRequest);
			if (sys_roles != null) {
				PageInfoBean pb = new PageInfoBean();
				pb.setDataList(sysRoles);
				pb.setTotalCount(sysRoles.size());
				return pb;
			}
		}
		return null;
	}

	/** 
	* @see com.ehs.common.organization.service.OrgUserService#findOrgUserByDataCode(java.lang.String)  
	*/
	@Override
	public OrgUser findOrgUserBySysUserKey(String code) {
		Assert.notNull(code, "params account is required");
		SysUser sysUser=loginDao.findByAccount(code);
		if (sysUser!=null) {
			return orgUserDao.findOrgUserBySysUserKey(sysUser.getKey());
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#transferUser(com.ehs.common.organization.bean.OrgUserBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 人员调岗
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月24日 下午10:47:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void transferUser(OrgUserBean userBean) {
		if (userBean.getUsers() != null) {
			for (OrgUser orgUser : userBean.getUsers()) {
				OrgUser user = baseCommonService.findByKey(OrgUser.class, orgUser.getKey());
				OrganizationInfo orgInfo = baseCommonService.findByKey(OrganizationInfo.class, userBean.getOrgKey());
				user.setOrgKey(userBean.getOrgKey());
				user.setOrgName(orgInfo.getName());
				baseCommonService.saveOrUpdate(user);
			}
		}
	}

}
