/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:50:13 
 */
package com.ehs.eam.eamLedgerManager.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamScrapQueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月7日 下午7:50:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月7日     qjj           v1.0.0               修改原因
*/
public class EamAllocateQueryBean extends PageBody {

	private String query;
	
	private String status;
	
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
