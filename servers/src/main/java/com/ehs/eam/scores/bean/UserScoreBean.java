package com.ehs.eam.scores.bean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: UserScoreBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:44:16 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
public class UserScoreBean {

	/**
	 * 用户key
	 */
	private String userKey;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 时间
	 */
	private String time;
	
	/**
	 * 标签
	 */
	private String label;
	
	/**
	 * 分数
	 */
	private Long score;
	
	/**
	 * 评分
	 */
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
