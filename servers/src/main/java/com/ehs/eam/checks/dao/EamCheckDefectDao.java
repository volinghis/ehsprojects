package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckDefect;

@Repository
public interface EamCheckDefectDao extends JpaRepository<EamCheckDefect, String>{

	@Query(" select t from EamCheckDefect t where t."+BaseEntity.DELETED+"=0 and t.taskKey=:taskKey ")
	public List<EamCheckDefect> findByTaskKey(@Param("taskKey") String taskKey);
}
