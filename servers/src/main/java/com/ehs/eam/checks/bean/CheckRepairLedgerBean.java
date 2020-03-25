/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.bean 
 * @author: qjj   
 * @date: 2020年3月25日 下午2:00:54 
 */
package com.ehs.eam.checks.bean;

import com.ehs.common.oper.bean.PageBody;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: CheckRepairLedgerBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年3月25日 下午2:00:54
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年3月25日
 *        qjj v1.0.0 修改原因
 */
public class CheckRepairLedgerBean extends PageBody {

	private String result;

	private String userType;
	
	private String address;
	
	private String objectType;
	
	private String objectKey;
	

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

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
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the objectKey
	 */
	public String getObjectKey() {
		return objectKey;
	}

	/**
	 * @param objectKey the objectKey to set
	 */
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

}
