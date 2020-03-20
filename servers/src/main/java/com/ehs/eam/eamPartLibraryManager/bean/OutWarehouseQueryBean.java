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
	private String warehouse;
	
	/**
	 * 入库类型
	 */
	private String outBoundType;
	
	/**
	 * 任务状态
	 */
	private String status;

	public String getQuery() {
		return query;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getOutBoundType() {
		return outBoundType;
	}

	public void setOutBoundType(String outBoundType) {
		this.outBoundType = outBoundType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
