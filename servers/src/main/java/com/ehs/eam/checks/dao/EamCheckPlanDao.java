package com.ehs.eam.checks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckPlan;

@Repository
public interface EamCheckPlanDao  extends JpaRepository<EamCheckPlan, String>{


	
	@Query(" select t from EamCheckPlan t where t."+BaseEntity.DATA_MODEL+" in :dataModels "
			+" and (case when 'ALL'=:rates then :rates else  t."+EamCheckPlan.RATE+" end ) = :rates"
			+" and ((case when 'ALL'=:types then 1 else  0 end ) = 1"
			+" or (case when 'OWNER'=:types then t."+BaseEntity.OWNER+" else  1 end ) = :userKey "
			+" or (case when 'NEEDEXECUTE'=:types then LOCATE(:orgKey,t."+EamCheckPlan.CHECKOR+") else  0 end ) >0 "
			+ " ) "
			+" and ((case when 'ALL'=:status then 1 else  0 end ) = 1"
			+" or (case when 'ENABLE'=:status then t."+EamCheckPlan.ENABLE+" else  0 end ) = 1"
			+" or (case when 'DISABLE'=:status then  t."+EamCheckPlan.ENABLE+" else  1 end ) = 0"
			+ ") "
			+" and ((case when 'ALL'=:executes then 1 else  0 end ) = 1"
			+" or ((case when 'EFFECTIVE'=:executes then to_days(t."+EamCheckPlan.START_TIME+") else  (to_days(current_date())+1) end ) <= to_days(current_date()) "
			+" and (case when 'EFFECTIVE'=:executes then to_days(t."+EamCheckPlan.END_TIME+") else  (to_days(current_date())-1) end ) >= to_days(current_date()) )"
			+" or (case when 'INVALID'=:executes then to_days(t."+EamCheckPlan.END_TIME+") else  (to_days(current_date())+1) end ) < to_days(current_date()) "
			+" or (case when 'EVERSTART'=:executes then to_days(t."+EamCheckPlan.START_TIME+") else  (to_days(current_date())-1) end ) > to_days(current_date()) "
			+ ") "
			+ "")
	public Page<EamCheckPlan> findAllPlan(@Param("dataModels")  DataModel[] dataModels,
			@Param("rates") String rates,
			@Param("types") String types,
			@Param("status") String status,
			@Param("executes") String executes,
			@Param("userKey") String userKey,
			@Param("orgKey") String orgKey,
			Pageable pageable);
}
