package com.ehs.common.basicInfo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.basicInfo.entity.entitysuper.SupplierInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierInfoHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:24:36 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "SUPPLIER_INFO_HIS")
public class SupplierInfoHis extends SupplierInfo{

	private static final long serialVersionUID = 1L;

}
