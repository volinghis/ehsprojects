package com.ehs.eam.eamLedgerManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.TreeDataBean;
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
	 * 设备台账记录
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
		List<EamLedgerLast> eamLedgers=	(List<EamLedgerLast>) baseCommonService.findAll(EamLedgerLast.class);
		List<Object> resList=new  ArrayList<Object>();
		for (EamLedgerLast el : eamLedgers) {
			Map<String, String> innerMap=new HashMap<String, String>();
			innerMap.put("value", el.getDeviceName());
			innerMap.put("key",el.getRefKey());
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
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/getListNotPage")
	public String getEamLedgerListNotPage( HttpServletRequest request) {
		List<EamLedgerLast>  eamLedgers= (List<EamLedgerLast>) baseCommonService.findAll(EamLedgerLast.class);
		return eamLedgers == null ? "[]" : JsonUtils.toJsonString(eamLedgers);
	}
	
	/**
	 * 
	* @Function:getProfessionTreeForDevice 
	* @Description: 获取专业的树结构数据
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月17日 下午1:37:47 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月17日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getTreeForDevice")
	public String getTreeForDevice(@RequestParam String parentKey,@RequestParam String subKey) {
		List<TreeDataBean> dataTreeList=eamLedgerLastService.findTreeForDevice(parentKey,subKey);
		return JsonUtils.toJsonString(dataTreeList);
	}
}
