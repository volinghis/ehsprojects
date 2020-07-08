package com.ehs.common.basicInfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.basicInfo.entity.ContactInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ContactInfoDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 下午3:05:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface ContactInfoDao extends JpaRepository<ContactInfo, String>{

	@Query(" select c from ContactInfo c where c."+ContactInfo.DELETED+"=0 and c."+ContactInfo.CONTART_CODE+"=?1" )
	List<ContactInfo> findByCode(String key);

	
}
