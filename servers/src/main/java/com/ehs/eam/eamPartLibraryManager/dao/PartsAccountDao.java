package com.ehs.eam.eamPartLibraryManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsAccountDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月22日 下午11:01:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月22日     zhaol          v1.0.0               修改原因
*/
@Repository
public interface PartsAccountDao extends JpaRepository<PartsAccount, String> {
	
	@Query("select p from PartsAccount p where p."+BaseEntity.DELETED+" =0 "
			+ "and ( p."+PartsAccount.WAREHOUSE_CODE+" like %:query% "
			+ "or p."+PartsAccount.DEVICE_CODE+" like %:query% "
			+ "or p."+PartsAccount.DEVICE_NAME+" like %:query% "
			+ "or p."+PartsAccount.NORM+" like %:query% "
			+ "or p."+PartsAccount.MANUFACTURER+" like %:query% "
			+ "or p."+PartsAccount.MATERIAL_CODE+" like %:query% "
			+ "or p."+PartsAccount.MATERIAL_TYPE+" like %:query% "
			+ " ) "
			+" and ('ALL'=:warehouseNames  or p."+PartsAccount.WAREHOUSE_NAME+" = :warehouseNames)"
			+" and ('ALL'=:reserve "
			+" or ('ENOUGH'=:reserve and p."+PartsAccount.AMOUNT+" >= 0 and p."+PartsAccount.WARNING_VALUE+" >=0 and p."+PartsAccount.AMOUNT+" > p."+PartsAccount.WARNING_VALUE+")"
			+" or ('NOENOUGH'=:reserve and p."+PartsAccount.AMOUNT+" >= 0 and p."+PartsAccount.WARNING_VALUE+" >=0 and p."+PartsAccount.AMOUNT+" <= p."+PartsAccount.WARNING_VALUE+")"
			+ " ) "
			+ "")
	public Page<PartsAccount> findAllAccount(@Param("query")String query,
											 @Param("warehouseNames") String warehouseNames,
											 @Param("reserve") String reserve,
											 Pageable pb);

	@Query(" select p from PartsAccount p where p."+PartsAccount.DEVICE_CODE+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsAccount> findByDeviceCode(String deviceCode);

	@Query("select p from PartsAccount p where p."+BaseEntity.DELETED+" =0 "
			+ "and (p."+PartsAccount.DEVICE_CODE+" like %:query% "
			+ "or p."+PartsAccount.DEVICE_NAME+" like %:query% "
			+ "or p."+PartsAccount.NORM+" like %:query% "
			+ " ) "
			+ "")
	public Page<PartsAccount> getAllPartsAccount(String query, Pageable pb);

}
