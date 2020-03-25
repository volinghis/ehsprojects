/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.entity 
 * @author: qjj   
 * @date: 2020年3月25日 上午9:58:44 
 */
package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.checks.entity.entitysuper.EamCheckRepairSuper;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckRepairLedger.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月25日 上午9:58:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月25日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_CHECK_REPAIR_LEDGER",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckRepairLedger extends EamCheckRepairSuper{

	private static final long serialVersionUID = 1L;

}
