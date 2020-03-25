package com.ehs.common.notifys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.notifys.entity.impl.NotifyMessageInfo;

@Repository
public interface NotifyMessageInfoDao extends JpaRepository<NotifyMessageInfo, String>{
	
	@Query(" select t from NotifyMessageInfo t where t."+BaseEntity.DELETED+"=0 and t."+NotifyMessageInfo.READED+"=0 and t."+NotifyMessageInfo.USER+"=:user order by "+BaseEntity.BASE_SORT_NUM+" desc ")
	public List<NotifyMessageInfo> findByUserOfNotRead(@Param("user") String user);

	
	@Query(" select u from NotifyMessageInfo u where u."+NotifyMessageInfo.USER+"=:user and  u."+BaseEntity.DELETED+" =0 ")
	public  Page<NotifyMessageInfo> findAllByUser(@Param("user") String user,Pageable pageable);
}
