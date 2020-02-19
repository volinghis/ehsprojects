/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamDataBase.bean 
 * @author: Administrator   
 * @date: 2020年2月19日 下午3:57:04 
 */
package com.ehs.eam.eamDataBase.bean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamDataReqBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2020年2月19日 下午3:57:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月19日     Administrator           v1.0.0               修改原因
*/
public class EamDataReqBean {
	
	private String fileId;
	
	private String entityKey;
	
	private String category;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
