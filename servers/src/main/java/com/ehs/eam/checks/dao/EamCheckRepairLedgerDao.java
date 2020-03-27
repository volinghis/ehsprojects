/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.dao 
 * @author: qjj   
 * @date: 2020年3月25日 下午2:12:27 
 */
package com.ehs.eam.checks.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckRepairLedger;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckRepairLedgerDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月25日 下午2:12:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月25日     qjj           v1.0.0               修改原因
*/
public interface EamCheckRepairLedgerDao extends JpaRepository<EamCheckRepairLedger, String>{

	@Query("select t from EamCheckRepairLedger t where t."+BaseEntity.DELETED+"=0 "
			+ " and ('ALL'=:address or t.deviceAddress= :address) "
			+ " and ('ALL'=:objectKey or t.objectKey = :objectKey) "
			+ " and ('ALL'=:objectType or t.objectType = :objectType) "
			+ " and ( 'ALL'=:result or t.result = :result)"
			+ " and ( 'ALL'=:userType "
			+ " or ('OWNER'=:userType and t.user = :userKey)"
			+ " or ('OUTER'=:userType and t.result = :userType)"
			+ " ) "
			+ "")
	public Page<EamCheckRepairLedger> findRepairLedgers(
			@Param("userKey") String userKey,
			@Param("address") String address,
			@Param("objectKey")String objectKey,
			@Param("objectType")String objectType,
			@Param("result") String result,
			@Param("userType") String userType,
			Pageable pageable);
}
