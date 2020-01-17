package com.ehs.eam.eamPartLibraryManager.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午3:58:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/

@Repository
public interface PartsAccountDao extends JpaRepository<PartsAccount, String> {

	@Query(" select p from PartsAccount p where p."+PartsAccount.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<PartsAccount> findPartsAccountAll(Pageable pageable);

	@Query(" select p from PartsAccount p where p."+PartsAccount.DEVICE_CODE+"=?1 and p."+EnterWareHouse.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public List<PartsAccount> findByDeviceCode(String deviceCode);

	@Query(" select p from PartsAccount p where p."+PartsAccount.DEVICE_CODE+"=?1 and p."+PartsAccount.PRICE+"=?2 and p."+EnterWareHouse.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public PartsAccount findPartsAccount(String deviceCode, BigDecimal price);
}
