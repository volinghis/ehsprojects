package com.ehs.common.notifys.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

@MappedSuperclass
public abstract class NotifyMessageInfoSuper extends BaseEntity{

	private static final long serialVersionUID = 1L;
	public static final String USER="user";
	public static final String READED="readed";
	private String title;
	@Column(length = 4000)
	private String content;
	
	private String user;
	private String userName;
	private Boolean readed=false;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getReaded() {
		return readed;
	}
	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	

	
	
	
	
}
