/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.dao 
 * @author: qjj   
 * @date: 2020年3月6日 上午11:58:09 
 */
package com.ehs.common.auth.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.auth.entity.SysLoginLog;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月6日 上午11:58:09 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     qjj           v1.0.0               修改原因
*/
@Repository
public interface LoginLogDao extends JpaRepository<SysLoginLog, String>{
	
	@Query(" select sl from SysLoginLog sl where (sl."+SysLoginLog.ACCOUNT+" like %?1% or sl."+SysLoginLog.NAME+" like %?1% ) and sl."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM)
	public Page<SysLoginLog> findAlLoginLogs(String query,DataModel[] dataModels,Pageable pageable);

}
