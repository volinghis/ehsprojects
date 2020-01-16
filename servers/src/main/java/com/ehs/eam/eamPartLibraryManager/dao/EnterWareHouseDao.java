package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouseDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月6日 上午10:10:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月6日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface EnterWareHouseDao extends JpaRepository<EnterWareHouse, String>{

	@Query(" select ewh from EnterWareHouse ewh where ewh."+EnterWareHouse.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.CREATION_TIME+" desc")
	Page<EnterWareHouse> findAll(PageRequest pageRequest);

}
