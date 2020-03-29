/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.dao 
 * @author: qjj   
 * @date: 2020年3月26日 上午10:39:39 
 */
package com.ehs.eam.checks.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.eam.checks.bean.CheckDefectAnalysisBean;
import com.ehs.eam.checks.entity.EamCheckDefectLedger;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckDefectLedgerDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月26日 上午10:39:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月26日     qjj           v1.0.0               修改原因
*/
public interface EamCheckDefectLedgerDao  extends JpaRepository<EamCheckDefectLedger, String>{
	
	@Query("select t from EamCheckDefectLedger t where t."+BaseEntity.DELETED+"=0 "
			+ " and ('ALL'=:address or t.deviceAddress= :address) "
			+ " and ('ALL'=:objectKey or t.objectKey = :objectKey) "
			+ " and ('ALL'=:objectType or t.objectType = :objectType) "
			+" and ( 'ALL'=:status "
			+" or ('OK'=:status and t.status = :status)"
			+" or ('ERROR'=:status and t.status = :status)"
			+ " ) "
			+" and ( 'ALL'=:level "
			+" or ('NORMAL'=:level and t.level = :level)"
			+" or ('MAJOR'=:level and t.level = :level)"
			+ " ) "
			+ "")
	public Page<EamCheckDefectLedger> findDefectLedgers(
			@Param("address") String address,
			@Param("objectKey")String objectKey,
			@Param("objectType")String objectType,
			@Param("status") String status,
			@Param("level") String level,
			Pageable pageable);
	
	
	@Query(" select new com.ehs.eam.checks.bean.CheckDefectAnalysisBean(d."+BaseEntity.KEY+",d."+DataDictionary.TEXT+",c."+BaseEntity.KEY+",c."+DataDictionary.TEXT+",SUM( "
			+ " (case when ISNULL(e."+BaseEntity.KEY+")=1 then 0 else 1 end) "
			+ ")) from  DataDictionary d "
			+"  join DataDictionary c "
			+ "on d."+DataDictionary.PARENT_KEY+"= 'deviceAddress' "
			+" and  c."+DataDictionary.PARENT_KEY+"= :type  "
			+" left join EamCheckDefectLedger e on "
			+"  e."+EamCheckDefectLedger.DEVICE_ADDRESS+"= d."+BaseEntity.KEY
			+" and e."+EamCheckDefectLedger.OBJECT_KEY+"= c."+BaseEntity.KEY
			+" where d."+BaseEntity.DELETED+"=0 "
			+" and c."+BaseEntity.DELETED+"=0 "
			+" and (e."+BaseEntity.DELETED+"=0 or ISNULL(e."+BaseEntity.DELETED+")=1 ) "
			+" and ("
			+ " ( true=:onlyMajor "
			+" and (e."+EamCheckDefectLedger.LEVEL+"='MAJOR' or ISNULL(e."+EamCheckDefectLedger.LEVEL+")=1) ) "
			+ " or false=:onlyMajor) "
			+" and ("
			+ " ( true=:onlyStatusError "
			+" and (e."+EamCheckDefectLedger.STATUS+"='ERROR' or ISNULL(e."+EamCheckDefectLedger.STATUS+")=1) ) "
			+ " or false=:onlyStatusError) "
			+ " group by d."+BaseEntity.KEY+",d."+DataDictionary.TEXT+",c."+BaseEntity.KEY+",c."+DataDictionary.TEXT
			)
	public List<CheckDefectAnalysisBean> analysisByType( @Param("type") String type,@Param("onlyMajor") boolean onlyMajor,@Param("onlyStatusError") boolean onlyStatusError);

}
