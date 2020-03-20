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
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

@Repository
public interface PartsExtendsDao extends JpaRepository<PartsExtends, String> {

	@Query(" select pe from PartsExtends pe where pe."+PartsExtends.DEVICE_CODE+"=?1 and pe."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public PartsExtends findByDeviceCode(String deviceCode);

	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getExtendsByKey(String key,PageRequest pageRequest);
	
	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getAllByWareHouseKey(String wareHouseKey);

	@Query(" select p from PartsExtends p where p."+BaseEntity.DELETED+" = 0  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getAll(PageRequest pageRequest);

	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getExtendsByKey(String key);
	
	
	
	
	@Query("select p from EnterWareHouse e left join PartsExtends p on p."+PartsExtends.WAREHOUSE_KEY+"=e."+EnterWareHouse.KEY
			+ " where e."+BaseEntity.DELETED+" = 0 "
			+ "and ( e."+EnterWareHouse.WAREHOUSE_CODE+" like %:query% "
			+ "or e."+EnterWareHouse.INBOUND_TYPE_NAME+" like %:query% "
			+ "or p."+PartsExtends.DEVICE_CODE+" like %:query% "
			+ "or p."+PartsExtends.DEVICE_NAME+" like %:query% "
			+ "or p."+PartsExtends.NORM+" like %:query% "
			+ "or p."+PartsExtends.MANUFACTURER+" like %:query% "
			+ "or p."+PartsExtends.MATERIAL_CODE+" like %:query% "
			+ "or p."+PartsExtends.MATERIAL_TYPE+" like %:query% "
			+ " ) "
			+" and ( 'ALL'=:warehouses or e."+EnterWareHouse.WAREHOUSE+" = :warehouses)"
			+" and ( 'ALL'=:inBoundTypes or e."+EnterWareHouse.INBOUND_TYPE+" = :inBoundTypes)"
			+" and ( 'ALL'=:statusAll "
			+" or ('userTask1'=:statusAll and e."+EnterWareHouse.STATUS+" = 'userTask1')"
			+" or ('userTask2'=:statusAll and e."+EnterWareHouse.STATUS+" = 'userTask2')"
			+" or ('END'=:statusAll and e."+EnterWareHouse.STATUS+" = 'END')"
			+ " ) "
			+ "")
	public Page<PartsExtends> findAll(@Param("query") String query, 
									  @Param("warehouses") String warehouse, 
									  @Param("inBoundTypes") String inBoundType, 
									  @Param("statusAll") String statusAll,
									  Pageable pb);

}
