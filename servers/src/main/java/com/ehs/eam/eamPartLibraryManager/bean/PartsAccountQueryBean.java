package com.ehs.eam.eamPartLibraryManager.bean;

import com.ehs.common.oper.bean.PageBody;

public class PartsAccountQueryBean extends PageBody{

	/**
	 * 查询
	 */
	public String query;
	
	/**
	 * 所在仓库
	 */
	public String warehouseNames;
	
	/**
	 * 库存是否足够
	 */
	public String reserve;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getWarehouseNames() {
		return warehouseNames;
	}

	public void setWarehouseNames(String warehouseNames) {
		this.warehouseNames = warehouseNames;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
}
