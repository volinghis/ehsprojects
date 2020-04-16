package com.ehs.web.news.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.web.news.bean.QueryBean;
import com.ehs.web.news.entity.News;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: NewsService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月15日 上午9:37:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月15日     zhaol           v1.0.0               修改原因
*/
public interface NewsService {

	/**
	 * 
	* @Function: NewsService.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月15日 上午9:37:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月15日     zhaol           v1.0.0               修改原因
	 */
	public void save(News news);

	/**
	 * 
	* @Function: NewsService.java
	* @Description: 查看
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月15日 上午10:01:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月15日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllNews(QueryBean queryBean);

	/**
	 * 
	* @Function: NewsService.java
	* @Description: 删除
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月15日 上午10:40:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月15日     zhaol           v1.0.0               修改原因
	 */
	public void deleteNews(String key);

}
