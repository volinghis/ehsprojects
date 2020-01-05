package com.ehs.common.organization.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgQueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月20日 上午8:58:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月20日     zhaol           v1.0.0               修改原因
*/
public class OrgQueryBean extends PageBody {
	
	private  String query;
	
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}
