package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.eam.checks.entity.EamCheckTask;

@Repository
public interface EamCheckPlanDao  extends JpaRepository<EamCheckPlan, String>{


	
	@Query(" select t from EamCheckPlan t where t."+BaseEntity.DELETED+" =0 "
			+" and ( 'ALL'=:rates or   t."+EamCheckPlan.RATE+"  = :rates)"
			+" and ('ALL'=:types "
			+" or ('OWNER'=:types and t."+BaseEntity.OWNER+"  = :userKey )"
			+" or ('NEEDEXECUTE'=:types and LOCATE(:orgKey,t."+EamCheckPlan.CHECKOR+") >0) "
			+ " ) "

			+" and ( 'ALL'=:status "
			+" or ('ENABLE'=:status and t."+EamCheckPlan.ENABLE+" = 1)"
			+" or ('DISABLE'=:status and t."+EamCheckPlan.ENABLE+" = 0)"
			+ " ) "
			+" and ('ALL'=:executes "
			+" or ('EFFECTIVE'=:executes and to_days(t."+EamCheckPlan.START_TIME+")  <= to_days(current_date()) "
			+" and to_days(t."+EamCheckPlan.END_TIME+")  >= to_days(current_date()) )"
			+" or ('INVALID'=:executes and to_days(t."+EamCheckPlan.END_TIME+")  < to_days(current_date())) "
			+" or ('EVERSTART'=:executes and to_days(t."+EamCheckPlan.START_TIME+")  > to_days(current_date())) "
			+ ") "
			+ "")
	public Page<EamCheckPlan> findAllPlan(
			@Param("rates") String rates,
			@Param("types") String types,
			@Param("status") String status,
			@Param("executes") String executes,
			@Param("userKey") String userKey,
			@Param("orgKey") String orgKey,
			Pageable pageable);
	
	@Query(" select t from EamCheckPlan t where t."+BaseEntity.DELETED+" =0 "
			+" and t."+EamCheckPlan.ENABLE+" = 1"
			+" and to_days(t."+EamCheckPlan.START_TIME+")  <= to_days(current_date()) "
			+" and to_days(t."+EamCheckPlan.END_TIME+")  >= to_days(current_date()) "
			+ "")
	public List<EamCheckPlan> findAllPlanOfEnable();
}
