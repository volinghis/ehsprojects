package com.ehs.common.auth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.auth.bean.MenuNode;
import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.entity.entitysuper.SysMenu;
import com.ehs.common.auth.enums.RoleType;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.auth.service.MenuService;
import com.ehs.common.auth.service.RoleService;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.fasterxml.jackson.core.type.TypeReference;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月11日 下午2:28:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月11日     zhaol           v1.0.0               修改原因
*/
@Controller
public class MenuController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private RoleService roleService;


	/**
	 * 
	* @Function: MenuController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月11日 下午2:28:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月11日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/menu/menuDatas")
	@ResponseBody
	public String getMenu(HttpServletRequest request,HttpServletResponse response) {
		List<SysMenu> smList =(List<SysMenu>)baseCommonService.findAll(SysMenu.class);

		if (smList == null || smList.isEmpty()) {
			return "[]";
		}
		
		
		List<MenuNode> menus = new ArrayList<MenuNode>();
		if(StringUtils.isNotBlank(SysAccessUser.get().getRoleKeys())&&Arrays.asList(StringUtils.split(SysAccessUser.get().getRoleKeys(), ",")).contains(AuthConstants.ADMIN_ROLE_KEY)) {
			createMenuNode(menus, smList, null);
		}else {
			List<SysMenu> smListByRoles= smList.stream().filter(sm->{
				if(!sm.getLeaf()) {
					return false;
				}
				if(StringUtils.isBlank(sm.getRoles())) {
					return true;
				}
				List<RoleBean> list= (List<RoleBean>)JsonUtils.parseObject(sm.getRoles(), new TypeReference<List<RoleBean>>(){});
				long fs=list.stream().filter(r->(RoleType.ORG==r.getRoleType()&&StringUtils.equals(r.getRoleKey(), SysAccessUser.get().getOrgKey()))
						||(RoleType.SYSUSER==r.getRoleType()&&StringUtils.equals(r.getRoleKey(), SysAccessUser.get().getSysUserKey()))
						||(RoleType.ROLE==r.getRoleType()&&StringUtils.isNotBlank(SysAccessUser.get().getRoleKeys())&&Arrays.asList(StringUtils.split(SysAccessUser.get().getRoleKeys(), ",")).contains(r.getRoleKey()))
						).count();
				return fs>0;
			}
			).collect(Collectors.toList());
			if(smListByRoles==null||smListByRoles.isEmpty()) {
				return "[]";
			}
			List<String> parentKeyList=new ArrayList<String>();
			smListByRoles.forEach(s->{
				 int i=0;
				 String parentKey=s.getParentKey();
				 while(StringUtils.isNotBlank(parentKey)&&i<100) {
					 if(!parentKeyList.contains(parentKey)) {
						 parentKeyList.add(parentKey);
					 }
					 final String temp=parentKey;
					 SysMenu sm=smList.stream().filter(ss->StringUtils.equals(ss.getKey(), temp)).findFirst().get();
					 parentKey=sm.getParentKey();
					 i++;
				 }
			});
			
			
			List<SysMenu> resultMenus=new ArrayList<SysMenu>();
			resultMenus.addAll(smListByRoles);
			parentKeyList.forEach(s->{
				 SysMenu sm=smList.stream().filter(ss->StringUtils.equals(ss.getKey(), s)).findFirst().get();
				 resultMenus.add(sm);
			});
			createMenuNode(menus, resultMenus, null);

		}
		menus.sort((a, b) -> (a.getSort()==null?0:a.getSort()) - (b.getSort()==null?0:b.getSort()));
		return JsonUtils.toJsonString(menus);
	}

	/**
	 * 
	* @Function: MenuController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月11日 下午2:28:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月11日     zhaol           v1.0.0               修改原因
	 */
	private void createMenuNode(List<MenuNode> menuNodes,List<SysMenu> menus,String parentkey) {
		menus.stream().filter(s->StringUtils.equals(s.getParentKey(),parentkey)).forEach(c->{
			
				MenuNode menuNode=new MenuNode();
		
				List<MenuNode> ll=new ArrayList<MenuNode>();
				createMenuNode(ll,menus,c.getKey());
				if(ll.size()>0) {
					ll.sort((a, b) -> (a.getSort()==null?0:a.getSort()) - (b.getSort()==null?0:b.getSort()));
					menuNode.setChildren(ll);
				}
				boolean isAdd=ll.size()>0||c.getLeaf();
				if(isAdd) {
					menuNode.setKey(c.getKey());
					menuNode.setCode(c.getKey());
					menuNode.setLabel(c.getName());
					menuNode.setPath(c.getUrl());
					menuNode.setComponent(c.getUrl());
					menuNode.setIcon(c.getIcon());
					menuNode.setBusiness(c.getBusiness());
					menuNode.setLeaf(c.getLeaf());
					menuNode.setSort(c.getSort());
					menuNodes.add(menuNode);
				}
				
			
		});
	}
	/**
	 * 根据菜单获取角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestAuth(menuKeys = {"menuManager"})
	@RequestMapping(value = "/auth/menu/findMenuRoles")
	@ResponseBody
	public String findMenuRoles(HttpServletRequest request, HttpServletResponse response) {
		String menuKey=request.getParameter("menuKey");
		return JsonUtils.toJsonString(roleService.findRolesByMenuKey(menuKey));
	}
	
	
	/**
	 * 获取待选择角色列表
	 */
	@RequestAuth(menuKeys = {"menuManager"})
	@RequestMapping(value = "/auth/menu/findAllRolesByMenuKey")
	@ResponseBody
	public String findAllRolesByMenuKey(@RequestBody RoleQueryBean roleQueryBean,HttpServletResponse response) {
		PageInfoBean pb = roleService.findRoles(roleQueryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function:saveMenuRole 
	* @Description: 菜单授权
	* @param roleBean
	* @param menuKey
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月16日 下午3:23:11 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月16日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"menuManager"})
	@RequestMapping(value = "/auth/menu/saveMenuRole")
	@ResponseBody
	public String saveMenuRole(@RequestBody MenuRolesBean menuRolesBean,HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		menuService.saveMenuRole(menuRolesBean);
		return JsonUtils.toJsonString(resultBean.ok("授权成功！",""));
	}
	
	
	
	@RequestAuth(menuKeys = {"menuManager"})
	@RequestMapping(value = "/auth/menu/deleteMenuRole")
	@ResponseBody
	public String deleteMenuRole(@RequestBody MenuRolesBean menuRolesBean, HttpServletRequest request, HttpServletResponse response) {
		ResultBean resultBean=new ResultBean();
		menuService.deleteMenuRole(menuRolesBean);
		return JsonUtils.toJsonString(resultBean.ok("删除成功！",""));
	}
}
