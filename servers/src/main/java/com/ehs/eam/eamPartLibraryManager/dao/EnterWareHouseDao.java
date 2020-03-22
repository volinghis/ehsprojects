package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouseDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月6日 上午10:10:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月6日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface EnterWareHouseDao extends JpaRepository<EnterWareHouse, String>{

//	@Query("select e from EnterWareHouse e left join PartsExtends p on p."+PartsExtends.WAREHOUSE_KEY+"=e."+BaseEntity.KEY+" left join FlowProcessInfo fi on e."+BaseEntity.KEY+"=fi."+FlowProcessInfo.BUSINESS_ENTITY_KEY
//			+ " where e."+BaseEntity.DELETED+" = 0 "
//			+ "and ( e."+EnterWareHouse.WAREHOUSE_CODE+" like %:query% "
//			+ "or e."+EnterWareHouse.INBOUND_TYPE_NAME+" like %:query% "
//			+ "or p."+PartsExtends.DEVICE_CODE+" like %:query% "
//			+ "or p."+PartsExtends.DEVICE_NAME+" like %:query% "
//			+ "or p."+PartsExtends.NORM+" like %:query% "
//			+ "or p."+PartsExtends.MANUFACTURER+" like %:query% "
//			+ "or p."+PartsExtends.MATERIAL_CODE+" like %:query% "
//			+ "or p."+PartsExtends.MATERIAL_TYPE+" like %:query% "
//			+ " ) "
//			+ " and ('ALL'=:wareHouseNames  or e."+EnterWareHouse.WAREHOUSE+" = :wareHouseNames)"
//			+ " and ('ALL'=:inBoundTypes or e."+EnterWareHouse.INBOUND_TYPE+" = :inBoundTypes)"
//			+ " and ('ALL'=:status "
//			+ " or ('APPROVL'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask2' ) "
//			+ " or ('END'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"=:status )"
//			+ " or ('REJECT'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask1' ) "
//			+ " or ('OVERDUE'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"<>:status and (TO_DAYS(current_date())-TO_DAYS(e."+ EnterWareHouse.CREATION_TIME + "))>7) "
//			+ " )"
//			+ "")
//	public Page<EnterWareHouse> findAllTask(@Param("query") String query, 
//									  @Param("wareHouseNames") String wareHouseNames, 
//									  @Param("inBoundTypes") String inBoundTypes, 
//									  @Param("status") String status,
//									  Pageable pb);


//	
//	@Query(" select ewh from EnterWareHouse ewh where ewh."+BaseEntity.DELETED+" = 0  order by "+BaseEntity.BASE_SORT_NUM+" desc")
//	Page<EnterWareHouse> findAll(PageRequest pageRequest);

}
