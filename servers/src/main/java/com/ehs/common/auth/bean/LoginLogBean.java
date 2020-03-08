/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.bean 
 * @author: qjj   
 * @date: 2020年3月6日 下午1:39:58 
 */
package com.ehs.common.auth.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月6日 下午1:39:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     qjj           v1.0.0               修改原因
*/
public class LoginLogBean extends PageBody {

	private String query;

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
