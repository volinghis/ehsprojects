package com.ehs.common.data.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.data.entity.DataDictionary;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月6日 下午3:39:11 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface DataDictionaryDao extends JpaRepository<DataDictionary, String> {

	@Query(" select d from DataDictionary d where d."+DataDictionary.PARENT_KEY+" = ?1 and d."+DataDictionary.DELETED+"=0 order by "+DataDictionary.SORT+" asc" )
	public List<DataDictionary> getFirstNode(String parentKey);

	@Query(" select d from DataDictionary d where d."+DataDictionary.PARENT_KEY+" =?1 and d."+DataDictionary.DELETED+"=0 order by "+DataDictionary.SORT+" asc" )
	public Page<DataDictionary> findDatasByParentCode(String parentKey, String query, PageRequest pageRequest);

	@Query(" select d from DataDictionary d where d."+DataDictionary.PARENT_KEY+" = ?1 and d."+BaseEntity.DELETED+" =0  order by "+DataDictionary.SORT+" asc")
	public List<DataDictionary> findDataDictByParentKey(String key);
	
	@Query(" select d from DataDictionary d where d."+DataDictionary.PARENT_KEY+" <> 'null' and d."+DataDictionary.DELETED+" =0  order by "+DataDictionary.SORT+" asc" )
	public Page<DataDictionary> findAllDatas(String query, PageRequest pageRequest);

}
