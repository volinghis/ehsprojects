/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.service.impl 
 * @author: qjj   
 * @date: 2020年3月25日 下午2:09:47 
 */
package com.ehs.eam.checks.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckRepairLedgerBean;
import com.ehs.eam.checks.dao.EamCheckRepairDao;
import com.ehs.eam.checks.dao.EamCheckRepairLedgerDao;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.entity.EamCheckRepairLedger;
import com.ehs.eam.checks.service.EamCheckRepairLedgerService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamCheckRepairLedgerServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年3月25日 下午2:09:47
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年3月25日
 *        qjj v1.0.0 修改原因
 */
@Service
public class EamCheckRepairLedgerServiceImpl implements EamCheckRepairLedgerService {

	@Resource
	private EamCheckRepairLedgerDao ecrlDao;

	@Resource
	private EamCheckRepairDao checkRepairDao;

	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * @see com.ehs.eam.checks.service.EamCheckRepairLedgerService#findAllRepairLedger(com.ehs.eam.checks.bean.CheckqueryBean)
	 */
	@Override
	public PageInfoBean findAllRepairLedger(CheckRepairLedgerBean queryBean) {
		Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
		Page<EamCheckRepairLedger> repairLedgers = ecrlDao.findRepairLedgers(SysAccessUser.get().getUserKey() ,queryBean.getAddress(),queryBean.getObjectKey(),queryBean.getObjectType(),queryBean.getResult(),
				queryBean.getUserType(), pb);
		if (repairLedgers != null) {
			List<EamCheckRepairLedger> resultList = repairLedgers.getContent();
			PageInfoBean pib = new PageInfoBean();
			for (EamCheckRepairLedger el : resultList) {
				DataDictionary sys = baseCommonService.findByKey(DataDictionary.class, el.getObjectKey());
				el.setObjectKey(sys.getText());
				DataDictionary addr = baseCommonService.findByKey(DataDictionary.class, el.getDeviceAddress());
				el.setDeviceAddress(addr.getText());
			}
			pib.setDataList(resultList);
			pib.setTotalCount(repairLedgers.getTotalElements());
			return pib;
		}
		return null;
	}

	/**
	 * @see com.ehs.eam.checks.service.EamCheckRepairLedgerService#updateDatasAfterFlow(com.ehs.common.flow.entity.impl.FlowProcessInfo)
	 */
	@Override
	@Transactional
	public void addDatasAfterFlow(FlowProcessInfo flowProcessInfo) {
		List<EamCheckRepair> eamCheckRepair = checkRepairDao.findByTaskKey(flowProcessInfo.getBusinessEntityKey());
		if (!CollectionUtils.isEmpty(eamCheckRepair)) {
			for (EamCheckRepair er : eamCheckRepair) {
				EamCheckRepairLedger checkLedger = new EamCheckRepairLedger();
				BeanUtils.copyProperties(er, checkLedger);
				baseCommonService.saveOrUpdate(checkLedger);
			}
		}
	}

}
