package com.ehs.common.basicInfo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.basicInfo.entity.entitysuper.ContactInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ContactInfoHis.java
* @Description: 联系人历史类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 上午11:06:18 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "CONTACT_INFO_HIS")
public class ContactInfoHis extends ContactInfo{

	private static final long serialVersionUID = 1L;

}
