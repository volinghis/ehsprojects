/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 下午4:22:40 
 */
package com.ehs.eam.eamDataBase.entity.entitysuper;

import javax.persistence.MappedSuperclass;
import com.ehs.common.base.entity.BaseEntity;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: FileInfo.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年6月26日 下午4:22:40
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年6月26日
 * chentm v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class DataFileInfoCopy extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "name";
	public static final String FILE_ID = "fileId";
	public static final String CATEGORIES = "categories";

	private String name;

	private String fileId;
	
	private String categories;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}
	
}
