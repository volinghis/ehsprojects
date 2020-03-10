/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.dao 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:46:51 
 */
package com.ehs.eam.eamLedgerManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamAllocate;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamAllocateDao.java
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
public interface EamAllocateDao extends JpaRepository<EamAllocate, String>{

	@Query(" select el from EamAllocate el where el."+EamAllocate.ALLOCATE_NUM+" like %?1%  and el."+EamAllocate.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<EamAllocate> findEamAllocateList(String query,Pageable pageable);
	
	@Query(" select el from EamAllocate el where el."+EamAllocate.DEVICE_KEY+" =?1  and el."+BaseEntity.DATA_MODEL+" in ?2")
	public EamAllocate findByDeviceKey(String key,DataModel[] dataModels);
}
