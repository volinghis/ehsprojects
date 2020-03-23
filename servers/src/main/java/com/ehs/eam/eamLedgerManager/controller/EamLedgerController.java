/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.controller 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:59:10 
 */
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

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;
import com.ehs.eam.eamLedgerManager.service.EamLedgerService;
import com.ehs.eam.eamLedgerManager.service.EamScrapService;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamLedgerController.java
 * @Description: 设备更新列表
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月30日 下午3:59:10
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年12月30日
 * qjj v1.0.0 修改原因
 */
@RequestMapping("/eam/eamLedger")
@RestController
public class EamLedgerController {

	@Resource
	private EamLedgerService eamLedgerService;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private EamScrapService eamScrapService;
	
	/**
	 * 
	 * @Function:getEamLedgerList
	 * @Description: 获取所有设备记录
	 * @param querybean
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年12月31日 上午10:30:47
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2019年12月31日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getList")
	public String getEamLedgerList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerService.findEamLedgerList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	
	

	/**
	 * 
	 * @Function:saveEamLedger
	 * @Description: 设备台账保存或修改
	 * @param eamLedger
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年12月31日 下午12:00:14
	 *
	 *  Modification History: Date Author Version Description
	 *  ---------------------------------------------------------* 2019年12月31日
	 *  qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/saveEamLedger")
	public String saveEamLedger(HttpServletRequest request, @RequestBody EamRequestBean eamRequestBean) {
		ResultBean resultBean = new ResultBean();
		eamLedgerService.saveEamLedger(eamRequestBean);
//		List<EamAccountPrint> roleInfoList=	(List<EamAccountPrint>)baseCommonService.findAll(EamAccountPrint.class);
//		if (roleInfoList!=null&&roleInfoList.size()>0) {
//			long c=roleInfoList.stream().filter(s->StringUtils.equals(s.getDeviceName(),eamAccountPrint.getDeviceName())&&!StringUtils.equals(s.getDeviceNum(), eamAccountPrint.getDeviceNum())).count();
//			if(c>0) {
//				return JsonUtils.toJsonString(resultBean.error("保存失败:已存在相同设备编号或设备名称"));
//			}
//		}
		return JsonUtils.toJsonString(resultBean.ok("保存成功"));
	}

	/**
	 * 
	 * @Function:getEamParamsByKey
	 * @Description: 根据设备key获取其参数
	 * @param request
	 * @param key     设备唯一标识
	 * @return json 格式设备参数数据
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月6日 下午2:16:31
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月6日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getEamParamsByKey")
	public String getEamParamsByKey(HttpServletRequest request, @RequestParam String key) {
		List<EamParameters> eamParameters = eamLedgerService.getEamParametersByKey(key);
		return eamParameters != null && eamParameters.size() > 0 ? JsonUtils.toJsonString(eamParameters) : "[]";
	}

	/**
	 * 
	* @Function:deleteEamParams 
	* @Description: 删除设备参数
	* @param request
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月10日 上午11:09:04 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/deleteEamParams")
	public String deleteEamParams(HttpServletRequest request, @RequestParam String key) {
		ResultBean resultBean = new ResultBean();
		try {
			baseCommonService.deleteByKey(EamParameters.class, key);
		} catch (Exception e) {

			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}

	/**
	 * 
	 * @Function:getInspectorsByKey
	 * @Description: 根据设备获取其历任点检员
	 * @param request
	 * @param key     设备唯一标识
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月6日 下午1:35:17
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月6日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getInspectorsByKey")
	public String getInspectorsByKey(HttpServletRequest request, @RequestParam String key) {
		List<EamInspectors> eamInspectors = eamLedgerService.getInspectorsByKey(key);
		return eamInspectors != null && eamInspectors.size() > 0 ? JsonUtils.toJsonString(eamInspectors) : "[]";
	}

	/**
	 * 
	 * @Function:deleteInspectors
	 * @Description: 历任点检员删除
	 * @param request
	 * @param key
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月6日 下午4:15:52
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月6日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/deleteInspectors")
	public String deleteInspectors(HttpServletRequest request, @RequestParam String key) {
		ResultBean resultBean = new ResultBean();
		try {
			baseCommonService.deleteByKey(EamInspectors.class, key);
		} catch (Exception e) {

			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}


	/**
	 * 
	 * @Function:deleteEamLedgerByKey
	 * @Description: 设备台账删除
	 * @param key     设备主键
	 * @param request
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月7日 上午9:50:49
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月7日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/deleteEamLedgerByKey")
	public String deleteEamLedgerByKey(@RequestParam String key, HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		try {
			EamLedger eamLedger=baseCommonService.findByKey(EamLedger.class, key);
			if (eamLedger!=null) {
				if (eamLedger.getDeviceStatus().contains("申请中")) {
					return JsonUtils.toJsonString(resultBean.error("申请中的设备不能删除"));
				}
			}
			eamLedgerService.deleteEamLedger(key);
		} catch (Exception e) {

			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}

	/**
	 * 
	 * @Function:removeEamLedger
	 * @Description: 移除当前设备的子设备
	 * @param key
	 * @param request
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月7日 下午4:39:07
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月7日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/removeEamLedger")
	public String removeEamLedger(@RequestParam String deviceKey, @RequestParam String keys) {
		ResultBean resultBean = new ResultBean();
		try {
			eamLedgerService.removeRelatedEamLedgers(deviceKey, keys);
		} catch (Exception e) {

			return JsonUtils.toJsonString(resultBean.error("移除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("移除成功"));
	}
	
	/**
	 * 
	* @Function:removeEamLedger 
	* @Description: 删除关联文件
	* @param deviceKey
	* @param keys
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月12日 下午2:34:29 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月12日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/removeRefFile")
	public String removeRefFile(@RequestParam String deviceKey, @RequestParam String key) {
		ResultBean resultBean = new ResultBean();
		try {
			eamLedgerService.removeRelatedFile(deviceKey, key);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("移除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("移除成功"));
	}

	
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getEamLedgerByKey")
	public String getEamLedgerByKey(@RequestParam String key) {
		EamLedger eamLedger=baseCommonService.findByKey(EamLedger.class, key);
		return eamLedger == null ? "{}" : JsonUtils.toJsonString(eamLedger);
	}
	
	
	/**
	 * 
	* @Function:getEamLedgersNotInFlow 
	* @Description: 获取不在流程中的设备
	* @param querybean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月10日 下午4:48:09 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getEamLedgersNotInFlow")
	public String getEamLedgersNotInFlow(@RequestBody EamLedgerQueryBean querybean) {
		PageInfoBean pageBean = eamLedgerService.findEamLedgersNotInFlow(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}
	
	@RequestAuth(menuKeys = {"eamLedger"})
	@RequestMapping(value = "/getSuggestions")
	public String getSuggestions() {
		List<EamLedger> eamLedgers=	(List<EamLedger>) baseCommonService.findAll(EamLedger.class);
		List<Object> resList=new  ArrayList<Object>();
		for (EamLedger el : eamLedgers) {
			Map<String, String> innerMap=new HashMap<String, String>();
			innerMap.put("value", el.getDeviceName());
			innerMap.put("key",el.getKey());
			resList.add(innerMap);
		}
		return JsonUtils.toJsonString(resList);
	}
}
