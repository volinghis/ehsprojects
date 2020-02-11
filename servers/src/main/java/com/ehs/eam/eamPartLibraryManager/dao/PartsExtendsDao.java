package com.ehs.eam.eamPartLibraryManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

@Repository
public interface PartsExtendsDao extends JpaRepository<PartsExtends, String> {

	@Query(" select pe from PartsExtends pe where pe."+PartsExtends.DEVICE_CODE+"=?1 and pe."+EnterWareHouse.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public PartsExtends findByDeviceCode(String deviceCode);

	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+PartsExtends.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsExtends> getExtendsByKey(String key, PageRequest pageRequest);
	
	@Query(" select p from PartsExtends p where p."+PartsExtends.WAREHOUSE_KEY+"=?1 and p."+PartsExtends.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsExtends> getAllByWareHouseKey(String wareHouseKey);
}
