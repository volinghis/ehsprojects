package com.ehs.eam.eamLedgerManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;

@Repository
public interface EamLedgerLastDao
		extends JpaRepository<EamLedgerLast, String> {
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
	 * Modification History: Date Author Version Description
	 * ---------------------------------------------------------* 2020年1月8日
	 * qjj v1.0.0 修改原因
	 */
	@Query(" select el from EamLedgerLast el where el. " + BaseEntity.DELETED + " = 0 " 
	        + " and ('ALL'=:name or el."+ EamLedgerLast.DEVICE_NAME + " like %:name% ) " 
			+ " and ('ALL'=:address or el."+ EamLedgerLast.INSTALL_LOCATION + "= :address) "
			+ " and ('ALL'=:profession or el."+ EamLedgerLast.PROFESSION + " = :profession) "
			+ " and ('ALL'=:deviceSystem or el."+ EamLedgerLast.DEVICE_SYSTEM + " = :deviceSystem) "
			+ " and ('ALL'=:status or el."+ EamLedgerLast.DEVICE_STATUS + " =:status) "
			+ " and ('ALL'=:complete " 
			+ " or ('LTHALF'=:complete and el."+ BaseEntity.COMPLETE_POINT+ " <50 ) " 
			+ " or ('GTHALF'=:complete and el."+ BaseEntity.COMPLETE_POINT+ " >50 ) "
			+ " ) " 
			+ " and ('ALL'=:time " 
			+ " or ('Y'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedgerLast.RUN_DATE + "))< 365 )" 
			+ " or ('LTY'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedgerLast.RUN_DATE + "))< 1095 )" 
			+ " or ('GTY'=:time and (TO_DAYS(current_date())-TO_DAYS(el."+ EamLedgerLast.RUN_DATE + "))>= 1095 )" 
			+ " ) "
			+ "")
	public Page<EamLedgerLast> findEamLedgerLastList(
			@Param("name") String name, 
			@Param("address") String address, 
			@Param("profession") String profession,
			@Param("deviceSystem") String deviceSystem,
			@Param("status") String status, 
			@Param("complete") String complete, 
			@Param("time") String time, 
			Pageable pageable);

	@Query(" select el from EamLedgerLast el where el."+ BaseEntity.DELETED + "=0  and  el."+ EamLedgerLast.DEVICE_NAME + " like %:name%")
	public Page<EamLedgerLast> findListByDeviceName(String name, Pageable pageable);

	@Query(" select el from EamLedgerLast el where el." + BaseEntity.KEY + "=?1 and el." + BaseEntity.DELETED
			+ "=0 order by " + BaseEntity.BASE_SORT_NUM + " desc")
	public EamLedgerLast findEamLedgerLastByKey(String key);

	@Query(" select el from EamLedgerLast el where el." + EamLedgerLast.REF_KEY + "=?1 and el." + BaseEntity.DELETED
			+ "=0 order by " + BaseEntity.BASE_SORT_NUM + " desc")
	public EamLedgerLast findEamLedgerLastByRefKey(String key);
}
