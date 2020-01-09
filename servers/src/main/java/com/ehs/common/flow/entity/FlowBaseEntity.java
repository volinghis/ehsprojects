/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.entity 
 * @author: chentm   
 * @date: 2019年7月8日 下午4:33:00 
 */
package com.ehs.common.flow.entity;


import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseEntity.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月8日 下午4:33:00 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月8日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class FlowBaseEntity extends BaseEntity {
	
	
	private static final long serialVersionUID = 1L;
	
	public abstract String getFlow();
	
	public abstract String getEditPage();
	
	public abstract String getViewPage();

	public static final String FLOW_PROCESS_INFO_KEY = "flowProcessInfoKey" ;
	
	private String flowProcessInfoKey;

	@Transient
	private String flowProcessKey;

	@Transient
	private String flowBusinessEditPage;
	
	@Transient
	private String flowBusinessViewPage;


	

	public String getFlowBusinessEditPage() {
		return getEditPage();
	}


	public String getFlowBusinessViewPage() {
		return getViewPage();
	}


	public String getFlowProcessKey() {
		return getFlow();
	}


	public String getFlowProcessInfoKey() {
		return flowProcessInfoKey;
	}

	public void setFlowProcessInfoKey(String flowProcessInfoKey) {
		this.flowProcessInfoKey = flowProcessInfoKey;
	}

	
	
	
	
	



	
	
	
}
