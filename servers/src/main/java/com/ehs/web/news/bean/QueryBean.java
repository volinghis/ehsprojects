package com.ehs.web.news.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: QueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月15日 上午10:00:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月15日     zhaol           v1.0.0               修改原因
*/
public class QueryBean extends PageBody{

	public String dataCode;
	
	public String query;
	
	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
