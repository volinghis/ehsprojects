/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service.impl 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:46:16 
 */
package com.ehs.eam.eamLedgerManager.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamScrapDao;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;
import com.ehs.eam.eamLedgerManager.service.EamScrapService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamScrapServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月7日 下午7:46:16
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月7日
 *        qjj v1.0.0 修改原因
 */
@Service
public class EamScrapServiceImpl implements EamScrapService {

	@Resource
	private EamScrapDao eamScrapDao;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private FlowBaseService flowBaseService;

	@Resource
	private FlowProcessInfoService flowProcessInfoService;

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#saveEamScrap(com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean)
	 */
	@Transactional
	@Override
	public void saveEamScrap(EamScrapRequestBean reqBean) {
		EamScrap reqScrap = reqBean.getEamScrap();
		reqScrap.setScrapNum(BaseUtils.getNumberForAll());
		// 开始流程
		ProcessInstance pi = flowBaseService.startProcess(reqScrap, reqBean.getFlowProcessInfo());
		String entityKey = "";
		if (pi != null) {
			entityKey = pi.getBusinessKey();
			// 保存关联的设备
			List<EamLedger> eamLedgers = reqBean.getScrapDatas();
			if (!CollectionUtils.isEmpty(eamLedgers)) {
				EamLedger el = baseCommonService.findByKey(EamLedger.class, eamLedgers.get(0).getKey());
				el.setDeviceStatus("报废申请中");
				el.setScrapKey(entityKey);
				baseCommonService.saveOrUpdate(el);
			}
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#findEamScrapList(com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean)
	 */
	@Override
	public PageInfoBean findEamScrapList(EamScrapQueryBean scrapQueryBean) {
		PageRequest pageRequest = PageRequest.of(scrapQueryBean.getPage() - 1, scrapQueryBean.getSize());
		Page<EamScrap> eamScraPage = eamScrapDao.findEamScrapList(scrapQueryBean.getQuery(), pageRequest);
		if (eamScraPage != null) {
			List<EamScrap> resList = eamScraPage.getContent();
			for (EamScrap ep : resList) {
				FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(ep.getKey());
				if (fpi != null) {
					ep.setStatus(fpi.getFlowCurrentStepName());
					if(StringUtils.equals(fpi.getFlowCurrentStep(), "END")) {
						ep.setCurrentStepPerson(fpi.getFlowPrevPersonName());
					}else {
						ep.setCurrentStepPerson(fpi.getFlowCurrentPersonName());
					}
				}
			}
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(resList);
			pb.setTotalCount(eamScraPage.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#deleteEamScraps(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteEamScraps(String keys) {
		String[] keyArr = keys.split(",");
		if (keyArr.length > 0) {
			for (int i = 0; i < keyArr.length; i++) {
				baseCommonService.deleteByKey(EamScrap.class, keyArr[i]);
			}
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#findScrapFlowBean(java.lang.String)
	 */
	@Override
	public EamFlowBean findScrapFlowBean(String key) {
		EamScrap eamScrap = baseCommonService.findByKey(EamScrap.class, key);
		EamFlowBean eFlowBean = new EamFlowBean();
		if (eamScrap != null) {
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(eamScrap.getKey());
			if (fpi != null) {
				eFlowBean.setCurrentStep(fpi.getFlowCurrentStepName());
				eFlowBean.setCurrentUser(eamScrap.getOwnerName());
				eFlowBean.setEditPage(fpi.getFlowEditPage());
				eFlowBean.setViewPage(fpi.getFlowViewPage());
				eFlowBean.setInstanceId(fpi.getFlowProcessInstanceId());
				eFlowBean.setStartActivityId(fpi.getFlowStartActivityId());
			}
		}
		return eFlowBean;
	}

	@Override
	@Transactional
	public void updateRelatedAfterFlow(FlowProcessInfo flowProcessInfo) {
		String status="已报废";
		//更新设备报废流程信息表数据
		EamScrap es=baseCommonService.findByKey(EamScrap.class, flowProcessInfo.getBusinessEntityKey());
		if(es!=null) {
			es.setScrapDate(new Timestamp(System.currentTimeMillis())); 
		}
		
		//设备更新表数据更新
		EamLedger el = eamScrapDao.findEamByScrapKey(flowProcessInfo.getBusinessEntityKey());
		el.setDeviceStatus(status);
		
		//设备台账表数据更新
		EamLedgerLast ell = baseCommonService.findByKey(EamLedgerLast.class, el.getKey());
		ell.setScrapKey(flowProcessInfo.getBusinessEntityKey());
		ell.setDeviceStatus(status);
		baseCommonService.saveOrUpdate(ell);
	}

	@Override
	public EamLedger findEamLedgerByScrapKey(String key) {
		return eamScrapDao.findEamByScrapKey(key);
	}
}