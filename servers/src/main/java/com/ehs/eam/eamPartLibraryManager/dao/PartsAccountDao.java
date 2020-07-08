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
	
	/**
	 * 
	* @Function: PartsAccountDao.java
	* @Description: 根据不同条件查询备件台账
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:51:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
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
			+" and "+PartsAccount.WAREHOUSE_CODE+" is not null"
			+ "")
	public Page<PartsAccount> findAllAccount(@Param("query")String query,
											 @Param("warehouseNames") String warehouseNames,
											 @Param("reserve") String reserve,
											 Pageable pb);

	/**
	 * 
	* @Function: PartsAccountDao.java
	* @Description: 根据备件编号查询所有台账
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:52:26 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select p from PartsAccount p where p."+PartsAccount.DEVICE_CODE+"=?1 and p."+BaseEntity.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsAccount> findByDeviceCode(String deviceCode);

	/**
	 * 
	* @Function: PartsAccountDao.java
	* @Description: 根据不同条件查询备件台账
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:53:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query("select p from PartsAccount p where p."+BaseEntity.DELETED+" =0 "
			+ "and (p."+PartsAccount.DEVICE_CODE+" like %:query% "
			+ "or p."+PartsAccount.DEVICE_NAME+" like %:query% "
			+ "or p."+PartsAccount.NORM+" like %:query% "
			+ " ) "
			+ " and p."+PartsAccount.WAREHOUSE+" like %:flag% "
			+ "")
	public Page<PartsAccount> getAllPartsAccount(String query, String flag,Pageable pb);
	
	/**
	 * 
	* @Function: PartsAccountDao.java
	* @Description: 根据所在仓库和备件编号查询所有台账
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午9:53:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query("select p from PartsAccount p where p."+PartsAccount.WAREHOUSE+" = ?1"
			+ " and p."+PartsAccount.DEVICE_CODE+" = ?2 "
			+ " and p."+BaseEntity.DELETED+" =0"
			+ "")
	public PartsAccount getAccountBywareHouseAndDeviceCode(String wareHouse,String deviceCode);

}
