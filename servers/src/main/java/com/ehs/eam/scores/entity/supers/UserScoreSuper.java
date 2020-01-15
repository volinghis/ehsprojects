package com.ehs.eam.scores.entity.supers;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

@MappedSuperclass
public abstract class UserScoreSuper extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String USER_KEY="userKey";
	public static final String USER_NAME="userName";
	public static final String SCORE="score";
	
	public static final String MONTHS="months";
	public static final String YEARS="years";
	public static final String RATE="rate";
	
	private String userKey;
	
	private String userName;
	
	private Integer score=0;
	
	private Integer months;
	private Integer years;
	
	private Integer rate=5;

	/**
	 * 加分项名称
	 */
	private String itemName;
	
	/**
	 * 加分项操作时间
	 */
	private Timestamp itemCreateDate;

	
	



	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Timestamp getItemCreateDate() {
		return itemCreateDate;
	}

	public void setItemCreateDate(Timestamp itemCreateDate) {
		this.itemCreateDate = itemCreateDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
}
