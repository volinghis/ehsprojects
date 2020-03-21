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
	private String userName;
	private String org;
	private String orgName;
	private boolean repairs=false;
	private boolean defects=false;
	private boolean reserves=false;
	public final static String USER="user";
	public final static String PLAN_KEY="planKey";
	public final static String REPAIRS="repairs";
	public final static String DEFECTS="defects";
	public final static String RESERVES="reserves";
	public final static String RESULT="result";
	
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public boolean isRepairs() {
		return repairs;
	}
	public void setRepairs(boolean repairs) {
		this.repairs = repairs;
	}
	public boolean isDefects() {
		return defects;
	}
	public void setDefects(boolean defects) {
		this.defects = defects;
	}
	public boolean isReserves() {
		return reserves;
	}
	public void setReserves(boolean reserves) {
		this.reserves = reserves;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
