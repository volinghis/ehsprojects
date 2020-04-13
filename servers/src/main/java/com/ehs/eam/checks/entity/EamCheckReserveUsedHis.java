package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsedHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午11:11:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_CHECK_RESERVE_USED_HIS")
public class EamCheckReserveUsedHis extends EamCheckReserveUsed {

	private static final long serialVersionUID = 1L;

}
