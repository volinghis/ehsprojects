package com.ehs.eam.checks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.utils.FlowConstans;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.entity.EamCheckTask;

@Repository
public interface EamCheckTaskDao extends JpaRepository<EamCheckTask, String>{

	@Query(" select t from EamCheckTask t left join EamCheckPlan eamCheckPlan on t."+EamCheckTask.PLAN_KEY+"=eamCheckPlan."+BaseEntity.KEY+" left join FlowProcessInfo flowProcessInfo on t."+BaseEntity.KEY+"=flowProcessInfo."+FlowProcessInfo.BUSINESS_ENTITY_KEY
			+" where t."+BaseEntity.DELETED+" =0 "
			+" and ( 'ALL'=:times "
			+" or ( 'TODAY'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") = to_days(current_date())) "
			+" or ( 'THREE'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") > (to_days(current_date())-3))  "
			+" or ( 'SEVEN'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") > (to_days(current_date())-7))  "
			+ " ) "
			+" and ( 'ALL'=:owners and (:userKey  = t."+EamCheckTask.USER+" or LOCATE(:userKey,flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+")>0 )"
			+" or ( 'EXECUTE'=:owners and :userKey  = t."+EamCheckTask.USER+") "
			+" or ( 'APPROVE'=:owners and :userKey <>  t."+EamCheckTask.USER+" and LOCATE(:userKey,flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+")>0 )"
			+ " ) "

			+" and ( 'ALL'=:checks "
			+" or ('INCLUDE'=:checks and t."+EamCheckTask.REPAIRS+" = 1)"
			+" or ('EXCLUDE'=:checks and t."+EamCheckTask.REPAIRS+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:defects "
			+" or ('INCLUDE'=:defects and t."+EamCheckTask.DEFECTS+" = 1)"
			+" or ('EXCLUDE'=:defects and t."+EamCheckTask.DEFECTS+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:revers "
			+" or ('INCLUDE'=:revers and t."+EamCheckTask.RESERVES+" = 1)"
			+" or ('EXCLUDE'=:revers and t."+EamCheckTask.RESERVES+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:executeResult  or t."+EamCheckTask.RESULT+" = :executeResult ) "
			+" and ( 'ALL'=:flowstatus "
			+" or ( 'DRAFT'=:flowstatus and (( ISNULL(flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+")=1 OR LENGTH(TRIM(flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+")) <=0) or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_DRAFT+"' )  ) "
			+" or ('END'=:flowstatus and (flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_CANCELED+"' or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_END+"')  ) "
			+" or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+" = :flowstatus "
			+ " ) "
			+ " and (eamCheckPlan."+BaseEntity.DELETED+" =0 or eamCheckPlan."+BaseEntity.DELETED+" is null )"
			+ " and (flowProcessInfo."+BaseEntity.DELETED+" =0 or flowProcessInfo."+BaseEntity.DELETED+" is null )"
			+ "")
	public Page<EamCheckTask> findAllPlan(
			@Param("userKey") String userKey,
			@Param("times") String times,
			@Param("owners")  String owners,
			@Param("checks") String checks,
			@Param("defects") String defects,
			@Param("revers") String revers,
			@Param("flowstatus") String flowstatus,
			@Param("executeResult") String executeResult,
			Pageable pageable);
}
