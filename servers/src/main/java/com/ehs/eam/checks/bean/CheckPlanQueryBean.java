package com.ehs.eam.checks.bean;

import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.SortBean;

public class CheckPlanQueryBean extends PageBody{

	private String query;
	private boolean byowner=false;
	private boolean effective=false;
	private boolean enable=false;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isByowner() {
		return byowner;
	}
	public void setByowner(boolean byowner) {
		this.byowner = byowner;
	}
	public boolean isEffective() {
		return effective;
	}
	public void setEffective(boolean effective) {
		this.effective = effective;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	

	
	
}
