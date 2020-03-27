/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.checks.service.impl 
 * @author: qjj   
 * @date: 2020年3月26日 上午11:01:16 
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

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.checks.bean.CheckDefectAnalysisBean;
import com.ehs.eam.checks.bean.CheckDefectLedgerBean;
import com.ehs.eam.checks.dao.EamCheckDefectDao;
import com.ehs.eam.checks.dao.EamCheckDefectLedgerDao;
import com.ehs.eam.checks.dao.EamCheckRepairDao;
import com.ehs.eam.checks.entity.EamCheckDefect;
import com.ehs.eam.checks.entity.EamCheckDefectLedger;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.entity.EamCheckRepairLedger;
import com.ehs.eam.checks.service.EamCheckDefectLedgerService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamCheckDefectLedgerServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年3月26日 上午11:01:16 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月26日     qjj           v1.0.0               修改原因
*/
@Service
public class EamCheckDefectLedgerServiceImpl implements EamCheckDefectLedgerService {

	@Resource
	private EamCheckDefectLedgerDao defectLedgerDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EamCheckDefectDao checkDefectDao;
	/** 
	* @see com.ehs.eam.checks.service.EamCheckDefectLedgerService#findAllDefectLedgers(com.ehs.eam.checks.bean.CheckDefectLedgerBean)  
	*/
	@Override
	public PageInfoBean findAllDefectLedgers(CheckDefectLedgerBean queryBean) {
		Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
		Page<EamCheckDefectLedger> defectLedgers = defectLedgerDao.findDefectLedgers(queryBean.getAddress(), queryBean.getObjectKey(), queryBean.getObjectType(), queryBean.getStatus(), queryBean.getLevel(),pb);
		if (defectLedgers != null) {
			List<EamCheckDefectLedger> resultList = defectLedgers.getContent();
			PageInfoBean pib = new PageInfoBean();
			for (EamCheckDefectLedger el : resultList) {
				DataDictionary sys = baseCommonService.findByKey(DataDictionary.class, el.getObjectKey());
				el.setObjectKey(sys.getText());
				DataDictionary addr = baseCommonService.findByKey(DataDictionary.class, el.getDeviceAddress());
				el.setDeviceAddress(addr.getText());
			}
			pib.setDataList(resultList);
			pib.setTotalCount(defectLedgers.getTotalElements());
			return pib;
		}
		
		return null;
	}

	/** 
	* @see com.ehs.eam.checks.service.EamCheckDefectLedgerService#addDatasAfterFlow(com.ehs.common.flow.entity.impl.FlowProcessInfo)  
	*/
	@Override
	@Transactional
	public void addDatasAfterFlow(FlowProcessInfo flowProcessInfo) {
		List<EamCheckDefect> eamCheckDefect = checkDefectDao.findByTaskKey(flowProcessInfo.getBusinessEntityKey());
		if (!CollectionUtils.isEmpty(eamCheckDefect)) {
			for (EamCheckDefect ef : eamCheckDefect) {
				EamCheckDefectLedger checkLedger = new EamCheckDefectLedger();
				BeanUtils.copyProperties(ef, checkLedger);
				baseCommonService.saveOrUpdate(checkLedger);
			}
		}
	}

	@Override
	public List<CheckDefectAnalysisBean> analysisByType(String type, boolean onlyMajor, boolean onlyStatusError) {
		return defectLedgerDao.analysisByType(type, onlyMajor, onlyStatusError);
	}


}
