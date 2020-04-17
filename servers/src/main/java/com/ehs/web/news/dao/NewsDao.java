package com.ehs.web.news.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.web.news.entity.News;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: NewsDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月15日 上午10:09:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月15日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface NewsDao extends JpaRepository<News, String>{

	@Query(" select n from News n where n."+News.NEWS_TITLE+" like %?1% and n."+News.DATA_CODE+" = ?2 and n."+BaseEntity.DELETED+"=0 order by n."+BaseEntity.CREATION_TIME+" desc" )
	public Page<News> findAllNews(String query, String dataCode,PageRequest pageRequest); 

}
