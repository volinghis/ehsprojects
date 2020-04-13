package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsedDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:58:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface EamCheckReserveUsedDao extends JpaRepository<EamCheckReserveUsed, String> {

	/**
	 * 
	* @Function: EamCheckReserveUsedDao.java
	* @Description: 查询所有检修已经使用的备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:58:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select r from EamCheckReserveUsed r where r."+BaseEntity.DELETED+"=0 and r.taskKey=:taskKey ")
	public List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey);

}
