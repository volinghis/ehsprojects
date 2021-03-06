package com.ehs.common.organization.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.OrgTreeNodeLazy;
import com.ehs.common.organization.bean.OrgUserBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.bean.UserRolesBean;
import com.ehs.common.organization.bean.UserTreeDataBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrgUserService;
import com.ehs.common.organization.service.OrganizationService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月19日 下午7:10:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月19日     zhaol           v1.0.0               修改原因
*/
@RestController
public class OrgUserController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private OrganizationService organizationService;
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月5日 下午11:11:50 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月5日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/auth/orgUser/getAllForTree")
	@ResponseBody
	public String findAllOrg(HttpServletRequest request, HttpServletResponse response) {
		List<OrganizationInfo> orgList =(List<OrganizationInfo>)baseCommonService.findAll(OrganizationInfo.class);
		if (orgList == null || orgList.isEmpty()) {
			return "[]";
		}
		String parentKey=request.getParameter("parentKey");
		List<OrganizationInfo> ois=orgList.stream().filter(s->
		(StringUtils.isBlank(parentKey)?StringUtils.isBlank(s.getParentKey()):StringUtils.equals(parentKey, s.getParentKey()))
		).collect(Collectors.toList());
		List<UserTreeDataBean> users = new LinkedList<UserTreeDataBean>();

		if (ois != null && !ois.isEmpty()) {
			ois.sort((a, b) -> a.getSort() - b.getSort());
			ois.forEach(s->{
				UserTreeDataBean utb=new UserTreeDataBean();
				utb.setDisabled(true);
				utb.setOrg(true);
				utb.setLeaf(false);
				utb.setValue(s.getKey());
				utb.setLabel(s.getName());
				users.add(utb);
			});
			
		}
		List<OrgUser> orgUsers=orgUserService.findUserByOrgKey(parentKey);
		if(orgUsers!=null&&!orgUsers.isEmpty()) {
			orgUsers.forEach(s->{
				UserTreeDataBean utb=new UserTreeDataBean();
				utb.setValue(s.getKey());
				utb.setLabel(s.getName());
				users.add(utb);
			});
		}
		return JsonUtils.toJsonString(users);
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月5日 下午11:11:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月5日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/getTreeLazyNode")
	@ResponseBody
	public String getTreeNode(@RequestParam(required = false) String id) {
		List<OrgTreeNodeLazy> trees=new ArrayList<OrgTreeNodeLazy>();
		if(StringUtils.isBlank(id)) {
			OrganizationInfo organizationInfo = organizationService.getFirstNode();
			OrgTreeNodeLazy tn=new OrgTreeNodeLazy();
			tn.setId(organizationInfo.getKey());
			tn.setPid(organizationInfo.getParentKey());
			tn.setName(organizationInfo.getName());
			tn.setSort(String.valueOf(organizationInfo.getSort()));
			tn.setLeaf(false);
			trees.add(tn);
		}else {
			List<OrganizationInfo> orgs = (List<OrganizationInfo>) baseCommonService.findAll(OrganizationInfo.class);
			if (orgs != null && orgs.size() > 0) {
				for (OrganizationInfo organizationInfo : orgs) {
					if(StringUtils.equals(organizationInfo.getParentKey(), id)) {
						OrgTreeNodeLazy tn=new OrgTreeNodeLazy();
						tn.setId(organizationInfo.getKey());
						tn.setPid(organizationInfo.getParentKey());
						tn.setName(organizationInfo.getName());
						tn.setSort(String.valueOf(organizationInfo.getSort()));
		    			List list=orgs.stream().filter(d->StringUtils.equals(d.getParentKey(),organizationInfo.getKey())).collect(Collectors.toList());
		    			tn.setLeaf(list==null||list.size()<1);
		    			trees.add(tn);
					}
				}
			}
			trees.sort((a, b) -> {
		    	int c = Integer.parseInt(StringUtils.defaultIfBlank(a.getSort(), "0")) - Integer.parseInt(StringUtils.defaultIfBlank(b.getSort(), "0"));
		    	if(c==0) {
		    		return (Integer.parseInt(a.getSort()) - Integer.parseInt(b.getSort()));
		    	}
		    	return c;
		    });
		}
		return (trees==null?"[]":JsonUtils.toJsonString(trees));
	}
	
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据部门查询用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:10:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findUserByOrgKey")
	@ResponseBody
	public String findUserByOrgKey(HttpServletRequest request,UserQueryBean queryBean) {
		String orgKey=request.getParameter("orgKey");
		String searchData=request.getParameter("searchData");
		if (StringUtils.isNotBlank(searchData)) {
			queryBean = (UserQueryBean) JsonUtils.parseObject(searchData, UserQueryBean.class);
		}
		PageInfoBean users=orgUserService.findUserByOrgKey(orgKey,queryBean);
		return (users == null ? "[]" : JsonUtils.toJsonString(users));
	}
	
//	@RequestMapping(value="/action/userBaseInfoManager/userBaseInfoManagerList")
//	@ResponseBody
//	public String userBaseInfoManagerList(HttpServletRequest request) {
//		Pagenate pageNate=new Pagenate();
//		String searchParam=request.getParameter("searchParam");
//        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
//        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
//		String orgCode=request.getParameter("orgCode");
//		List<OrganizationInfo> orgList = baseCommonService.findAll(OrganizationInfo.class, null);
//        List<String> treelist=new ArrayList<String>();
//		createMenuNode(treelist, orgList, orgCode);
//		Specification<UserBaseInfo> sf=null;
//		List<Predicate> ps=new ArrayList<Predicate>();
//		//多条件查询
//		sf=(Root<UserBaseInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
//			if(StringUtils.isNotBlank(orgCode)) {
//				OrganizationInfo orgInfos= (OrganizationInfo) baseCommonService.findByCode(OrganizationInfo.class, orgCode);
//				CriteriaBuilder.In<String> in=null;
//				if(orgInfos != null ) {
//					in= criteriaBuilder.in(root.get(UserBaseInfo.ORG_CODE));
//					if (treelist.size() > 0) {
//						for (int j = 0; j < treelist.size(); j++) {
//							in.value(treelist.get(j));
//							System.out.println("递归查询到的第"+(j+1)+"个Code："+treelist.get(j));
//						}
//					}
//				}
//				in.value(orgCode);
//				ps.add(criteriaBuilder.and(in));//存入条件集合里
//			}
//        	if(StringUtils.isNotBlank(searchParam)) {
//        		ps.add(criteriaBuilder.or(criteriaBuilder.like(root.get(UserBaseInfo.DATA_CODE),"%"+searchParam+"%" ), criteriaBuilder.like(root.get(UserBaseInfo.NAME),"%"+searchParam+"%" )));
//        	}
//        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
//        };
//        return JSON.toJSONString(baseCommonService.findPagenate(UserBaseInfo.class, sf, pageNate));
//	} 
//	

	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 保存用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/saveOrgUser")
	public String saveOrgUser(@RequestBody OrgUser orgUser,HttpServletRequest request, HttpServletResponse response) {
		ResultBean resultBean=new ResultBean();
		try {
			List<OrgUser> users= (List<OrgUser>)baseCommonService.findAll(OrgUser.class);
			if (users!=null&&users.size()>0) {
				long c=users.stream().filter(s->StringUtils.equals(s.getDataCode(),orgUser.getDataCode())&&!StringUtils.equals(s.getKey(), orgUser.getKey())).count();
				if(c>0) {
					return JsonUtils.toJsonString(resultBean.error("此用户编号已经被使用，请重新输入"));
				}
			}
			orgUserService.saveUser(orgUser);
			return JsonUtils.toJsonString(resultBean.ok("用户信息保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("保存用户失败"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 改变状态：停用或者启用
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/changeState")
	@ResponseBody
	public String changeState(@RequestBody OrgUser orgUser,HttpServletRequest request) {
		OrgUser user =orgUserService.changeState(orgUser);
		return JsonUtils.toJsonString(user);
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 删除用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/deleteOrgUser")
	public String deleteOrgUser(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		OrgUser user = orgUserService.deleteOrgUser(key);
		if (user != null) {
			return JsonUtils.toJsonString(resultBean.ok("用户删除成功"));
		}else {
			return JsonUtils.toJsonString(resultBean.error("该用户有提交的任务尚未审核或有待办任务"));
		}
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据用户获取角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午10:10:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findUserRoles")
	@ResponseBody
	public String findUserRoles(HttpServletRequest request, HttpServletResponse response) {
		String userKey=request.getParameter("userKey");
		return JsonUtils.toJsonString(orgUserService.findRolesByUserKey(userKey));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据用户查询角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:24:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findAllRolesByUserKey")
	public String findAllRolesByUserKey(HttpServletRequest request) {
		String userKey=request.getParameter("userKey");
		String query=request.getParameter("query");
		RoleQueryBean queryBean =(RoleQueryBean) JsonUtils.parseObject(query, RoleQueryBean.class);
		PageInfoBean pb = orgUserService.findAllRolesByUserKey(userKey,queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 添加用户角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:13:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/saveUserRole")
	@ResponseBody
	public String saveUserRoles(@RequestBody UserRolesBean userRolesBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			orgUserService.saveUserRole(userRolesBean);
			return JsonUtils.toJsonString(resultBean.ok("授权成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("授权失败！"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 删除用户角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:13:13 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/deleteUserRole")
	@ResponseBody
	public String deleteUserRole(@RequestBody UserRolesBean userRolesBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			orgUserService.deleteUserRole(userRolesBean);
			return JsonUtils.toJsonString(resultBean.ok("删除成功！",""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("删除失败！"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 验证账号是否唯一
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:12:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
//	@RequestAuth(menuKeys = {"userManager"})
//	@RequestMapping(value = "/auth/orgUser/userValidation")
//	@ResponseBody
//	public String userValidation(HttpServletRequest request) {
//		ResultBean resultBean=new ResultBean();
//		String dataCode = request.getParameter("dataCode");
//		String key = request.getParameter("key");
//		List<OrgUser> users = (List<OrgUser>) baseCommonService.findAll(OrgUser.class);
//		long count=users.stream().filter((e)->e.getDataCode().equals(dataCode)).count();
//		if (!StringUtils.isBlank(key)) {
//			OrgUser user=(OrgUser)baseCommonService.findByKey(OrgUser.class, key);
//			if(user != null) {
//				System.out.println("user===dataCode==="+user.getDataCode());
//				if(StringUtils.equals(dataCode, user.getDataCode())) {
//					return JsonUtils.toJsonString(resultBean.ok("该工号尚未修改"));
//				}else {
//					if(!dataCode.equals(user.getDataCode()) && count > 0l) {
//						return JsonUtils.toJsonString(resultBean.error("该员工工号已经存在，请重新确认"));
//					}
//				}
//			}
//		}
//		if (count >= 1l) {
//			return JsonUtils.toJsonString(resultBean.error("该员工工号已经存在，请重新确认"));
//		}
//		if(StringUtils.isNotBlank(dataCode)) {
//			return JsonUtils.toJsonString(resultBean.ok("该工号可以使用"));
//		}
//		return JsonUtils.toJsonString("");
//	}
	
	/**
	 * 
	* @Function:findOrgUserByAccount 
	* @Description: 根据账号或dataCode 获取当前用户
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月27日 下午4:01:50 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月27日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/orgUser/findOrgUserByAccount")
	public String findOrgUserByAccount(HttpServletRequest request) {
		String account=request.getParameter("account");
		OrgUser orgUser=orgUserService.findOrgUserBySysUserKey(account);
		return orgUser==null?"{}":JsonUtils.toJsonString(orgUser);
	}
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据部门key查找所有用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月24日 下午10:46:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/orgUser/findUserByKey")
	public String findUserByKey(HttpServletRequest request) {
		String key=request.getParameter("key");
		OrgUser ou=baseCommonService.findByKey(OrgUser.class, key);
		return ou==null?"{}":JsonUtils.toJsonString(ou);
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 人员调岗
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月24日 下午10:47:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/transferUser")
	@ResponseBody
	public String transferUser(@RequestBody OrgUserBean userBean,HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			orgUserService.transferUser(userBean);
			return JsonUtils.toJsonString(resultBean.ok("人员调岗成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("人员调岗失败"));
	}
}
