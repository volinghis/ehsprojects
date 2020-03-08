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

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.OrgQueryBean;
import com.ehs.common.organization.bean.OrgTreeNodeLazy;
import com.ehs.common.organization.bean.OrganizationBean;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrgUserService;
import com.ehs.common.organization.service.OrganizationService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationController.java
* @Description: 部门管理
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月13日 上午9:06:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月13日     zhaol           v1.0.0               修改原因
*/
@RestController
public class OrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrgUserService orgUserService;
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 查询所有部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 上午9:06:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/auth/orgManager/getAllForTree")
	@ResponseBody
	public String findAllOrg(HttpServletRequest request, HttpServletResponse response) {
		List<OrganizationInfo> orgList =(List<OrganizationInfo>)baseCommonService.findAll(OrganizationInfo.class);
		if (orgList == null || orgList.isEmpty()) {
			return "[]";
		}
		if (orgList.size()> 1) {
			orgList.sort((a, b) -> a.getSort() - b.getSort());
		}
		List<OrganizationBean> orgs = new LinkedList<OrganizationBean>();
		createOrg(orgs, orgList, null);
		return JsonUtils.toJsonString(orgs);
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 部门递归
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 上午9:07:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	private void createOrg(List<OrganizationBean> orgBeans,List<OrganizationInfo> orgs,String parentkey) {
		orgs.stream().filter(s->StringUtils.equals(s.getParentKey(),parentkey)).forEach(c->{
			OrganizationBean orgBean=new OrganizationBean();
			orgBean.setId(c.getKey());
			orgBean.setLabel(c.getName());
			orgBean.setParentId(c.getParentKey()); 
			orgBean.setValue(c.getKey());
			List ll=new ArrayList();
			createOrg(ll,orgs,c.getKey());
			if(ll.size()>0) {
				orgBean.setChildren(ll);
			}
			orgBeans.add(orgBean);
		});
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 根据部门查询子部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午4:23:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"orgManager"})
	@RequestMapping(value = "/auth/orgManager/findOrgsByParentKey")
	@ResponseBody
	public String getAllOrgsTable(HttpServletRequest request,HttpServletResponse response, OrgQueryBean queryBean) {
		String orgParentKey = request.getParameter("orgParentKey");
		PageInfoBean pb = organizationService.getAllOrgsTable(orgParentKey,queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 保存部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 下午7:34:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"orgManager"})
	@RequestMapping(value = "/auth/organization/saveOrg")
	public String saveOrg(@RequestBody OrganizationInfo orgInfo, HttpServletRequest request,HttpServletResponse response) {
		ResultBean resultBean=new ResultBean();
	 	try {
	 		List<OrganizationInfo> organizationInfos = (List<OrganizationInfo>) baseCommonService.findAll(OrganizationInfo.class);
	 		if(organizationInfos != null && organizationInfos.size() >0) {
	 			long count = organizationInfos.stream().filter(s -> StringUtils.equals(s.getDataCode(), orgInfo.getDataCode()) &&!StringUtils.equals(s.getKey(), orgInfo.getKey())).count();
	 			if (count > 0) {
	 				return JsonUtils.toJsonString(resultBean.error("此部门编号已经存在，请重新确认"));
	 			}
	 		}
	 		organizationService.saveOrg(orgInfo);
	 		return JsonUtils.toJsonString(resultBean.ok("认证成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}

	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 删除部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:26:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"orgManager"})
	@RequestMapping(value = "/auth/orgManager/deleteOrgInfo")
	public String deleteOrgInfo(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		organizationService.deleteOrgByKey(key);
		return JsonUtils.toJsonString(resultBean.ok("部门删除成功"));
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 验证部门编号是否存在
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年2月19日 下午4:05:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年2月19日     zhaol           v1.0.0               修改原因
	 */
//	@RequestAuth(menuKeys = {"orgManager"})
//	@RequestMapping(value = "/auth/orgManager/orgValidation")
//	@ResponseBody
//	public String orgValidation(HttpServletRequest request) {
//		ResultBean resultBean=new ResultBean();
//		String dataCode = request.getParameter("dataCode");
//		String key = request.getParameter("key");
//		List<OrganizationInfo> orgs = (List<OrganizationInfo>) baseCommonService.findAll(OrganizationInfo.class);
//		long count=orgs.stream().filter((e)->e.getDataCode().equals(dataCode)).count();
//		if (!StringUtils.isBlank(key)) {
//			OrganizationInfo orgInfo=(OrganizationInfo)baseCommonService.findByKey(OrganizationInfo.class, key);
//			System.out.println(JsonUtils.toJsonString(orgInfo));
//			if(orgInfo != null) {
//				if(StringUtils.equals(dataCode, orgInfo.getDataCode())) {
//					return JsonUtils.toJsonString(resultBean.ok("此部门编号尚未修改"));
//				}else {
//					if(!dataCode.equals(orgInfo.getDataCode()) && count > 0l) {
//						return JsonUtils.toJsonString(resultBean.error("该部门编号已经存在，请重新确认"));
//					}
//				}
//			}
//		}
//		if (count >= 1l) {
//			return JsonUtils.toJsonString(resultBean.error("该部门编号已经存在，请重新确认"));
//		}
//		if(StringUtils.isNotBlank(dataCode)) {
//			return JsonUtils.toJsonString(resultBean.ok("该部门编号可以使用"));
//		}
//		return JsonUtils.toJsonString("");
//		
//	}
	
	@RequestAuth(menuKeys = {"orgManager"})
	@RequestMapping(value = "/auth/orgManager/getTreeLazyNode")
	public String getTreeNode(@RequestParam(required = false) String id) {
		List<OrgTreeNodeLazy> trees=new ArrayList<OrgTreeNodeLazy>();
		if(StringUtils.isBlank(id)) {
			OrganizationInfo organizationInfo = organizationService.getFirstNode();
			OrgTreeNodeLazy tn=new OrgTreeNodeLazy();
			tn.setId(organizationInfo.getKey());
			tn.setPid(organizationInfo.getParentKey());
			tn.setName(organizationInfo.getName());
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
		    			List list=orgs.stream().filter(d->StringUtils.equals(d.getParentKey(),organizationInfo.getKey())).collect(Collectors.toList());
		    			tn.setLeaf(list==null||list.size()<1);
		    			trees.add(tn);
					}
				}
			}
		}
		return (trees==null?"[]":JsonUtils.toJsonString(trees));
	}
	
}
