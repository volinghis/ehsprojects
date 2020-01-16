package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsParam;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsParamHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月2日 下午5:45:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月2日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_PARTS_PARAM_HIS")
public class PartsParamHis extends PartsParam {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
