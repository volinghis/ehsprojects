package com.ehs.eam.checks.bean;

import com.ehs.common.oper.bean.PageBody;

public class CheckTaskQueryBean extends PageBody{
    private String times;
    private String owners;
    private String checks;
    private String defects;
    private String revers;
    private String flowstatus;
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}
	public String getChecks() {
		return checks;
	}
	public void setChecks(String checks) {
		this.checks = checks;
	}
	public String getDefects() {
		return defects;
	}
	public void setDefects(String defects) {
		this.defects = defects;
	}
	public String getRevers() {
		return revers;
	}
	public void setRevers(String revers) {
		this.revers = revers;
	}
	public String getFlowstatus() {
		return flowstatus;
	}
	public void setFlowstatus(String flowstatus) {
		this.flowstatus = flowstatus;
	}
    
    
    
}
