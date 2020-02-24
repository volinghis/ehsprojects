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

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamAllocateRequestBean;
import com.ehs.eam.eamLedgerManager.bean.EamFlowBean;
import com.ehs.eam.eamLedgerManager.dao.EamAllocateDao;
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
			reqAllocate.setProfession(ledgers.get(0).getProfession());
			reqAllocate.setInstallLocation(ledgers.get(0).getInstallLocation());
		}
		String deptKey = reqAllocate.getTargetDept();
		if (StringUtils.isNotBlank(deptKey) && deptKey.length() >= 32) {
			OrganizationInfo oi = baseCommonService.findByKey(OrganizationInfo.class, deptKey);
			reqAllocate.setTargetDept(oi == null ? null : oi.getName());
		}

		// 开始流程
		ProcessInstance pi = flowBaseService.startProcess(reqAllocate, reqBean.getFlowProcessInfo());
		String entityKey = "";
		if (pi != null) {
			entityKey = pi.getBusinessKey();
			// 保存关联的设备
			if (!CollectionUtils.isEmpty(ledgers)) {
				EamLedger el = baseCommonService.findByKey(EamLedger.class, ledgers.get(0).getKey());
				el.setDeviceStatus("调拨申请中");
				el.setAllocateKey(entityKey);
				baseCommonService.saveOrUpdate(el);
			}
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamAllocateService#findEamAllocateList(com.ehs.eam.eamLedgerManager.bean.EamAllocateQueryBean)
	 */
	@Override
	public PageInfoBean findEamAllocateList(EamAllocateQueryBean AllocateQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(AllocateQueryBean.getPage() - 1, AllocateQueryBean.getSize());
		Page<EamAllocate> eamAllocateage = eamAllocateDao.findEamAllocateList(AllocateQueryBean.getQuery(),
				pageRequest);
		if (eamAllocateage != null) {
			List<EamAllocate> resList = eamAllocateage.getContent();
			for (EamAllocate el : resList) {
				FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(el.getKey());
				if (fpi != null) {
					el.setStatus(fpi.getFlowCurrentStepName());
					if(StringUtils.equals(fpi.getFlowCurrentStep(), "END")) {
						el.setCurrentStepPerson(fpi.getFlowPrevPersonName());
					}else {
						el.setCurrentStepPerson(fpi.getFlowCurrentPersonName());
					}
				}
				OrganizationInfo org=baseCommonService.findByKey(OrganizationInfo.class, el.getTargetDept());
				if(org!=null) {
					el.setTargetDept(org.getName());
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
		// 更新设备报废流程信息表数据
		EamAllocate ea = baseCommonService.findByKey(EamAllocate.class, flowProcessInfo.getBusinessEntityKey());
		if (ea != null) {
			ea.setAllocateDate(new Timestamp(System.currentTimeMillis()));
			//设备更新表数据更新
			EamLedger el = eamAllocateDao.findEamByAllocateKey(flowProcessInfo.getBusinessEntityKey());
			el.setDeviceStatus("已调拨");
			el.setInstallLocation(ea.getTargetPosition());
			el.setProfession(ea.getTargetDept());
			//设备台账表数据更新
			EamLedgerLast ell = baseCommonService.findByKey(EamLedgerLast.class, el.getKey());
			ell.setInstallLocation(ea.getTargetPosition());
			ell.setProfession(ea.getTargetDept());
		}
	}

	@Override
	public EamFlowBean findAllocateFlowBean(String key) {
		EamAllocate eamAllocate = baseCommonService.findByKey(EamAllocate.class, key);
		EamFlowBean eFlowBean = new EamFlowBean();
		if (eamAllocate != null) {
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(eamAllocate.getKey());
			if (fpi != null) {
				eFlowBean.setCurrentStep(fpi.getFlowCurrentStepName());
				eFlowBean.setCurrentUser(eamAllocate.getOwnerName());
				eFlowBean.setEditPage(fpi.getFlowEditPage());
				eFlowBean.setViewPage(fpi.getFlowViewPage());
				eFlowBean.setInstanceId(fpi.getFlowProcessInstanceId());
				eFlowBean.setStartActivityId(fpi.getFlowStartActivityId());
			}
		}
		return eFlowBean;
	}

	@Override
	public EamLedger EamLedgerByAllocateKey(String key) {
		return eamAllocateDao.findEamByAllocateKey(key);
	}
}
