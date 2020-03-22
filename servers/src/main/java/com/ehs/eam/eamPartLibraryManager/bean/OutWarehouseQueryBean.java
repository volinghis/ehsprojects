package com.ehs.eam.eamPartLibraryManager.bean;

import com.ehs.common.oper.bean.PageBody;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWarehouseQueryBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月20日 上午10:16:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月20日     zhaol           v1.0.0               修改原因
*/
public class OutWarehouseQueryBean extends PageBody{

	/**
	 * 查询条件
	 */
	private  String query;
	
	/**
	 * 所在仓库
	 */
	private String warehouseNames;
	
	/**
	 * 入库类型
	 */
	private String outBoundTypes;
	
	/**
	 * 任务状态
	 */
	private String flowstatus;

	public String getQuery() {
		return query;
	}

	public String getWarehouseNames() {
		return warehouseNames;
	}

	public void setWarehouseNames(String warehouseNames) {
		this.warehouseNames = warehouseNames;
	}

	public String getOutBoundTypes() {
		return outBoundTypes;
	}

	public void setOutBoundTypes(String outBoundTypes) {
		this.outBoundTypes = outBoundTypes;
	}

	public String getFlowstatus() {
		return flowstatus;
	}

	public void setFlowstatus(String flowstatus) {
		this.flowstatus = flowstatus;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
