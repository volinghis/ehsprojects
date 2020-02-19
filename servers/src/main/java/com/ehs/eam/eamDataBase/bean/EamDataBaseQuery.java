package com.ehs.eam.eamDataBase.bean;

import com.ehs.common.oper.bean.PageBody;

public class EamDataBaseQuery extends PageBody {
	
	private String query;

	private String nodeKey;
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}
	
}
