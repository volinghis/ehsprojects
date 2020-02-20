/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamDataBase.dao 
 * @author: Administrator   
 * @date: 2020年2月20日 下午5:25:22 
 */
package com.ehs.eam.eamDataBase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ehs.eam.eamDataBase.entity.DataFileInfoCopy;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataFileInfoCopyDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2020年2月20日 下午5:25:22 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月20日     Administrator           v1.0.0               修改原因
*/
public interface DataFileInfoCopyDao extends JpaRepository<DataFileInfoCopy, String>,JpaSpecificationExecutor<DataFileInfoCopy> {

}
