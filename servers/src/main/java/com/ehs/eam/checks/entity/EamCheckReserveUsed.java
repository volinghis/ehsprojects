package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.checks.entity.entitysuper.EamCheckReserveUsedSuper;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsed.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午11:11:03 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_CHECK_RESERVE_USED",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckReserveUsed extends EamCheckReserveUsedSuper {

	private static final long serialVersionUID = 1L;

}
