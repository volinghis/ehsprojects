/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.dao 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:11:30 
 */
package com.ehs.eam.eamLedgerManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午4:11:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Repository
public interface EamLedgerDao extends JpaRepository<EamLedger, String>  {

	@Query(" select el from EamLedger el where el."+EamLedger.DEVICE_NAME+" like %?1%  and el."+EamLedger.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<EamLedger> findEamLedgerList(String query,Pageable pageable);
	
	@Query(" select el from EamLedger el where el."+BaseEntity.KEY+"=?1 and el."+EamLedger.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
    public EamLedger findEamLedgerByKey(String key);
	
	/**
	 * 
	* @Function:findEamLedgerByScrapKey 
	* @Description: 获取所未报废设备
	* @param key 
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月8日 下午1:57:21 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月8日     qjj        v1.0.0            修改原因
	 */
	@Query(" select el from EamLedger el where el."+EamLedger.DEVICE_NAME+" like %?1%  and el."+EamLedger.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' and el."+EamLedger.SCRAP_KEY+" IS NULL order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<EamLedger> findEamLedgerListNotScrap(String query,Pageable pageable);
}