package com.ehs.eam.checks.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;


@MappedSuperclass
public abstract class EamCheckDefectSuper extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	private String content;
	private String taskKey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTaskKey() {
		return taskKey;
	}
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
	
	
}
