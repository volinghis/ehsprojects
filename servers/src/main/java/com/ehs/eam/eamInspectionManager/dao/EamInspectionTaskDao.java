package com.ehs.eam.eamInspectionManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamInspectionManager.entity.EamInspectionTask;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionTaskDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:27:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface EamInspectionTaskDao extends JpaRepository<EamInspectionTask, String>{

	@Query(" select t from EamInspectionTask t where t."+EamInspectionTask.DELETED+" = 0 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	Page<EamInspectionTask> findAllTask(PageRequest pageRequest);

}
