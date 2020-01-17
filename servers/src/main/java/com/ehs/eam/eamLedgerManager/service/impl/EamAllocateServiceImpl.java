/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service.impl 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:46:16 
 */
package com.ehs.eam.eamLedgerManager.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamAllocateDao;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;
import com.ehs.eam.eamLedgerManager.entity.EamAllocate;
import com.ehs.eam.eamLedgerManager.service.EamAllocateService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamAllocateServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月7日 下午7:46:16
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2020年1月7日
 * qjj v1.0.0 修改原因
 */
@Service
public class EamAllocateServiceImpl implements EamAllocateService {

	@Resource
	private EamAllocateDao EamAllocateDao;

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#saveEamAllocate(com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean)
	 */
	@Transactional
	@Override
	public void saveEamAllocate(EamAllocateRequestBean reqBean) {
		// TODO Auto-generated method stub
		List<EamLedger> ledgers=reqBean.getEamLedgerDatas();
		EamAllocate reqAllocate = reqBean.getAllocateForm();
		reqAllocate.setAllocateNum(BaseUtils.getNumberForAll(null));
		if (!CollectionUtils.isEmpty(ledgers)) {
			reqAllocate.setProfession(ledgers.get(0).getProfession());
			reqAllocate.setInstallLocation(ledgers.get(0).getInstallLocation());
		}
		//开始流程
     	flowBaseService.startProcess(reqAllocate, reqBean.getFlowProcessInfo());
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#findEamAllocateList(com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean)  
	*/
	@Override
	public PageInfoBean findEamAllocateList(EamAllocateQueryBean AllocateQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(AllocateQueryBean.getPage() - 1, AllocateQueryBean.getSize());
		Page<EamAllocate> eamAllocateage = EamAllocateDao.findEamAllocateList(AllocateQueryBean.getQuery(), pageRequest);
		List<EamAllocate> resList=eamAllocateage.getContent();
		for (EamAllocate el : resList) {
			FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(el.getKey());
			if(fpi!=null) {
				el.setStatus(fpi.getFlowCurrentStepName());
			}
		}
		if (eamAllocateage != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(resList);
			pb.setTotalCount(eamAllocateage.getTotalElements());
			return pb;
		}
		return null;
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#deleteEamAllocates(java.lang.String)  
	*/
	@Override
	@Transactional
	public void deleteEamAllocates(String keys) {
		// TODO Auto-generated method stub
		String [] keyArr=keys.split(",");
		if (keyArr.length>0) {
			for (int i = 0; i < keyArr.length; i++) {
				baseCommonService.deleteByKey(EamAllocate.class, keyArr[i]);
			}
		}
	}
}
