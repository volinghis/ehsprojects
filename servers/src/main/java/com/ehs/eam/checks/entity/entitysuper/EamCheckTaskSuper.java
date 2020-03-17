package com.ehs.eam.checks.entity.entitysuper;


import javax.persistence.MappedSuperclass;

import com.ehs.common.flow.entity.FlowBaseEntity;

@MappedSuperclass
public abstract class EamCheckTaskSuper extends FlowBaseEntity{

	private static final long serialVersionUID = 1L;
	private String planKey;
	private String name;
	private String result;
	private String description;
	private String user;
	private String org;
	
	public final static String USER="user";
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getPlanKey() {
		return planKey;
	}
	public void setPlanKey(String planKey) {
		this.planKey = planKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
