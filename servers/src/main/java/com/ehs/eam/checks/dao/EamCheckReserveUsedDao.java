package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;

@Repository
public interface EamCheckReserveUsedDao extends JpaRepository<EamCheckReserveUsed, String> {

	@Query(" select r from EamCheckReserveUsed r where r."+BaseEntity.DELETED+"=0 and r.taskKey=:taskKey ")
	public List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey);

}
