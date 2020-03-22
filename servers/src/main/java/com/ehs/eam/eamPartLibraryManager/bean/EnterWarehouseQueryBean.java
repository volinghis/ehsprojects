package com.ehs.eam.eamPartLibraryManager.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: QueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:04:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
public class EnterWarehouseQueryBean extends PageBody {

	/**
	 * 查询条件
	 */
	private  String query;
	
	/**
	 * 所在仓库
	 */
	private String wareHouseNames;
	
	/**
	 * 入库类型
	 */
	private String inBoundTypes;
	
	/**
	 * 任务状态
	 */
	private String flowstatus;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getWareHouseNames() {
		return wareHouseNames;
	}

	public void setWareHouseNames(String wareHouseNames) {
		this.wareHouseNames = wareHouseNames;
	}

	public String getInBoundTypes() {
		return inBoundTypes;
	}

	public void setInBoundTypes(String inBoundTypes) {
		this.inBoundTypes = inBoundTypes;
	}

	public String getFlowstatus() {
		return flowstatus;
	}

	public void setFlowstatus(String flowstatus) {
		this.flowstatus = flowstatus;
	}
	
}
