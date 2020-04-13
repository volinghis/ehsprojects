package com.ehs.eam.eamDataBase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.service.DataDictionaryService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamDataBase.bean.DataDictTreeBean;
import com.ehs.eam.eamDataBase.bean.EamDataBaseQuery;
import com.ehs.eam.eamDataBase.service.EamDataBaseServie;

@RestController
@RequestMapping("/eam/dataBase")
public class EamDataBaseController {

	@Resource
	private EamDataBaseServie eamDataBaseServie;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private DataDictionaryService dataDictService;

	/**
	 * 
	* @Function:getFileInfoList 
	* @Description: 获取资料管理列表信息
	* @param querybean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午9:45:33 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/getFileInfoList")
	public String getFileInfoList(@RequestBody EamDataBaseQuery querybean) {
		PageInfoBean pageBean=null;
		try {
			pageBean = eamDataBaseServie.findEamDataBaseList(querybean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	/**
	 * 
	* @Function:getFileCategoriesForTree 
	* @Description: 获取资料类型树结构数据
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月3日 下午1:37:50 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月3日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/getFileCategoriesForTree")
	public String getFileCategoriesForTree(HttpServletRequest request) {
		String parentKey = "edmEquipmentInfo";
		List<DataDictTreeBean> childList = new LinkedList<DataDictTreeBean>();
		List<DataDictionary> List = dataDictService.findDataDictByParentKey(parentKey);
		if (!CollectionUtils.isEmpty(List)) {
			for (DataDictionary data : List) {
				DataDictTreeBean inner = new DataDictTreeBean();
				inner.setId(data.getKey());
				inner.setLabel(data.getText());
				inner.setParentId(parentKey);
				childList.add(inner);
			}
		}
		List<DataDictTreeBean> resultList = new LinkedList<DataDictTreeBean>();
		DataDictionary dd = baseCommonService.findByKey(DataDictionary.class, parentKey);
		if (dd != null) {
			DataDictTreeBean ddtree = new DataDictTreeBean();
			ddtree.setId(dd.getKey());
			ddtree.setLabel(dd.getText());
			ddtree.setParentId(dd.getParentKey());
			ddtree.setChildren(childList);
			resultList.add(ddtree);
		}
		return JsonUtils.toJsonString(resultList);
	}
	
	/**
	 * 
	* @Function:saveDataFileInfo 
	* @Description:保存资料
	* @param fileId 当前的文件唯一标识
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午9:46:28 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "dataBase" })
	@RequestMapping("/saveDataFileInfo")
	public String saveDataFileInfo(@RequestParam String fileId ) {
		ResultBean resultBean = new ResultBean();
		try {
			eamDataBaseServie.saveDataFileInfo(fileId);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));
		
	}
	
	/**
	 * 
	* @Function:getFileCategories 
	* @Description: 获取文件类型数据
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午9:47:14 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/getFileCategories")
	public String getFileCategories() {
		String key="edmEquipmentInfo";
		List<DataDictionary> dataDict=dataDictService.findDataDictByParentKey(key);
		List<Object> resList=new  ArrayList<Object>();
		for (DataDictionary dict : dataDict) {
			Map<String, String> innerMap=new HashMap<String, String>();
			innerMap.put("label", dict.getText());
			innerMap.put("value", dict.getKey());
			resList.add(innerMap);
		}
		return JsonUtils.toJsonString(resList);
	}
}
