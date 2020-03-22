package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;

@Repository
public interface OutWareHouseDao  extends JpaRepository<OutWareHouse, String> {

//	@Query(" select ewh from OutWareHouse ewh where ewh."+OutWareHouse.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
//	public Page<OutWareHouse> findAll(PageRequest pageRequest);

}
