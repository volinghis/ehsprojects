package com.ehs.eam.checks.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.checks.dao.EamCheckReserveUsedDao;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;
import com.ehs.eam.checks.service.EamCheckReserveUsedSerevice;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**
 * 
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckReserveUsedSereviceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月27日 上午10:19:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月27日     zhaol           v1.0.0               修改原因
 */
@Service
public class EamCheckReserveUsedSereviceImpl implements EamCheckReserveUsedSerevice{

	@Resource
	private EamCheckReserveUsedDao reserveUsedDao;
	
	@Resource
	private PartsAccountDao partsAccountDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckReserveUsedSerevice#findReserveUsedByTaskKey(java.lang.String)  
	* @Function: EamCheckReserveUsedSereviceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:35:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey) {
		return reserveUsedDao.findReserveUsedByTaskKey(taskKey);
	}

	/**
	 * 
	* @see com.ehs.eam.checks.service.EamCheckReserveUsedSerevice#updatePartsAfterFlow(com.ehs.common.flow.entity.impl.FlowProcessInfo)  
	* @Function: EamCheckReserveUsedSereviceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月27日 上午10:35:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月27日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void updatePartsAfterFlow(FlowProcessInfo flowProcessInfo) {
		List<EamCheckReserveUsed> reserveUseds = reserveUsedDao.findReserveUsedByTaskKey(flowProcessInfo.getBusinessEntityKey());
		if(!CollectionUtils.isEmpty(reserveUseds)) {
			for (EamCheckReserveUsed eamCheckReserveUsed : reserveUseds) {
				if(StringUtils.isNotBlank(eamCheckReserveUsed.getWareHouse()) && StringUtils.isNotBlank(eamCheckReserveUsed.getDeviceCode())) {
					PartsAccount partsAccount = partsAccountDao.getAccountBywareHouseAndDeviceCode(eamCheckReserveUsed.getWareHouse(), eamCheckReserveUsed.getDeviceCode());
					partsAccount.setAmount(partsAccount.getAmount()-eamCheckReserveUsed.getAmount());
					baseCommonService.saveOrUpdate(partsAccount);
				}
			}
		}
	}

}
