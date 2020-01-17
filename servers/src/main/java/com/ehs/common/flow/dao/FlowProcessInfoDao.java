package com.ehs.common.flow.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;

@Repository
public interface FlowProcessInfoDao extends JpaRepository<FlowProcessInfo, String>{

	public FlowProcessInfo findByFlowProcessInstanceId(String flowProcessInstanceId);
	
	@Query(" select u from FlowProcessInfo u where u."+BaseEntity.OWNER+"=?1 and  u."+BaseEntity.DATA_MODEL+" in ?2 order by "+BaseEntity.OWNER_CREATION_TIME+" desc ")
	public  Page<FlowProcessInfo> findInfos(String userKey,DataModel[] dataModels,Pageable pageable);
	
	@Query(" select u from FlowProcessInfo u where u."+BaseEntity.DATA_MODEL+" in ?1 and u."+FlowProcessInfo.FLOW_CURRENT_STEP+"=?2 and (u."+FlowProcessInfo.FLOW_SCORE+"=0 or u."+FlowProcessInfo.FLOW_SCORE+" is null) order by "+BaseEntity.OWNER_CREATION_TIME+" desc ")
	public  List<FlowProcessInfo> findInfos(DataModel[] dataModels,String flowStatus);

	@Query(" select u from FlowProcessInfo u where u."+FlowProcessInfo.BUSINESS_ENTITY_KEY+"=?1 and  u."+BaseEntity.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'")
	public  FlowProcessInfo findProcessInfoByEntityKey(String appKey);
}
