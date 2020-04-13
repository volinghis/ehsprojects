package com.ehs.eam.scores.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.entitysuper.OrgUser;
import com.ehs.eam.scores.bean.UserScoreBean;
import com.ehs.eam.scores.entity.impl.UserScore;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: UserScoreDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:47:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface UserScoreDao extends JpaRepository<UserScore, String>{

	/**
	 * 
	* @Function: UserScoreDao.java
	* @Description: 查询所有用户的分数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:47:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end) else 0 end  )"
			+ ",avg( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) else 5 end))   "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where ou."+OrgUser.KEY+" = ?3 "
			+ " and (u."+BaseEntity.DELETED+" = 0 or u."+BaseEntity.DELETED+" is null)"
			+ " and ou."+BaseEntity.DELETED+" = 0 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public UserScoreBean findUserScores(int[] year,int[] month,String userKey);
	
	/**
	 * 
	* @Function: UserScoreDao.java
	* @Description: 查询每个人分数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:47:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end)"
			+ ",avg( case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) )  "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where ou."+OrgUser.KEY+" = ?1 "
			+ " and (u."+BaseEntity.DELETED+" = 0 or u."+BaseEntity.DELETED+" is null)"
			+ " and ou."+BaseEntity.DELETED+" = 0 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public UserScoreBean findUserScores(String userKey);
	
	/**
	 * 
	* @Function: UserScoreDao.java
	* @Description: 分页查询所有用户分数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:48:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when u."+UserScore.YEARS+" in :year and u."+UserScore.MONTHS+" in :month then (case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end) else 0 end  )"
			+ ",avg( case when u."+UserScore.YEARS+" in :year and u."+UserScore.MONTHS+" in :month then (case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) else 5 end))   "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where (u."+BaseEntity.DELETED+" = 0 or u."+BaseEntity.DELETED+" is null)"
			+ " and ou."+BaseEntity.DELETED+" = 0 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public Page<UserScoreBean> findUserScores(@Param("year") int[] year,@Param("month") int[] month,Pageable pageable);
	
	
	
}
