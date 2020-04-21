package com.ehs.web.news.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.web.news.bean.QueryBean;
import com.ehs.web.news.entity.News;
import com.ehs.web.news.service.NewsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: NewsController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月15日 上午9:24:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月15日     zhaol           v1.0.0               修改原因
*/
@RestController
@RequestMapping({"/portal/web/news","/web/news"})
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Resource
	private NewsService newsService;
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/getAllNews")
	public String getAllNews(@RequestBody QueryBean queryBean) {
		 PageInfoBean pb = newsService.getAllNews(queryBean);
		 return (pb==null?"[]":JsonUtils.toJsonString(pb));
		
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/getNewsList")
	public String getNewsList(@RequestParam String dataCode) {
		QueryBean queryBean = new QueryBean();
		queryBean.setDataCode(dataCode);
		queryBean.setPage(1);
		queryBean.setSize(5);
		PageInfoBean pb = newsService.getAllNews(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
		
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/saveNews")
	public String saveNews(@RequestBody News news) {
		ResultBean resultBean = new ResultBean();
		try {
			newsService.save(news);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			logger.error("保存方法--异常信息===="+e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/deleteNews")
	public String deleteNews(@RequestParam String key) {
		ResultBean resultBean = new ResultBean();
		try {
			newsService.deleteNews(key);
			return JsonUtils.toJsonString(resultBean.ok("删除成功"));
		} catch (Exception e) {
			logger.error("删除方法--异常信息===="+e.getMessage());
		}
		return JsonUtils.toJsonString(resultBean.error("删除失败"));
	}
}
