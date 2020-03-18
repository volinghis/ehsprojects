package com.ehs.eam.checks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.enums.FlowStatus;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.entity.EamCheckTask;

@Repository
public interface EamCheckTaskDao extends JpaRepository<EamCheckTask, String>{

//	@Query(" select t from EamCheckTask t left join EamCheckPlan eamCheckPlan on t."+EamCheckTask.PLAN_KEY+"=eamCheckPlan."+EamCheckPlan.KEY+" left join FlowProcessInfo flowProcessInfo on t.key=flowProcessInfo.businessEntityKey "
//			+ "where t."+BaseEntity.DELETED+" =0 "
//			+" and ((case when 'ALL'=:times then 1 else  0 end ) = 1"
//			+" or (case when 'TODAY'=:times then to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") else  0 end ) = to_days(current_date()) "
//			+" or (case when 'THREE'=:times then to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") else  0 end ) > (to_days(current_date())-3)  "
//			+" or (case when 'SEVEN'=:times then to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") else  0 end ) > (to_days(current_date())-7)  "
//			+ ") "
//			+" and ((case when 'ALL'=:owners then 1 else  0 end ) = 1"
//			+" or (case when 'EXECUTE'=:owners then :userKey else  0 end ) = t."+EamCheckTask.USER
//			+" or ((case when 'APPROVE'=:owners then :userKey else  t."+EamCheckTask.USER+" end ) <> t."+EamCheckTask.USER
//			+" and  (case when 'APPROVE'=:owners then LOCATE(:userKey,t."+FlowProcessInfo.FLOW_PERSONS+") else  0 end )>0 )"
//			+ ") "
//			+" and ((case when 'ALL'=:checks then 1 else  0 end ) = 1"
//			+" or (case when 'INCLUDE'=:checks then t."+EamCheckTask.REPAIRS+" else  0 end ) = 1"
//			+" or (case when 'EXCLUDE'=:checks then  t."+EamCheckTask.REPAIRS+" else  1 end ) = 0"
//			+ ") "
//			+" and ((case when 'ALL'=:defects then 1 else  0 end ) = 1"
//			+" or (case when 'INCLUDE'=:defects then t."+EamCheckTask.DEFECTS+" else  0 end ) = 1"
//			+" or (case when 'EXCLUDE'=:defects then  t."+EamCheckTask.DEFECTS+" else  1 end ) = 0"
//			+ ") "
//			+" and ((case when 'ALL'=:revers then 1 else  0 end ) = 1"
//			+" or (case when 'INCLUDE'=:revers then t."+EamCheckTask.RESERVES+" else  0 end ) = 1"
//			+" or (case when 'EXCLUDE'=:revers then  t."+EamCheckTask.RESERVES+" else  1 end ) = 0"
//			+ ") "
//			+" and ( 'ALL'=:flowstatus "
//			+" or ('DRAFT'=:flowstatus and (isnull(flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+") )) "
//			+" or (case when 'EXCLUDE'=:revers then  t."+EamCheckTask.RESERVES+" else  1 end ) = 0"
//			+ ") "
//			
//			
//			
//			+ "and (eamCheckPlan."+BaseEntity.DELETED+" =0 or eamCheckPlan."+BaseEntity.DELETED+" is null )"
//			+ "and (flowProcessInfo."+BaseEntity.DELETED+" =0 or flowProcessInfo."+BaseEntity.DELETED+" is null )"
//			+" and ( (flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+"  is null and t."+EamCheckTask.USER+"=:userKey) or LOCATE(:userKey,flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+")>0) "
//			+ "")
//	public Page<EamCheckTask> findAllPlan(
//			@Param("userKey") String userKey,
//			@Param("times") String times,
//			@Param("owners")  String owners,
//			@Param("checks") String checks,
//			@Param("defects") String defects,
//			@Param("revers") String revers,
//			@Param("flowstatus") String flowstatus,
//			Pageable pageable);
}
