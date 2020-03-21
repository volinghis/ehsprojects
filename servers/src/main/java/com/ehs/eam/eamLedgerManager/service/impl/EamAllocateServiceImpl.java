/**   
s * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
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

import com.aspose.slides.internal.a.a;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.dao.EamAllocateDao;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerLastDao;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
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
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月7日
 *        qjj v1.0.0 修改原因
 */
@Service
public class EamAllocateServiceImpl implements EamAllocateService {

	@Resource
	private EamAllocateDao eamAllocateDao;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private FlowBaseService flowBaseService;

	@Resource
	private EamLedgerLastDao eamLastDao;

	@Resource
	private FlowProcessInfoService flowProcessInfoService;

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#saveEamAllocate(com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean)
	 */
	@Transactional
	@Override
	public void saveEamAllocate(EamAllocateRequestBean reqBean) {
		// TODO Auto-generated method stub
		List<EamLedger> ledgers = reqBean.getEamLedgerDatas();
		EamAllocate reqAllocate = reqBean.getAllocateForm();
		reqAllocate.setAllocateNum(BaseUtils.getNumberForAll());
		if (!CollectionUtils.isEmpty(ledgers)) {
			DataDictionary dataDictionary=baseCommonService.findByKey(DataDictionary.class, reqAllocate.getTargetPosition());
			reqAllocate.setInstallLocation(ledgers.get(0).getInstallLocationName());
			reqAllocate.setTargetPositionName(dataDictionary==null?"":dataDictionary.getText());
		}
		reqAllocate.setDeviceKey(ledgers.get(0).getKey());
		// 开始流程
		ProcessInstance pi = flowBaseService.startProcess(reqAllocate, reqBean.getFlowProcessInfo());
		if (pi != null) {// 保存关联的设备
			if (!CollectionUtils.isEmpty(ledgers)) {
				EamLedger el = baseCommonService.findByKey(EamLedger.class, ledgers.get(0).getKey());
				el.setDeviceStatus("调拨申请中");
				baseCommonService.saveOrUpdate(el);
			}
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#findEamAllocateList(com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean)
	 */
	@Override
	public PageInfoBean findEamAllocateList(EamAllocateQueryBean allocateQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(allocateQueryBean.getPage() - 1, allocateQueryBean.getSize(),allocateQueryBean.getSortForJpaQuery());
		Page<EamAllocate> eamAllocateage = eamAllocateDao.findEamAllocateList(allocateQueryBean.getQuery(),allocateQueryBean.getStatus(), pageRequest);
		if (eamAllocateage != null) {
			List<EamAllocate> resList = eamAllocateage.getContent();
			for (EamAllocate el : resList) {
				FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(el.getKey());
				if (fpi != null) {
					if (StringUtils.equals(fpi.getFlowCurrentStep(), "END")) {
						el.setCurrentStepPerson(fpi.getFlowPrevPersonName());
						el.setStatus(fpi.getFlowCurrentStepName());
					} else if (StringUtils.equals(fpi.getFlowCurrentStep(), "usertask1")){
						el.setStatus("已驳回");
						el.setAllocateDate(fpi.getCreationTime());
						el.setCurrentStepPerson(fpi.getFlowPrevPersonName());
					}else {
						el.setStatus(fpi.getFlowCurrentStepName());
						el.setCurrentStepPerson(fpi.getFlowCurrentPersonName());
					}
				}
			}
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
		String[] keyArr = keys.split(",");
		if (keyArr.length > 0) {
			for (int i = 0; i < keyArr.length; i++) {
				baseCommonService.deleteByKey(EamAllocate.class, keyArr[i]);
			}
		}
	}

	@Override
	@Transactional
	public void updateRelatedAfterFlow(FlowProcessInfo flowProcessInfo) {
		// 更新设备调拨流程信息表数据
		EamAllocate ea = baseCommonService.findByKey(EamAllocate.class, flowProcessInfo.getBusinessEntityKey());
		if (ea != null) {
			ea.setAllocateDate(new Timestamp(System.currentTimeMillis()));
			DataDictionary dataDictionary=baseCommonService.findByKey(DataDictionary.class, ea.getTargetPosition());
			String locationName=dataDictionary==null?"":dataDictionary.getText();
			// 设备更新表数据更新
			EamLedger el = baseCommonService.findByKey(EamLedger.class, ea.getDeviceKey());
			el.setDeviceStatus("正常");
			el.setInstallLocation(ea.getTargetPosition());
			el.setInstallLocationName(locationName);
			baseCommonService.saveOrUpdate(el);
			// 设备台账表数据更新
			EamLedgerLast ell = eamLastDao.findEamLedgerLastByRefKey(el.getKey());
			ell.setInstallLocation(ea.getTargetPosition());
			ell.setInstallLocationName(locationName);
			baseCommonService.saveOrUpdate(ell);
		}
	}

	@Override
	public EamFlowBean findAllocateFlowBean(String key) {
		EamAllocate eamAllocate = baseCommonService.findByKey(EamAllocate.class, key);
		EamFlowBean eFlowBean = new EamFlowBean();
		if (eamAllocate != null) {
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(eamAllocate.getKey());
			if (fpi != null) {
				eFlowBean.setCurrentStep(fpi.getFlowCurrentStep());
				eFlowBean.setCurrentUser(eamAllocate.getOwnerName());
				eFlowBean.setEditPage(fpi.getFlowEditPage());
				eFlowBean.setViewPage(fpi.getFlowViewPage());
				eFlowBean.setInstanceId(fpi.getFlowProcessInstanceId());
				eFlowBean.setStartActivityId(fpi.getFlowStartActivityId());
			}
		}
		return eFlowBean;
	}
}
