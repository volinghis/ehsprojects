/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity 
 * @author: qjj   
 * @date: 2020年1月10日 下午4:14:48 
 */
package com.ehs.eam.eamLedgerManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamLedgerManager.entity.entitySuper.EamAllocateSuper;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamAllocateHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月10日 下午4:14:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月10日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_ALLOCATE_HIS")
public class EamAllocateHis extends EamAllocateSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
