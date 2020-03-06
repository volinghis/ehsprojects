package com.ehs.common.data.controller;

import java.util.ArrayList;
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

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.service.DataDictionaryService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.OrgQueryBean;
import com.ehs.common.organization.bean.OrgTreeNodeLazy;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月6日 下午3:29:11 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/auth/dataDictionaryManager")
public class DataDictionaryController {
	
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function: DataDictionaryController.java
	* @Description: 懒加载树结构
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午3:28:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"dataDictionaryManager"})
	@RequestMapping(value = "/getTreeLazyNode")
	public String dataDictionaryTreeData(@RequestParam(required = false) String id) {
		List<OrgTreeNodeLazy> trees=new ArrayList<OrgTreeNodeLazy>();
		if(StringUtils.isBlank(id)) {
			DataDictionary dataDictionary = dataDictionaryService.getFirstNode();
			OrgTreeNodeLazy tn=new OrgTreeNodeLazy();
			tn.setId(dataDictionary.getKey());
			tn.setPid(dataDictionary.getParentCode());
			tn.setName(dataDictionary.getText());
			tn.setLeaf(false);
			trees.add(tn);
		}else {
			List<DataDictionary> dataDictionaries = (List<DataDictionary>) baseCommonService.findAll(DataDictionary.class);
			if (dataDictionaries != null && dataDictionaries.size() > 0) {
				for (DataDictionary dataDict : dataDictionaries) {
					if(StringUtils.equals(dataDict.getParentCode(), id)) {
						OrgTreeNodeLazy tn=new OrgTreeNodeLazy();
						tn.setId(dataDict.getKey());
						tn.setPid(dataDict.getParentCode());
						tn.setName(dataDict.getText());
		    			List list=dataDictionaries.stream().filter(d->StringUtils.equals(d.getParentCode(),dataDict.getKey())).collect(Collectors.toList());
		    			tn.setLeaf(list==null||list.size()<1);
		    			trees.add(tn);
					}
				}
			}
		}
		return (trees==null?"[]":JsonUtils.toJsonString(trees));
	}
	
	/**
	 * 
	* @Function: DataDictionaryController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午3:33:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"dataDictionaryManager"})
	@RequestMapping(value = "/findDatasByParentCode")
	@ResponseBody
	public String getAllOrgsTable(HttpServletRequest request,HttpServletResponse response, OrgQueryBean queryBean) {
		String parentCode = request.getParameter("parentCode");
		PageInfoBean pb = dataDictionaryService.getAllDatasTable(parentCode,queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: DataDictionaryController.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午4:31:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"dataDictionaryManager"})
	@RequestMapping(value = "/saveDataDictionary")
	public String saveOrg(@RequestBody DataDictionary data, HttpServletRequest request,HttpServletResponse response) {
		ResultBean resultBean=new ResultBean();
	 	try {
	 		List<DataDictionary> organizationInfos = (List<DataDictionary>) baseCommonService.findAll(DataDictionary.class);
	 		if(organizationInfos != null && organizationInfos.size() >0) {
	 			long count = organizationInfos.stream().filter(s -> StringUtils.equals(s.getDataCode(), data.getDataCode()) &&!StringUtils.equals(s.getKey(), data.getKey())).count();
	 			if (count > 0) {
	 				return JsonUtils.toJsonString(resultBean.error("此编号已经存在，请重新确认"));
	 			}
	 		}
	 		dataDictionaryService.saveDataDictionary(data);
	 		return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}
	
	/**
	 * 
	* @Function: DataDictionaryController.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午4:41:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"dataDictionaryManager"})
	@RequestMapping(value = "/deleteDataDictionary")
	public String deleteOrgInfo(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			String key=request.getParameter("key");
			dataDictionaryService.deleteOrgByKey(key);
			return JsonUtils.toJsonString(resultBean.ok("部门删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("部门删除失败"));
	}
}