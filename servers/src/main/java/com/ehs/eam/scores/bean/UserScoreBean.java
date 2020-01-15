package com.ehs.eam.scores.bean;

public class UserScoreBean {

	private String userKey;
	
	private String userName;
	
	private String time;
	
	private String label;
	
	private Long score;
	
	private Double rate;
	
	public UserScoreBean() {
		
	}

	
	public UserScoreBean(String userKey,String userName,Long score,Double rate) {
		this.userKey=userKey;
		this.userName=userName;
		this.score=score;
		this.rate=rate;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}




	public String getUserKey() {
		return userKey;
	}


	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	public Long getScore() {
		return score;
	}


	public void setScore(Long score) {
		this.score = score;
	}


	

	
	
	
}
