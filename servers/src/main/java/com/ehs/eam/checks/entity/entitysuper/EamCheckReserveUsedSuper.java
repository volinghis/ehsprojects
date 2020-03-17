package com.ehs.eam.checks.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

@MappedSuperclass
public abstract class EamCheckReserveUsedSuper extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String reserveKey;
	
	private int total;
	private String taskKey;
	private String description;

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getReserveKey() {
		return reserveKey;
	}

	public void setReserveKey(String reserveKey) {
		this.reserveKey = reserveKey;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
