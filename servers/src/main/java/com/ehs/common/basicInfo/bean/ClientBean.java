package com.ehs.common.basicInfo.bean;

import java.util.List;

import com.ehs.common.basicInfo.entity.ClientInfo;
import com.ehs.common.basicInfo.entity.ContactInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 下午4:55:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
public class ClientBean {

	/**
	 * 客户实体类
	 */
	private ClientInfo client;
	
	/**
	 * 客户联系人
	 */
	private List<ContactInfo> contactInfos;

	public ClientInfo getClient() {
		return client;
	}

	public void setClient(ClientInfo client) {
		this.client = client;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}
	
	
}
