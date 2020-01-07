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
import com.ehs.common.flow.enums.FlowStatus;


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
	
	public abstract String getPage();

	public static final String FLOW_PROCESS_INFO_KEY = "flowProcessInfoKey" ;
	
	private String flowProcessInfoKey;

	private String flowProcessKey;
	
	private String flowBusinessPage;
	
	

	public String getFlowBusinessPage() {
		if(StringUtils.isBlank(flowBusinessPage)) {
			return getPage();
		}
		return flowBusinessPage;
	}

	public void setFlowBusinessPage(String flowBusinessPage) {
		this.flowBusinessPage = flowBusinessPage;
	}

	public String getFlowProcessKey() {
		if(StringUtils.isBlank(flowProcessKey)) {
			return getFlow();
		}
		return flowProcessKey;
	}

	public void setFlowProcessKey(String flowProcessKey) {
		this.flowProcessKey = flowProcessKey;
	}

	public String getFlowProcessInfoKey() {
		return flowProcessInfoKey;
	}

	public void setFlowProcessInfoKey(String flowProcessInfoKey) {
		this.flowProcessInfoKey = flowProcessInfoKey;
	}

	
	
	
	
	



	
	
	
}
