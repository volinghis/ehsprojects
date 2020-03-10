/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.controller 
 * @author: qjj   
 * @date: 2020年1月8日 下午6:57:35 
 */
package com.ehs.eam.eamLedgerManager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;
import com.ehs.eam.eamLedgerManager.service.EamScrapService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamScrapController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月8日 下午6:57:35
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月8日
 *        qjj v1.0.0 修改原因
 */
@RestController
@RequestMapping("/eam/eamScrap")
public class EamScrapController {

	@Resource
	private EamScrapService eamScrapService;

	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	 * @Function:getEamScrapList
	 * @Description: 获取设备报废申请记录
	 * @param scrapQueryBean
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月10日 下午3:15:42
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2020年1月10日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/getScrapEamList")
	public String getEamScrapList(@RequestBody EamScrapQueryBean scrapQueryBean) {
		PageInfoBean pageBean = eamScrapService.findEamScrapList(scrapQueryBean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}

	/**
	 * 
	 * @Function:addEamScrap
	 * @Description: 新增设备报废申请
	 * @param reqBean
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月9日 上午10:51:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2020年1月9日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/addEamScrap")
	public String addEamScrap(@RequestBody EamScrapRequestBean reqBean) {
		ResultBean resultBean = new ResultBean();
		try {
			eamScrapService.saveEamScrap(reqBean);
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtils.toJsonString(resultBean.error("保存失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("保存成功"));
	}

	/**
	 * 
	 * @Function:deleteEamScrap
	 * @Description: 删除设备报废记录
	 * @param key
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月10日 下午3:17:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2020年1月10日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/deleteEamScrap")
	public String deleteEamScrap(@RequestParam String keys) {
		ResultBean resultBean = new ResultBean();
		try {
			eamScrapService.deleteEamScraps(keys);
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}

	/**
	 * 
	 * @Function:getScrapFlowBean
	 * @Description: 获取设备报废流程
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月14日 下午8:06:34
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2020年1月14日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/getScrapFlowBean")
	public String getScrapFlowBean(@RequestParam String key) {
		EamFlowBean eamScrapFlowBean = eamScrapService.findScrapFlowBean(key);
		return eamScrapFlowBean != null ? JsonUtils.toJsonString(eamScrapFlowBean) : "{}";
	}

	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/getEamLedgerByScrapKey")
	public String getEamLedgerByScrapKey(@RequestParam String key) {
		EamScrap eamScrap =baseCommonService.findByKey(EamScrap.class, key);
		EamLedger eamLedger=null;
		if (eamScrap!=null) {
			eamLedger = baseCommonService.findByKey(EamLedger.class, eamScrap.getDeviceKey());
		}
		return eamLedger != null ? JsonUtils.toJsonString(eamLedger) : "{}";
	}

	/**
	 * 
	* @Function:updateAfterFlow 
	* @Description:报废流程结束后的数据更新
	* @param flowProcessInfo
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年3月10日 下午3:44:05 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年3月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/updateAfterFlow")
	public String updateAfterFlow(@RequestBody FlowProcessInfo flowProcessInfo) {
		ResultBean resultBean = new ResultBean();
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		try {
			eamScrapService.updateRelatedAfterFlow(flowProcessInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("数据更新失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("数据更新成功"));
	}

	@RequestAuth(menuKeys = { "eamScrap" })
	@RequestMapping(value = "/getScrapByKey")
	public String getScrapByKey(@RequestParam String key) {
		EamScrap eamScrap = baseCommonService.findByKey(EamScrap.class, key);
		return eamScrap != null ? JsonUtils.toJsonString(eamScrap) : "{}";
	}
}
