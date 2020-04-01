package com.ehs.common.organization.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.OrgUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月26日 上午11:21:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月26日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface OrgUserDao extends JpaRepository<OrgUser, String> {

	@Query(" select u from OrgUser u where u."+OrgUser.ORG_KEY+" =?1 and u."+OrgUser.DELETED+"=0 " )
	public List<OrgUser> findUserByOrgKey(String orgKey);

	public OrgUser findOrgUserBySysUserKey(String sysUserKey);
	
	public List<OrgUser> findOrgUserByOrgKeyAndDeleted(String orgKey,boolean deleted);

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 分页递归查询部门下所有人员
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月1日 上午10:45:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月1日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u."+OrgUser.DELETED+" = 0 and (isnull("+OrgUser.NAME+")=0 or isnull("+OrgUser.DATA_CODE+")=0 or LENGTH(trim(?1))>0 or u."+OrgUser.NAME+" like %?1% or u."+OrgUser.DATA_CODE+" like %?1% ) and u."+OrgUser.ORG_KEY+" in ?2 order by "+BaseEntity.BASE_SORT_NUM +" desc")
	public Page<OrgUser> findByUser(String query, String[] array, Pageable pb);
	
	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 不需要递归，直接根据部门查询人员的分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月1日 上午10:46:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月1日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u."+OrgUser.DELETED+" = 0 and (isnull("+OrgUser.NAME+")=0 or isnull("+OrgUser.DATA_CODE+")=0 or LENGTH(trim(?1))>0 or u."+OrgUser.NAME+" like %?1% or u."+OrgUser.DATA_CODE+" like %?1% ) and u."+OrgUser.ORG_KEY+" = ?2 order by "+BaseEntity.BASE_SORT_NUM +" desc ")
	public Page<OrgUser> findByUser(String query,String orgKey, Pageable pb);



}
