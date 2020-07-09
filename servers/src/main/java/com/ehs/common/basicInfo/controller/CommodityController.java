package com.ehs.common.basicInfo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.basicInfo.bean.CommodityQueryBean;
import com.ehs.common.basicInfo.service.CommodityService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: CommodityController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 下午2:40:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping(value = "/basicInfo/commodityManager")
public class CommodityController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@Resource
	private CommodityService commodityService;

	/**
	 * 
	* @Function: CommodityController.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:04:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"commodity"})
	@RequestMapping(value = "/saveCommodity")
	public String saveCommodity(@RequestBody PartsAccount partsAccount) {
		logger.info("-------------开始保存商品----------");
		ResultBean resultBean=new ResultBean();
		try {
			commodityService.saveCommodity(partsAccount);
			return JsonUtils.toJsonString(resultBean.ok("祝贺你，商品信息创建成功 ！"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("很遗憾，商品信息创建失败！"));
	}
	
	/**
	 * 
	* @Function: CommodityController.java
	* @Description: 查找
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:04:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"commodity"})
	@RequestMapping(value = "/getCommodities")
	public String getCommodities(@RequestBody(required = false) CommodityQueryBean commodityQueryBean) {
		 PageInfoBean pb = commodityService.findCommodities(commodityQueryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: CommodityController.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:04:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"commodity"})
	@RequestMapping(value = "/deleteCommodity")
	public String deleteCommodity(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		commodityService.deleteCommodity(key);
		return JsonUtils.toJsonString(resultBean.ok("商品删除成功"));
	}
}
