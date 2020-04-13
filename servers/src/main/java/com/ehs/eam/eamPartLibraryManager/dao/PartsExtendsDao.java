package com.ehs.eam.eamPartLibraryManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtendsDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午9:54:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface PartsExtendsDao extends JpaRepository<PartsExtends, String> {

	/**
	 * 
	* @Function: PartsExtendsDao.java
	* @Description: 根据入库流程key查询所有 备件信息并分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:54:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select p from PartsExtends p where p."+PartsExtends.REF_FLOW_KEY+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getExtendsByKey(String key,PageRequest pageRequest);
	
	/**
	 * 
	* @Function: PartsExtendsDao.java
	* @Description: 根据入库流程key查询所有 备件信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:55:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select p from PartsExtends p where p."+PartsExtends.REF_FLOW_KEY+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getAllByWareHouseKey(String wareHouseKey);
	
	/**
	 * 
	* @Function: PartsExtendsDao.java
	* @Description: 查询所有入库备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:55:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query("select p from EnterWareHouse e left join PartsExtends p on p."+PartsExtends.REF_FLOW_KEY+"=e."+BaseEntity.KEY+" left join FlowProcessInfo fi on e."+BaseEntity.KEY+"=fi."+FlowProcessInfo.BUSINESS_ENTITY_KEY
			+ " where e."+BaseEntity.DELETED+" = 0 "
			+ " and ( e."+EnterWareHouse.WAREHOUSE_CODE+" like %:query% "
			+ " or p."+PartsExtends.DEVICE_CODE+" like %:query% "
			+ " or p."+PartsExtends.DEVICE_NAME+" like %:query% "
			+ " or p."+PartsExtends.NORM+" like %:query% "
			+ " or p."+PartsExtends.MANUFACTURER+" like %:query% "
			+ " or p."+PartsExtends.MATERIAL_CODE+" like %:query% "
			+ " or p."+PartsExtends.MATERIAL_TYPE+" like %:query% "
			+ " ) "
			+ " and ( 'ALL'=:warehouses or e."+EnterWareHouse.WAREHOUSE_NAME+" = :warehouses)"
			+ " and ( 'ALL'=:inBoundTypes or e."+EnterWareHouse.INBOUND_TYPE_NAME+" = :inBoundTypes)"
			+ " and ('ALL'=:flowstatus "
			+ " or('APPROVL'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask2' ) "
			+ " or('END'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"=:flowstatus )"
			+ " or('REJECT'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask1' ) "
			+ " or('OVERDUE'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"<> 'END' and (TO_DAYS(current_date())-TO_DAYS(e."+ EnterWareHouse.CREATION_TIME + "))>7)"
			+ " )"
			+ "")
	public Page<PartsExtends> getAllEnterWareHouseParts(@Param("query") String query, 
									  @Param("warehouses") String warehouses, 
									  @Param("inBoundTypes") String inBoundTypes, 
									  @Param("flowstatus") String flowstatus,
									  Pageable pb);

	
	/**
	 * 
	* @Function: PartsExtendsDao.java
	* @Description: 查询所有出库备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:56:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query("select p from OutWareHouse o left join PartsExtends p on p."+PartsExtends.REF_FLOW_KEY+"=o."+BaseEntity.KEY+" left join FlowProcessInfo fi on o."+BaseEntity.KEY+"=fi."+FlowProcessInfo.BUSINESS_ENTITY_KEY
			+ " where o."+BaseEntity.DELETED+" = 0 "
			+ " and ( o."+OutWareHouse.OUT_WAREHOUSE_CODE+" like %:query% "
			+ " or p."+PartsExtends.DEVICE_CODE+" like %:query% "
			+ " or p."+PartsExtends.DEVICE_NAME+" like %:query% "
			+ " or p."+PartsExtends.NORM+" like %:query% "
			+ " or p."+PartsExtends.MANUFACTURER+" like %:query% "
			+ " or p."+PartsExtends.MATERIAL_CODE+" like %:query% "
			+ " or p."+PartsExtends.MATERIAL_TYPE+" like %:query% "
			+ " ) "
			+ " and ( 'ALL'=:warehouseNames or o."+OutWareHouse.OUT_WAREHOUSE_NAME+" = :warehouseNames)"
			+ " and ( 'ALL'=:outBoundTypes or o."+OutWareHouse.OUTBOUND_TYPE_NAME+" = :outBoundTypes)"
			+ " and ('ALL'=:flowstatus "
			+ " or('APPROVL'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask2' ) "
			+ " or('END'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"=:flowstatus )"
			+ " or('REJECT'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask1' ) "
			+ " or('OVERDUE'=:flowstatus and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"<> 'END' and (TO_DAYS(current_date())-TO_DAYS(o."+ OutWareHouse.CREATION_TIME + "))>7)"
			+ " )"
			+ "")
	public Page<PartsExtends> getAllOutWareHouseParts(@Param("query") String query, 
									  @Param("warehouseNames") String warehouseNames, 
									  @Param("outBoundTypes") String outBoundTypes, 
									  @Param("flowstatus") String flowstatus,
									  Pageable pb);

}
