package com.ehs.common.basicInfo.bean;

import java.util.List;

import com.ehs.common.basicInfo.entity.ContactInfo;
import com.ehs.common.basicInfo.entity.SupplierInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:37:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
public class SupplierBean {

	/**
	 * 客户实体类
	 */
	private SupplierInfo supplier;
	
	/**
	 * 客户联系人
	 */
	private List<ContactInfo> contactInfos;

	public SupplierInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierInfo supplier) {
		this.supplier = supplier;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}
	
	
}
