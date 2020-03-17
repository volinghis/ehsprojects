package com.ehs.eam.checks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.entity.EamCheckTask;

@Repository
public interface EamCheckTaskDao extends JpaRepository<EamCheckTask, String>{

	@Query(" select t from EamCheckTask t left join FlowProcessInfo fpi on t.key=fpi.businessEntityKey where t."+BaseEntity.DATA_MODEL+" in ?1 "
			+" and ( (fpi."+FlowProcessInfo.FLOW_PERSONS+"  is null and t."+EamCheckTask.USER+"=?2) or LOCATE(?2,fpi."+FlowProcessInfo.FLOW_PERSONS+")>0) "
			+ "")
	public Page<EamCheckTask> findAllPlan(@Param("dataModels")  DataModel[] dataModels,
			String userKey,
			Pageable pageable);
}
