package com.ehs.eam.eamPartLibraryManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.service.PartsBaseInfoService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:11:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/eam/partsBaseInfo")
public class PartsBaseInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartsBaseInfoController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private PartsBaseInfoService partsBaseInfoService;
	
	/**
	 * 
	* @Function: PartsAccountController.java
	* @Description: 保存备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 上午10:27:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"partsBaseInfo"})
	@RequestMapping(value = "/savePartsBaseInfo")
	public String savePartsBaseInfo(@RequestBody PartsInfoBean partsBean, HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入saveEamPartLibrary方法=============");
		ResultBean resultBean=new ResultBean();
		try {
			partsBaseInfoService.savePartsBaseInfo(partsBean);
			return JsonUtils.toJsonString(resultBean.ok("保存备件基本信息成功！"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			logger.info("===========退出saveEamPartLibrary方法=============");
		return JsonUtils.toJsonString(resultBean.error("保存备件基本信息失败"));
	}
	
	/**
	 * 
	* @Function: PartsAccountController.java
	* @Description: 查找备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 上午10:27:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"partsBaseInfo"})
	@RequestMapping(value = "/getPartsBaseInfos")
	public String getPartsBaseInfos(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getPartsBaseInfoAll方法=============");
		PageInfoBean pb = partsBaseInfoService.getPartsBaseInfos(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: PartsAccountController.java
	* @Description: 删除备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 上午10:28:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"partsBaseInfo"})
	@RequestMapping(value = "/deletePartsAccount")
	public String deletePartsAccount(HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入deleteEamPartLibrary方法=============");
		ResultBean resultBean=new ResultBean();
		try {
			String key=request.getParameter("key");
			partsBaseInfoService.deletePartsByKey(key);
			return JsonUtils.toJsonString(resultBean.ok("删除备件成功！"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("删除备件失败！"));
	}


}
