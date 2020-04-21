package com.ehs.web.news.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.web.news.bean.QueryBean;
import com.ehs.web.news.dao.NewsDao;
import com.ehs.web.news.entity.News;
import com.ehs.web.news.service.NewsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: NewsServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月14日 下午4:30:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月14日     zhaol           v1.0.0               修改原因
*/
@Service
public class NewsServiceImpl implements NewsService{
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private NewsDao newsDao;
	
	/**
	 * 
	* @see com.ehs.web.news.service.NewsService#getAllNews(com.ehs.web.news.bean.QueryBean)  
	* @Function: NewsServiceImpl.java
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月15日 上午10:18:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月15日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllNews(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize());
		Page<News> news = newsDao.findAllNews(queryBean.getQuery(),queryBean.getDataCode(), pageRequest);
		if (news != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(news.getContent());
			pb.setTotalCount(news.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.web.news.service.NewsService#save(com.ehs.web.news.entity.News)  
	* @Function: NewsServiceImpl.java
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月15日 上午10:18:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月15日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void save(News news) {
		try {
			if(news != null ) {
				baseCommonService.saveOrUpdate(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteNews(String key) {
		try {
			baseCommonService.deleteByKey(News.class, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @see com.ehs.web.news.service.NewsService#getALLNewsList(com.ehs.web.news.bean.QueryBean)  
	* @Function: NewsServiceImpl.java
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月21日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getALLNewsList(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize());
		Page<News> news = newsDao.getALLNewsList(queryBean.getQuery(),queryBean.getDataCode(), pageRequest);
		if (news != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(news.getContent());
			pb.setTotalCount(news.getTotalElements());
			return pb;
		}
		return null;
	}
	
}
