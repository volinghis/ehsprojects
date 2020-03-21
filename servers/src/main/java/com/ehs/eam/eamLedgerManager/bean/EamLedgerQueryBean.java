/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:00:18 
 */
package com.ehs.eam.eamLedgerManager.bean;

import com.ehs.common.oper.bean.PageBody;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamLedgerQueryBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月30日 下午4:00:18
 *
 *  Modification History: Date Author Version Description
 *  ---------------------------------------------------------* 2019年12月30日
 *  qjj v1.0.0 修改原因
 */
public class EamLedgerQueryBean extends PageBody {

	private String name;
	
	private String profession;
	
	private String deviceSystem;
	
	private String deviceKey;
	
	private String complete;
	
	private String status;
	
	private String time;

	private String address;
	
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the complete
	 */
	public String getComplete() {
		return complete;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(String complete) {
		this.complete = complete;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the deviceKey
	 */
	public String getDeviceKey() {
		return deviceKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param deviceKey the deviceKey to set
	 */
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the deviceSystem
	 */
	public String getDeviceSystem() {
		return deviceSystem;
	}

	/**
	 * @param deviceSystem the deviceSystem to set
	 */
	public void setDeviceSystem(String deviceSystem) {
		this.deviceSystem = deviceSystem;
	}
	
}
