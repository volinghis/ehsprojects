/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.dao 
 * @author: qjj   
 * @date: 2020年1月6日 下午1:30:37 
 */
package com.ehs.eam.eamLedgerManager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectorsDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月6日 下午1:30:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月6日     qjj           v1.0.0               修改原因
*/
@Repository
public interface EamInspectorsDao extends JpaRepository<EamInspectors, String>{

	@Query(" select ei from EamInspectors ei where ei."+EamInspectors.DEVICE_KEY+" =?1 and ei."+EamInspectors.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<EamInspectors> findEamInspectorsByDeviceKey(String key);
}
