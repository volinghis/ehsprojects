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
import com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.service.EamAllocateService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamAllocateController.java
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
@RequestMapping("/eam/eamAllocate")
public class EamAllocateController {

	@Resource
	private EamAllocateService eamAllocateService;

	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function:getEamAllocateList 
	* @Description: 获取设备报废申请记录
	* @param AllocateQueryBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月10日 下午3:15:42 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamAllocate" })
	@RequestMapping(value = "/getAllocateEamList")
	public String getEamAllocateList(@RequestBody EamAllocateQueryBean allocateQueryBean) {
		PageInfoBean pageBean = eamAllocateService.findEamAllocateList(allocateQueryBean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}

	/**
	 * 
	 * @Function:addEamAllocate
	 * @Description: 新增设备调拨申请
	 * @param reqBean
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2020年1月9日 上午10:51:36
	 *
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月9日
	 * qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamAllocate" })
	@RequestMapping(value = "/addEamAllocate")
	public String addEamAllocate(@RequestBody EamAllocateRequestBean reqBean) {
		ResultBean resultBean = new ResultBean();
		try {
			eamAllocateService.saveEamAllocate(reqBean);
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtils.toJsonString(resultBean.error("保存失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("保存成功"));
	}
	
	
	/**
	 * 
	* @Function:deleteEamAllocate 
	* @Description: 删除设备调拨记录
	* @param key
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月10日 下午3:17:50 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { "eamAllocate" })
	@RequestMapping(value = "/deleteEamAllocate")
	public String deleteEamAllocate(@RequestParam String keys) {
		ResultBean resultBean = new ResultBean();
		try {
			eamAllocateService.deleteEamAllocates(keys);
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtils.toJsonString(resultBean.error("删除失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("删除成功"));
	}
	
	/**
	 * 
	* @Function:getScrapFlowBean 
	* @Description: 获取设备调拨流程
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月14日 下午8:06:34 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月14日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"eamAllocate"})
	@RequestMapping(value = "/getAllocateFlowBean")
	public String getAllocateFlowBean(@RequestParam String key) {
	EamFlowBean eamFlowBean=	eamAllocateService.findAllocateFlowBean(key);
		return eamFlowBean!=null?JsonUtils.toJsonString(eamFlowBean):"{}";
	}
	
	
	
	@RequestAuth(menuKeys = {"eamAllocate"})
	@RequestMapping(value="/updateAfterAllocateFlow")
	public String updateAfterAllocateFlow(@RequestBody FlowProcessInfo flowProcessInfo) {
		ResultBean resultBean = new ResultBean();
		try {
			eamAllocateService.updateRelatedAfterFlow(flowProcessInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.toJsonString(resultBean.error("数据更新失败"));
		}
		return JsonUtils.toJsonString(resultBean.ok("数据更新成功"));
	}
}
