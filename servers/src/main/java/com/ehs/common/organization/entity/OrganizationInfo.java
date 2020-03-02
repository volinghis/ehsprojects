/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:52:10 
 */
package com.ehs.common.organization.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:52:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name = "ORGANIZATION_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class OrganizationInfo extends com.ehs.common.organization.entity.entitysuper.OrganizationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
}
