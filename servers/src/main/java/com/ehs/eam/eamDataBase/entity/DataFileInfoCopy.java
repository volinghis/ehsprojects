/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamDataBase.entity 
 * @author: Administrator   
 * @date: 2020年2月20日 下午5:10:07 
 */
package com.ehs.eam.eamDataBase.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataFileInfoCopy.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2020年2月20日 下午5:10:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月20日     Administrator           v1.0.0               修改原因
*/
@Entity
@Table(name="DATA_FILE_INFO_COPY",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class DataFileInfoCopy extends com.ehs.eam.eamDataBase.entity.entitysuper.DataFileInfoCopy{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
