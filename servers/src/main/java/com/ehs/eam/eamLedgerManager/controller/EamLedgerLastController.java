package com.ehs.eam.eamLedgerManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
import com.ehs.eam.eamLedgerManager.service.EamLedgerLastService;

@RequestMapping("/eam/eamLedgerLast")
@RestController
public class EamLedgerLastController {

	@Resource
	private EamLedgerLastService eamLedgerLastService;
	
	@Resource
	private BaseCommonService baseCommonService;
	/**
	 *     设备台账记录
	 * @param querybean
	 * @param request
	 * @return
	 */
	@RequestAuth(menuKeys = { "eamLedgerLast" })
	@RequestMapping(value = "/getLastList")
	public String getEamLedgerLastList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerLastService.findEamLedgerLastList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	
	@RequestAuth(menuKeys = {"eamLedgerLast"})
	@RequestMapping(value = "/getLastSuggestions")
	public String getLastSuggestions() {
		List<EamLedger> eamLedgers=	(List<EamLedger>) baseCommonService.findAll(EamLedger.class);
		List<Object> resList=new  ArrayList<Object>();
		for (EamLedger el : eamLedgers) {
			Map<String, String> innerMap=new HashMap<String, String>();
			innerMap.put("value", el.getDeviceName());
			resList.add(innerMap);
		}
		return JsonUtils.toJsonString(resList);
	}
	
	
	/**
	 * 
	 * @Function:getLeftEamLedgerList
	 * @Description: 不属于当前设备子设备的其他设备
	 * @param querybean
	 * @param request
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月7日 上午11:44:29
	 *
	 *  Modification History: Date Author Version Description
	 *  ---------------------------------------------------------* 2020年1月7日
	 *  qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedgerLast" })
	@RequestMapping(value = "/getLeftEamLedgerList")
	public String getLeftEamLedgerList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerLastService.findLeftEamLedgerList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	/**
	 * 
	* @Function:getEamLedgerListNotPage 
	* @Description:获取所有设备台账为分页
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月17日 下午7:52:23 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月17日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedgerLast" })
	@RequestMapping(value = "/getListNotPage")
	public String getEamLedgerListNotPage( HttpServletRequest request) {
		List<EamLedgerLast>  eamLedgers= (List<EamLedgerLast>) baseCommonService.findAll(EamLedgerLast.class);
	    List<EamLedgerLast> resultList=	eamLedgers.stream().filter(s -> (StringUtils.isEmpty(s.getScrapKey()))).collect(Collectors.toList());
		return resultList == null ? "[]" : JsonUtils.toJsonString(resultList);
	}

}
