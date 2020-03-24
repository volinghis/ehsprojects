package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckRepair;

@Repository
public interface EamCheckRepairDao extends JpaRepository<EamCheckRepair, String>{

	@Query(" select t from EamCheckRepair t where t."+BaseEntity.DELETED+"=0 and t.taskKey=:taskKey ")
	public List<EamCheckRepair> findByTaskKey(@Param("taskKey") String taskKey);
}
