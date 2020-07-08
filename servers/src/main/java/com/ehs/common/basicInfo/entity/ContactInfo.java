package com.ehs.common.basicInfo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ContactInfo.java
* @Description: 联系人信息类 
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 上午11:05:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "CONTACT_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class ContactInfo extends com.ehs.common.basicInfo.entity.entitysuper.ContactInfo{

	private static final long serialVersionUID = 1L;

}
