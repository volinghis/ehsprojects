/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.dao 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:46:51 
 */
package com.ehs.eam.eamLedgerManager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamScrapDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月7日 下午7:46:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月7日     qjj           v1.0.0               修改原因
*/
@Repository
public interface EamScrapDao extends JpaRepository<EamScrap, String>{
	
	

	@Query(" select el from EamScrap el left join FlowProcessInfo fi on el. "+EamScrap.FLOW_PROCESS_INFO_KEY+"= fi."+FlowProcessInfo.KEY
			+" where el."+BaseEntity.DELETED+" =0 "
			+" and el."+EamScrap.APPLICATION_NAME+" like %:query% "
			+" and ('ALL'=:status "
			+ " or('APPROVL'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask2' ) "
			+ " or('END'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"=:status )"
			+ " or('REJECT'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"='usertask1' ) "
			+ " or('OVERDUE'=:status and fi."+FlowProcessInfo.FLOW_CURRENT_STEP+"<>'END' and (TO_DAYS(current_date())-TO_DAYS(el."+ EamScrap.APPLICATION_TIME + "))>7) "
			+ " )"
			+ "")
	public Page<EamScrap> findEamScrapList(@Param("query")String query,@Param("status") String status,Pageable pageable);
}
