/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamPartLibraryManager.entity 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:47:29 
 */
package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibrary.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午3:47:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_PARTS_ACCOUNT",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class PartsAccount extends com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsAccount {

	private static final long serialVersionUID = 1L;

}