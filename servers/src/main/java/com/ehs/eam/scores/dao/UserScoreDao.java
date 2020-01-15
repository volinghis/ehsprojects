package com.ehs.eam.scores.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.organization.entity.entitysuper.OrgUser;
import com.ehs.eam.scores.bean.UserScoreBean;
import com.ehs.eam.scores.entity.impl.UserScore;

@Repository
public interface UserScoreDao extends JpaRepository<UserScore, String>{

	
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end) else 0 end  )"
			+ ",avg( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) else 5 end))   "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where ou."+OrgUser.KEY+" = ?3 "
			+ " and (u."+BaseEntity.DATA_MODEL+" in ?4 or u."+BaseEntity.DATA_MODEL+" is null)"
			+ " and ou."+BaseEntity.DATA_MODEL+" in ?4 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public UserScoreBean findUserScores(int[] year,int[] month,String userKey,DataModel[] dataModels);
	
	
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end)"
			+ ",avg( case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) )  "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where ou."+OrgUser.KEY+" = ?1 "
			+ " and (u."+BaseEntity.DATA_MODEL+" in ?2 or u."+BaseEntity.DATA_MODEL+" is null)"
			+ " and ou."+BaseEntity.DATA_MODEL+" in ?2 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public UserScoreBean findUserScores(String userKey,DataModel[] dataModels);
	
	
	
	
	@Query(" select new com.ehs.eam.scores.bean.UserScoreBean("
			+ "ou."+OrgUser.KEY+","
			+ "ou."+OrgUser.NAME+""
			+ ",sum( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.SCORE+")=1 then 0 else u."+UserScore.SCORE+" end) else 0 end  )"
			+ ",avg( case when u."+UserScore.YEARS+" in ?1 and u."+UserScore.MONTHS+" in ?2 then (case when isnull(u."+UserScore.RATE+")=1 then 5 else u."+UserScore.RATE+" end) else 5 end))   "
			+ " from OrgUser ou left join UserScore u on ou."+BaseEntity.KEY+"=u."+UserScore.USER_KEY+" "
			+ " where (u."+BaseEntity.DATA_MODEL+" in ?3 or u."+BaseEntity.DATA_MODEL+" is null)"
			+ " and ou."+BaseEntity.DATA_MODEL+" in ?3 "
			+ " group by ou."+OrgUser.KEY+",ou."+OrgUser.NAME
			+ " order by sum(u."+UserScore.SCORE+")  desc ")
	public Page<UserScoreBean> findUserScores(int[] year,int[] month,DataModel[] dataModels,Pageable pageable);
	
	
	
}
