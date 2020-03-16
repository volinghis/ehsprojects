package com.ehs.eam.eamPartLibraryManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

@Repository
public interface PartsExtendsDao extends JpaRepository<PartsExtends, String> {

	@Query(" select pe from PartsExtends pe where pe."+PartsExtends.DEVICE_CODE+"=?1 and pe."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public PartsExtends findByDeviceCode(String deviceCode,DataModel[] dataModels);

	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getExtendsByKey(String key,DataModel[] dataModels,PageRequest pageRequest);
	
	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getAllByWareHouseKey(String wareHouseKey,DataModel[] dataModels);

	@Query(" select p from PartsExtends p where p."+BaseEntity.DATA_MODEL+" in ?1  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getAll(DataModel[] dataModels,PageRequest pageRequest);

	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getExtendsByKey(String key,DataModel[] dataModels);
}
