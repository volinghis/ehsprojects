package com.ehs.common.basicInfo.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientQueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:38:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
public class ClientQueryBean extends PageBody{

	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
