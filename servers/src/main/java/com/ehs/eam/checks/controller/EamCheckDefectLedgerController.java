/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.controller 
 * @author: qjj   
 * @date: 2020年3月26日 上午10:48:46 
 */
package com.ehs.eam.checks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckDefectAnalysisBean;
import com.ehs.eam.checks.bean.CheckDefectLedgerBean;
import com.ehs.eam.checks.service.EamCheckDefectLedgerService;


/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckDefectLedgerController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月26日 上午10:48:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月26日     qjj           v1.0.0               修改原因
*/
@RestController
@RequestMapping("/eam/defectLedger")
public class EamCheckDefectLedgerController {
	
	private static final Logger logger=LoggerFactory.getLogger(EamCheckDefectLedgerController.class);
	
	@Resource
	private EamCheckDefectLedgerService defectLedgerService;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;

	/**
	 * 
	* @Function:getDefectLedgerList 
	* @Description: 获取缺陷信息所有记录
	* @param defectLedgerBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午10:41:53 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getDefectLedgerList")
	public String getDefectLedgerList(@RequestBody CheckDefectLedgerBean defectLedgerBean) {
		try {
			PageInfoBean pb=defectLedgerService.findAllDefectLedgers(defectLedgerBean);
			return pb!=null?JsonUtils.toJsonString(pb):"[]";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "[]";
	}
	
	/**
	 * 
	* @Function:addDatasAfterFlow 
	* @Description: 巡检任务结束后缺陷记录插入
	* @param flowProcessInfo 流程信息对象
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午10:42:59 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"defectLedger"})
	@RequestMapping(value = "/addDatasAfterFlow")
	public void addDatasAfterFlow(@RequestBody FlowProcessInfo flowProcessInfo) {
		try {
			defectLedgerService.addDatasAfterFlow(flowProcessInfo);
		} catch (Exception e) {
			logger.error("任务巡检--缺陷记录--回调错误："+e.getMessage());;
		}
		
	}
	
	/**
	 * 
	* @Function:defectAnalysisForIndexPage 
	* @Description: 封装缺陷统计数据
	* @param type
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午10:45:03 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/defectAnalysisForIndexPage")
	public String defectAnalysisForIndexPage(@RequestParam String type) {
		List<CheckDefectAnalysisBean> analysisBeans=defectLedgerService.analysisByType(type, false, false);
		if(analysisBeans!=null&&!analysisBeans.isEmpty()) {
			List<List<String>> mm=new ArrayList<List<String>>();
			analysisBeans.forEach(s->{
				System.out.println(JsonUtils.toJsonString(s));
			});
			List<String> addresses =analysisBeans.stream().map(CheckDefectAnalysisBean::getAddressName).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
			addresses.add(0,"product");
			mm.add(addresses);
			List<String> typeList =analysisBeans.stream().map(CheckDefectAnalysisBean::getObjectName).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
			typeList.forEach(ss->{
				List<String> counts=new ArrayList<String>();
				counts.add(ss);
				addresses.forEach(s->{
				long l=analysisBeans.stream().filter(p->StringUtils.equals(s, p.getAddressName())&&StringUtils.equals(ss, p.getObjectName())).count();
				if(l>0) {
//					counts.add(analysisBeans.stream().filter(p->StringUtils.equals(s, p.getAddressName())&&StringUtils.equals(ss, p.getObjectName())).findFirst().get().getCount()+"");
					counts.add(Math.random()+"");

				}
				});
				mm.add( counts);
			});
			return JsonUtils.toJsonString(mm);
		}
		return "[]";
	}

	/**
	 * 
	* @Function:getAnalysisByType 
	* @Description: 根据类型获取缺陷数据
	* @param type (deviceProfessiona或deviceSystem)
	* @param onlyMajor
	* @param onlyStatusError
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午10:46:42 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getAnalysisByType")
	public String getAnalysisByType(@RequestParam String type,@RequestParam boolean onlyMajor, @RequestParam boolean onlyStatusError) {
		List<CheckDefectAnalysisBean> analysisBeans=defectLedgerService.analysisByType(type, onlyMajor, onlyStatusError);
		return JsonUtils.toJsonString(analysisBeans);
	}
	
	/**
	 * 
	* @Function:getFlowProcessInfoByBusinessKey 
	* @Description: 根据businessKey获取流程对象
	* @param key businessKey
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年4月13日 上午10:56:11 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年4月13日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/getFlowProcessInfoByBusinessKey")
	public String getFlowProcessInfoByBusinessKey(@RequestParam String key) {
		FlowProcessInfo flowInfo=flowProcessInfoService.findProcessInfoByEntityKey(key);
		return JsonUtils.toJsonString(flowInfo);
	}
}
