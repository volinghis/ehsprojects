package com.ehs.common.basicInfo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: CommodityDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 下午3:40:15 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface CommodityDao extends JpaRepository<PartsAccount, String>{

	@Query(" select c from PartsAccount c where c."+PartsAccount.DELETED+"=0 and (c."+PartsAccount.DEVICE_NAME+" like %?1% or c."+PartsAccount.DEVICE_CODE+" like %?1% ) and "+PartsAccount.WAREHOUSE_CODE+" is null order by  "+BaseEntity.BASE_SORT_NUM+" desc")
	Page<PartsAccount> findCommodities(String query, PageRequest pageRequest);

}
