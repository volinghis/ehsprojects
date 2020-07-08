package com.ehs.common.basicInfo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.basicInfo.entity.ClientInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 下午2:07:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface ClientDao extends JpaRepository<ClientInfo, String>  {

	@Query(" select c from ClientInfo c where c."+ClientInfo.DELETED+"=0 and (c."+ClientInfo.CLIENT_CODE+" like %?1% or c."+ClientInfo.CLIENT_NAME+" like %?1% ) order by  "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<ClientInfo> findClients(String query, PageRequest pageRequest);

}
