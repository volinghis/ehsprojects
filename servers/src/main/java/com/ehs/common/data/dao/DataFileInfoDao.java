package com.ehs.common.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.data.entity.DataFileInfo;

@Repository
public interface DataFileInfoDao extends JpaRepository<DataFileInfo, String>{


	@Query(" select d from DataFileInfo d where d."+DataFileInfo.FILE_ID+" in ?1 and d."+BaseEntity.DELETED+" =0  order by "+BaseEntity.BASE_SORT_NUM)
	public List<DataFileInfo> find(String[] fileIds);

}
