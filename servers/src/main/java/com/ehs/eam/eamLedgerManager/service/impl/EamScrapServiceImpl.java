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
import com.ehs.eam.eamLedgerManager.dao.EamLedgerLastDao;
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
	private EamLedgerLastDao eamLastDao;
	
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
		EamLedger eamLedger=reqBean.getScrapDatas().get(0);
		reqScrap.setDeviceKey(eamLedger.getKey());
		// 开始流程
		ProcessInstance pi = flowBaseService.startProcess(reqScrap, reqBean.getFlowProcessInfo());
		if (pi != null) {// 保存关联的设备
			List<EamLedger> eamLedgers = reqBean.getScrapDatas();
			if (!CollectionUtils.isEmpty(eamLedgers)) {
				EamLedger el = baseCommonService.findByKey(EamLedger.class, eamLedgers.get(0).getKey());
				el.setDeviceStatus("报废申请中");
				baseCommonService.saveOrUpdate(el);
			}
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#findEamScrapList(com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean)
	 */
	@Override
	public PageInfoBean findEamScrapList(EamScrapQueryBean scrapQueryBean) {
		PageRequest pr = PageRequest.of(scrapQueryBean.getPage() - 1, scrapQueryBean.getSize(),scrapQueryBean.getSortForJpaQuery());
		Page<EamScrap> eamScraPage = eamScrapDao.findEamScrapList(scrapQueryBean.getQuery(), scrapQueryBean.getStatus(),pr);
		if (eamScraPage != null) {
			List<EamScrap> resList = eamScraPage.getContent();
			for (EamScrap ep : resList) {
				FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(ep.getKey());
				if (fpi != null) {
					if (StringUtils.equals(fpi.getFlowCurrentStep(), "END")) {
						ep.setCurrentStepPerson(fpi.getFlowPrevPersonName());
						ep.setStatus(fpi.getFlowCurrentStepName());
					} else if (StringUtils.equals(fpi.getFlowCurrentStep(), "usertask1")){
						ep.setStatus("已驳回");
						ep.setScrapDate(fpi.getCreationTime());
						ep.setCurrentStepPerson(fpi.getFlowPrevPersonName());
					}else {
						ep.setStatus(fpi.getFlowCurrentStepName());
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
				eFlowBean.setCurrentStep(fpi.getFlowCurrentStep());
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
		//更新设备报废流程信息表数据
		EamScrap es=baseCommonService.findByKey(EamScrap.class, flowProcessInfo.getBusinessEntityKey());
		if(es!=null) {
			es.setScrapDate(new Timestamp(System.currentTimeMillis())); 
			//设备更新表数据更新
			EamLedger el = baseCommonService.findByKey(EamLedger.class, es.getDeviceKey());
			el.setDeviceStatus("已报废");
			baseCommonService.saveOrUpdate(el);
			
			//设备台账表数据更新
			EamLedgerLast ell = eamLastDao.findEamLedgerLastByRefKey(el.getKey());
			ell.setDeviceStatus("已报废");
			baseCommonService.saveOrUpdate(ell);
		}
		
	}
}
