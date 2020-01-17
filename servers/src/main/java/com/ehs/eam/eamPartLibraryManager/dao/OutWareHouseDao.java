package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;

@Repository
public interface OutWareHouseDao  extends JpaRepository<OutWareHouse, String> {

	@Query(" select ewh from OutWareHouse ewh where ewh."+OutWareHouse.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<OutWareHouse> findAll(PageRequest pageRequest);

}
