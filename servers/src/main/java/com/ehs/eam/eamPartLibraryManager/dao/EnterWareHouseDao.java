package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsExtends;

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

//	@Query("select * FROM EnterWareHouse e left join PartsExtends p on p."+PartsExtends.WAREHOUSE_KEY+"=e."+EnterWareHouse.KEY
//			+ " where e."+BaseEntity.DELETED+" =0 "
//			+ "and e."+EnterWareHouse.WAREHOUSE_CODE+" = like %?1%"
//			+ "and e."+EnterWareHouse.INBOUND_TYPE_NAME+" = like %?1%"
//			+ "and e."+PartsExtends.DEVICE_CODE+" = like %?1%"
//			+ "and e."+PartsExtends.DEVICE_NAME+" = like %?1%"
//			+ "and e."+PartsExtends.NORM+" = like %?1%"
//			+ "and e."+PartsExtends.MANUFACTURER+" = like %?1%"
//			+ "and e."+PartsExtends.MATERIAL_CODE+" = like %?1%"
//			+ "and e."+PartsExtends.MATERIAL_TYPE+" = like %?1%"
//			+" and ( 'ALL'=:warehouses or e."+EnterWareHouse.WAREHOUSE+" = :warehouses)"
//			+" and ('ALL'=:inBoundTypes or e."+EnterWareHouse.INBOUND_TYPE+" = :inBoundTypes)"
//
//			+" and ( 'ALL'=:status "
//			+" or ('userTask1'=:status and e."+EnterWareHouse.STATUS+" = 'userTask1')"
//			+" or ('userTask2'=:status and e."+EnterWareHouse.STATUS+" = 'userTask2')"
//			+" or ('END'=:status and e."+EnterWareHouse.STATUS+" = 'END')"
//			+ " ) "
//			+ "")
//	public Page<EnterWareHouse> findAll(String query, @Param("warehouses") String warehouse, 
//								 @Param("inBoundTypes") String inBoundType, 
//								 @Param("status") String status, 
//								 Pageable pb);


	
	@Query(" select ewh from EnterWareHouse ewh where ewh."+BaseEntity.DELETED+" = 0  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	Page<EnterWareHouse> findAll(PageRequest pageRequest);

}
