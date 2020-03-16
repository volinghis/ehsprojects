package com.ehs.eam.eamLedgerManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;

@Repository
public interface EamLedgerLastDao extends JpaRepository<EamLedgerLast, String> {
	/**
	 * 
	* @Function:findEamLedgerByScrapKey 
	* @Description: 获取所未报废设备
	* @param key 
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月8日 下午1:57:21 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月8日     qjj        v1.0.0            修改原因
	 */
	@Query(" select el from EamLedgerLast el where el."+EamLedgerLast.DEVICE_NAME+" like %?1%  and el."+BaseEntity.DATA_MODEL+" in ?2 order by "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<EamLedgerLast> findEamLedgerList(String query,DataModel[] dataModels,Pageable pageable);
	
	
	@Query(" select el from EamLedgerLast el where el."+BaseEntity.KEY+"=?1 and el."+BaseEntity.DATA_MODEL+" in ?2 order by "+BaseEntity.BASE_SORT_NUM+" desc")
    public EamLedgerLast findEamLedgerLastByKey(String key,DataModel[] dataModels);
	
	@Query(" select el from EamLedgerLast el where el."+EamLedgerLast.REF_KEY+"=?1 and el."+BaseEntity.DATA_MODEL+" in ?2 order by "+BaseEntity.BASE_SORT_NUM+" desc")
    public EamLedgerLast findEamLedgerLastByRefKey(String key,DataModel[] dataModels);
}

