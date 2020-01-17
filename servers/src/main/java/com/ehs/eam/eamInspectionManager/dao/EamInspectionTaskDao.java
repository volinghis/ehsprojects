package com.ehs.eam.eamInspectionManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;

@Repository
public interface EamInspectionTaskDao extends JpaRepository<EamInspectionTask, String>{

	@Query(" select t from EamInspectionTask t where t."+EamInspectionTask.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	Page<EamInspectionTask> findAllTask(PageRequest pageRequest);

}
