package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import javax.persistence.MappedSuperclass;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsAccount.java
* @Description: 备件台账实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午8:48:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class PartsAccount extends PartsExtends {

	private static final long serialVersionUID = 1L;
	
	public static final String DUMMY_AMOUNT = "dummyAmount";

	private Integer dummyAmount;

	public Integer getDummyAmount() {
		return dummyAmount;
	}

	public void setDummyAmount(Integer dummyAmount) {
		this.dummyAmount = dummyAmount;
	}

}
