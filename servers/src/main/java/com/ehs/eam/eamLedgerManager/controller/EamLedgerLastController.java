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
	 * 
	* @Function:getEamLedgerLastList 
	* @Description: 设备台账列表数据
	* @param querybean
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午9:39:20 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/getLastList")
	public String getEamLedgerLastList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerLastService.findEamLedgerLastList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	/**
	 * 
	* @Function:getLastSuggestions 
	* @Description: 该函数的功能描述
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午9:38:38 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY })
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
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY  })
	@RequestMapping(value = "/getLeftEamLedgerList")
	public String getLeftEamLedgerList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerLastService.findLeftEamLedgerList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
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
	
	
	/**
	 * 
	 * @Function:getChildDevByKey
	 * @Description:获取当前设备的子设备
	 * @param key     当前设备的主键
	 * @param request
	 * @return 所有子设备的json格式数据
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月7日 上午9:44:42
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月7日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY  })
	@RequestMapping(value = "/getChildDevByKey")
	public String getChildDevByKey(@RequestParam String key, HttpServletRequest request) {
		List<EamLedgerLast> eamLedgers = eamLedgerLastService.getChildDevByKey(key);
		return eamLedgers != null ? JsonUtils.toJsonString(eamLedgers) : "[]";
	}
	
	
	/**
	 * 
	* @Function:getChildDevByKey 
	* @Description: 根据流程获取当前设备信息
	* @param key
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月24日 下午5:43:10 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月24日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY  })
	@RequestMapping(value = "/getEamLedgerByEntityKey")
	public String getEamLedgerByEntityKey(@RequestParam String key, HttpServletRequest request) {
		EamLedgerLast eamLedger = eamLedgerLastService.findEamLedgerByRefKey(key);
		return  JsonUtils.toJsonString(eamLedger);
	}
	
}
