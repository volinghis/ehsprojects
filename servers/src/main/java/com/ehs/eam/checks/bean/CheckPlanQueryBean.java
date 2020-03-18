package com.ehs.eam.checks.bean;

import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.SortBean;

public class CheckPlanQueryBean extends PageBody{

//	private String query;
//	private boolean byowner=false;
//	private boolean effective=false;
//	private boolean enable=false;
	
	private String rates;
	private String types;
	private String status;
	private String executes;
	
	
	
	
	
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExecutes() {
		return executes;
	}
	public void setExecutes(String executes) {
		this.executes = executes;
	}

	

	
	
}
