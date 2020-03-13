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

	@Query(" select t from EamCheckPlan t where t."+BaseEntity.DATA_MODEL+" in ?1 "
			+" and t."+EamCheckPlan.NAME +" like %?2% "
			+ " and (t."+EamCheckPlan.VIEW_TYPE+"='ALL' "
			+ "or t."+BaseEntity.OWNER+"= ?3"
			+ "or (t."+EamCheckPlan.VIEW_TYPE+"='ORG' and LOCATE(?4,t."+EamCheckPlan.CHECKOR+")>0 ) "
			+ " )  "
			+" and (case when true=?5 then t."+BaseEntity.OWNER+" else  ?3 end ) = ?3 "
			+" and (case when true=?6 then to_days(t."+EamCheckPlan.START_TIME+") else  (to_days(current_date())-1) end ) <= to_days(current_date()) "
			+" and (case when true=?6 then to_days(t."+EamCheckPlan.END_TIME+") else  (to_days(current_date())+1) end ) >= to_days(current_date()) "
			+" and (case when true=?7 then t."+EamCheckPlan.ENABLE+" else  1 end ) = 1 "
			+ "")
	public Page<EamCheckPlan> findAllPlan(@Param("dataModels")  DataModel[] dataModels,
			@Param("query") String query,
			@Param("userKey") String userKey,
			@Param("orgKey") String orgKey,
			@Param("byowner") boolean byowner,
			@Param("effective") boolean effective,
			@Param("enable") boolean enable,
			Pageable pageable);
}
