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
	
	/**
	 * 所在仓库
	 */
	private String wareHouse;
	private String wareHouseName;
	/**
	 * 备件名称
	 */
	private String deviceName;
	/**
	 * 备件编号
	 */
	private String deviceCode;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 领用人
	 */
	private String receivePerson;
	private String receivePersonName;
	/**
	 * 领用部门
	 */
	private String receiveOrg;

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

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceivePersonName() {
		return receivePersonName;
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	public String getReceiveOrg() {
		return receiveOrg;
	}

	public void setReceiveOrg(String receiveOrg) {
		this.receiveOrg = receiveOrg;
	}
	
}
