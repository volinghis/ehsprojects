package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.utils.FlowConstans;
import com.ehs.common.organization.entity.entitysuper.OrganizationInfo;
import com.ehs.eam.checks.bean.CheckTaskAnalysisBean;
import com.ehs.eam.checks.entity.EamCheckTask;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckTaskDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:56:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface EamCheckTaskDao extends JpaRepository<EamCheckTask, String>{

	/**
	 * 
	* @Function: EamCheckTaskDao.java
	* @Description: 查询检修任务
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:56:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select t from EamCheckTask t left join EamCheckPlan eamCheckPlan on t."+EamCheckTask.PLAN_KEY+"=eamCheckPlan."+BaseEntity.KEY+" left join FlowProcessInfo flowProcessInfo on t."+BaseEntity.KEY+"=flowProcessInfo."+FlowProcessInfo.BUSINESS_ENTITY_KEY
			+" where t."+BaseEntity.DELETED+" =0 "
			+" and ( 'ALL'=:times "
			+" or ( 'TODAY'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") = to_days(current_date())) "
			+" or ( 'THREE'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") > (to_days(current_date())-3))  "
			+" or ( 'SEVEN'=:times and to_days(t."+EamCheckTask.OWNER_CREATION_TIME+") > (to_days(current_date())-7))  "
			+ " ) "
			+" and ( 'ALL'=:owners and (:userKey  = t."+EamCheckTask.USER+" or LOCATE(:userKey,flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+")>0 )"
			+" or ( 'EXECUTE'=:owners and :userKey  = t."+EamCheckTask.USER+") "
			+" or ( 'APPROVE'=:owners and :userKey <>  t."+EamCheckTask.USER+" and LOCATE(:userKey,flowProcessInfo."+FlowProcessInfo.FLOW_PERSONS+")>0 )"
			+ " ) "

			+" and ( 'ALL'=:checks "
			+" or ('INCLUDE'=:checks and t."+EamCheckTask.REPAIRS+" = 1)"
			+" or ('EXCLUDE'=:checks and t."+EamCheckTask.REPAIRS+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:defects "
			+" or ('INCLUDE'=:defects and t."+EamCheckTask.DEFECTS+" = 1)"
			+" or ('EXCLUDE'=:defects and t."+EamCheckTask.DEFECTS+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:revers "
			+" or ('INCLUDE'=:revers and t."+EamCheckTask.RESERVES+" = 1)"
			+" or ('EXCLUDE'=:revers and t."+EamCheckTask.RESERVES+" = 0)"
			+ " ) "
			+" and ( 'ALL'=:executeResult  or t."+EamCheckTask.RESULT+" = :executeResult ) "
			+" and ( 'ALL'=:flowstatus "
			+" or ( 'DRAFT'=:flowstatus and (( ISNULL(flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+")=1 OR LENGTH(TRIM(flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+")) <=0) or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_DRAFT+"' )  ) "
			+" or ('END'=:flowstatus and (flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_CANCELED+"' or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_END+"')  ) "
			+" or flowProcessInfo."+FlowProcessInfo.FLOW_CURRENT_STEP+" = :flowstatus "
			+ " ) "
			+ " and (eamCheckPlan."+BaseEntity.DELETED+" =0 or eamCheckPlan."+BaseEntity.DELETED+" is null )"
			+ " and (flowProcessInfo."+BaseEntity.DELETED+" =0 or flowProcessInfo."+BaseEntity.DELETED+" is null )"
			+ "")
	public Page<EamCheckTask> findAllPlan(
			@Param("userKey") String userKey,
			@Param("times") String times,
			@Param("owners")  String owners,
			@Param("checks") String checks,
			@Param("defects") String defects,
			@Param("revers") String revers,
			@Param("flowstatus") String flowstatus,
			@Param("executeResult") String executeResult,
			Pageable pageable);
	
	/**
	 * 
	* @Function: EamCheckTaskDao.java
	* @Description:部门任务统计
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:57:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select new com.ehs.eam.checks.bean.CheckTaskAnalysisBean(o."+BaseEntity.KEY+",o."+OrganizationInfo.NAME+","
			+ "SUM((case when ISNULL(t."+BaseEntity.KEY+")=1 then 0 else 1 end) )"
			+" ) from OrganizationInfo o "
			+ " left join EamCheckTask t on o."+BaseEntity.KEY+"=t."+EamCheckTask.ORG
			+ " left join FlowProcessInfo p on t."+EamCheckTask.FLOW_PROCESS_INFO_KEY+"=p."+BaseEntity.KEY

			+" where o."+BaseEntity.DELETED+"=0 "
			+" and (t."+BaseEntity.DELETED+"=0 or ISNULL(t."+BaseEntity.KEY+")=1 )"
			+" and (t."+EamCheckTask.RESULT+"='NORMAL' or ISNULL(t."+BaseEntity.KEY+")=1 )"
			+" and ((p."+BaseEntity.DELETED+"=0 and p."+FlowProcessInfo.FLOW_CURRENT_STEP+"='"+FlowConstans.FLOW_STATUS_END+"') or ISNULL(p."+BaseEntity.KEY+")=1 )"

			+" group by o."+BaseEntity.KEY+",o."+OrganizationInfo.NAME
			)
	public List<CheckTaskAnalysisBean> analysisTaskForOrg();
}
