package com.ehs.common.data.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.service.DataDictionaryService;

@RequestMapping("/data/dict")
@RestController
public class DataDictionaryController {

	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionaryService dataDictService;
	
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
