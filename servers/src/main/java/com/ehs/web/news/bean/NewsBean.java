package com.ehs.web.news.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewsBean {

	private String key;
	
	private String newsTitle;
	
	private boolean major;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Date creationTime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public boolean isMajor() {
		return major;
	}

	public void setMajor(boolean major) {
		this.major = major;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public NewsBean() {
		super();
	}

	public NewsBean(String key, String newsTitle, boolean major, Date creationTime) {
		super();
		this.key = key;
		this.newsTitle = newsTitle;
		this.major = major;
		this.creationTime = creationTime;
	}
	
}
