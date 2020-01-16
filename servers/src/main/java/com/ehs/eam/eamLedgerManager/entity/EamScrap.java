/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:38:18 
 */
package com.ehs.eam.eamLedgerManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamScrap.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月7日 下午7:38:18 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月7日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_SCRAP",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamScrap extends com.ehs.eam.eamLedgerManager.entity.entitySuper.EamScrapSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
}
