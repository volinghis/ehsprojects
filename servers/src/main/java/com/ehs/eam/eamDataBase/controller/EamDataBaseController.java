package com.ehs.eam.eamDataBase.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestAuth(menuKeys = { "dataBase" })
	@RequestMapping("/getFileInfoList")
	public String getFileInfoList(@RequestBody EamDataBaseQuery querybean) {
		PageInfoBean pageBean=null;
		try {
			pageBean = eamDataBaseServie.findEamDataBaseList(querybean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	
	@RequestAuth(menuKeys = { "dataBase" })
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
	
}
