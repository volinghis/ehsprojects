/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamAccountPrint.entity.entitySuper 
 * @author: qjj   
 * @date: 2019年10月31日 下午1:56:48 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;

import javax.persistence.MappedSuperclass;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamAccountPrint.java
 * @Description: 设备台账实体类
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年10月31日 下午1:56:48
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年10月31日
 *        qjj v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamLedgerSuper extends EamLedgerFlowSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	public static final String REF_KEY = "refKey";
	
	
	private String refKey;


	public String getRefKey() {
		return refKey;
	}


	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	
	
}
