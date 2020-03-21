/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.dao 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:11:30 
 */
package com.ehs.eam.eamLedgerManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午4:11:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Repository
public interface EamLedgerDao extends JpaRepository<EamLedger, String>  {
	
	@Query(" select el from EamLedger el where el. " + BaseEntity.DELETED + " = 0 " 
	        + " and ('ALL'=:name or el."+ EamLedger.DEVICE_NAME + " like %:name% ) " 
			+ " and ('ALL'=:address or el."+ EamLedger.INSTALL_LOCATION + "= :address) "
			+ " and ('ALL'=:profession or el."+ EamLedger.PROFESSION + " = :profession) "
			+ " and ('ALL'=:deviceSystem or el."+ EamLedger.DEVICE_SYSTEM + " = :deviceSystem) "
			+ " and ('ALL'=:time " 
			+ " or ('Y'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedger.RUN_DATE + "))< 365 )" 
			+ " or ('LTY'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedger.RUN_DATE + "))< 1095 )" 
			+ " or ('GTY'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedger.RUN_DATE + "))>= 1095 )" 
			+ " ) "
			+ "")
	public Page<EamLedger> findEamLedgerList(
			@Param("name") String name, 
			@Param("address") String address, 
			@Param("profession") String profession,
			@Param("deviceSystem") String deviceSystem,
			@Param("time") String time, 
			Pageable pageable);

	@Query(" select el from EamLedger el where el."+EamLedger.DEVICE_NAME+" like %?1% and el."+BaseEntity.DELETED+"= 0  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<EamLedger> findListSingleQuery(String query,Pageable pageable);
	
	@Query(" select el from EamLedger el where el."+BaseEntity.KEY+"=?1 and el."+BaseEntity.DELETED+" = 0  order by "+BaseEntity.BASE_SORT_NUM+" desc")
    public EamLedger findEamLedgerByKey(String key);
}
