package com.ehs.common.flow.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;

@Repository
public interface FlowProcessInfoDao extends JpaRepository<FlowProcessInfo, String>{

	public FlowProcessInfo findByFlowProcessInstanceId(String flowProcessInstanceId);
	
	@Query(" select u from FlowProcessInfo u where u."+BaseEntity.OWNER+"=?1 and  u."+BaseEntity.DATA_MODEL+" in ?2 order by "+BaseEntity.OWNER_CREATION_TIME+" desc ")
	public  Page<FlowProcessInfo> findInfos(String userKey,DataModel[] dataModels,Pageable pageable);

}
