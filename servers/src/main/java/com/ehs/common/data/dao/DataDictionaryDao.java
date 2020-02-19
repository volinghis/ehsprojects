package com.ehs.common.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.data.entity.DataDictionary;

@Repository
public interface DataDictionaryDao extends JpaRepository<DataDictionary, String>{

	@Query(" select d from DataDictionary d where d."+DataDictionary.PARENT_KEY+" = ?1 and d."+BaseEntity.DATA_MODEL+" in ?2  order by "+BaseEntity.BASE_SORT_NUM)
	public List<DataDictionary> findDataDictByParentKey(String key,DataModel[] dataModels);
}
