/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service.impl 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:06:57 
 */
package com.ehs.eam.eamLedgerManager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamInspectorsDao;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerDao;
import com.ehs.eam.eamLedgerManager.dao.EamParametorsDao;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;
import com.ehs.eam.eamLedgerManager.service.EamLedgerService;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamLedgerServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月30日 下午4:06:57
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年12月30日
 * qjj v1.0.0 修改原因
 */
@Service
public class EamLedgerServiceImpl implements EamLedgerService {

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private EamLedgerDao eamLedgerDao;

	@Resource
	private EamInspectorsDao eamInspectorsDao;

	@Resource
	private EamParametorsDao eamParametersDao;

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#findEamLedgerList(com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean)
	 */
	@Override
	public PageInfoBean findEamLedgerList(EamLedgerQueryBean querybean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedger> eamLedgers = eamLedgerDao.findEamLedgerList(querybean.getQuery(), pageRequest);
		if (eamLedgers != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(eamLedgers.getContent());
			pb.setTotalCount(eamLedgers.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#saveEamLedger(com.ehs.eam.eamLedgerManager.bean.EamRequestBean)
	 */
	@Override
	@Transactional
	public void saveEamLedger(EamRequestBean eamRequestBean) {
		// TODO Auto-generated method stub
		EamLedger reqEamLedger = eamRequestBean.getEamLedger();
		String newKeys = "";
		String oldKeys = reqEamLedger.getRefDeviceKey();
		if (StringUtils.isNotBlank(oldKeys)) {// 关联子设备的保存
			newKeys = new StringBuffer(oldKeys).append(",").append(eamRequestBean.getDeviceKeys()).toString();
		} else {
			newKeys = eamRequestBean.getDeviceKeys();
		}
		reqEamLedger.setRefDeviceKey(newKeys);
		System.out.println("---------------------------->" + newKeys);
		if (StringUtils.isBlank(reqEamLedger.getKey())) {// 设备新建的时候初始化的值
			reqEamLedger.setDeviceStatus("正常");
			reqEamLedger.setDeviceNum(BaseUtils.getNumberForAll(reqEamLedger.getRunDate()));
		}
		EamLedger eamLedger = baseCommonService.saveOrUpdate(reqEamLedger);
		if (eamLedger != null) {// 设备台账保存成功后
			List<EamParameters> parameters = eamRequestBean.getParamsList();
			if (!CollectionUtils.isEmpty(parameters)) {

				for (EamParameters ep : parameters) {
					ep.setDeviceKey(eamLedger.getKey());
					baseCommonService.saveOrUpdate(ep);
				}
			}

			List<EamInspectors> inspectorsList = eamRequestBean.getInspectorsList();
			if (!CollectionUtils.isEmpty(inspectorsList)) {

				for (EamInspectors ei : inspectorsList) {
					ei.setDeviceKey(eamLedger.getKey());
					baseCommonService.saveOrUpdate(ei);
				}
			}

		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#getEamParametersByKey(java.lang.String)
	 */
	@Override
	public List<EamParameters> getEamParametersByKey(String code) {
		// TODO Auto-generated method stub
		return eamParametersDao.findEamParametersByDeviceKey(code);
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#getInspectorsByKey(java.lang.String)
	 */
	@Override
	public List<EamInspectors> getInspectorsByKey(String key) {
		// TODO Auto-generated method stub
		return eamInspectorsDao.findEamInspectorsByDeviceKey(key);
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#getChildDevByKey(java.lang.String)
	 */
	@Override
	public List<EamLedger> getChildDevByKey(String deviceKey) {
		// TODO Auto-generated method stub
		EamLedger eamLedger = baseCommonService.findByKey(EamLedger.class, deviceKey);
		return getCurrentList(eamLedger);
	}

	/*
	 * 获取当前设备的已有子设备
	 */
	private List<EamLedger> getCurrentList(EamLedger eamLedger) {
		String refKeys = "";
		if (eamLedger != null) {
			refKeys = eamLedger.getRefDeviceKey();
		}
		List<EamLedger> resEamLedgers = new ArrayList<EamLedger>();
		if (StringUtils.isNotBlank(refKeys)) {
			String[] keysArr = refKeys.split(",");
			for (int i = 0; i < keysArr.length; i++) {
				EamLedger el = eamLedgerDao.findEamLedgerByKey(keysArr[i]);
				if (el != null) {
					resEamLedgers.add(el);
				}
			}
		}
		return resEamLedgers;
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#findLeftEamLedgerList(com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean)
	 */
	@Override
	public PageInfoBean findLeftEamLedgerList(EamLedgerQueryBean querybean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedger> allLedgers = eamLedgerDao.findEamLedgerList(querybean.getQuery(), pageRequest);

		List<EamLedger> resultList = new ArrayList<EamLedger>();
		EamLedger curLedger = baseCommonService.findByKey(EamLedger.class, querybean.getDeviceKey());
		List<EamLedger> currentLedgers = getCurrentList(curLedger);
		if (currentLedgers == null || currentLedgers.isEmpty()) {
			resultList = allLedgers.getContent().stream()
					.filter(s -> (!StringUtils.equals(s.getKey(), querybean.getDeviceKey())))
					.collect(Collectors.toList());
		}
		resultList = allLedgers.getContent().stream()
				.filter(s -> currentLedgers.stream()
						.allMatch(ss -> (!StringUtils.equals(s.getKey(), ss.getKey()))
								&& (!StringUtils.equals(s.getKey(), querybean.getDeviceKey()))))
				.collect(Collectors.toList());

		if (resultList == null || resultList.isEmpty()) {
			return null;
		}
		PageInfoBean pb = new PageInfoBean();
		pb.setDataList(resultList);
		pb.setTotalCount(allLedgers.getTotalElements());
		return pb;
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#saveRelatedDevices(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	@Transactional
	public void saveRelatedDevices(String deviceKey, String keys) {
		// TODO Auto-generated method stub
		EamLedger eamLedger = baseCommonService.findByKey(EamLedger.class, deviceKey);
		if (eamLedger != null) {
			keys = eamLedger.getRefDeviceKey() + "," + keys;
			eamLedger.setRefDeviceKey(keys);
			System.out.println("===================" + keys);
		}
		baseCommonService.saveOrUpdate(eamLedger);
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#removeRelatedEamLedgers(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	@Transactional
	public void removeRelatedEamLedgers(String devicekey, String keys) {
		// TODO Auto-generated method stub
		EamLedger eamLedger = baseCommonService.findByKey(EamLedger.class, devicekey);
		String hadKeys = "";
		if (eamLedger != null) {
			hadKeys = eamLedger.getRefDeviceKey();
			List<String> arr1 = new ArrayList<String>(Arrays.asList(hadKeys.split(",")));
			List<String> arr2 = new ArrayList<String>(Arrays.asList(keys.split(",")));
			arr1.removeAll(arr2);
			eamLedger.setRefDeviceKey(StringUtils.join(arr1.toArray(), ","));
			baseCommonService.saveOrUpdate(eamLedger);
		}
	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#findLeftEamLedgerForScrap(com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean)
	 */
	@Override
	public PageInfoBean findLeftEamLedgerForScrap(EamLedgerQueryBean querybean) {
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedger> allLedgers = eamLedgerDao.findEamLedgerListNotScrap(querybean.getQuery(), pageRequest);
		PageInfoBean pb = new PageInfoBean();
		pb.setDataList(allLedgers.getContent());
		pb.setTotalCount(allLedgers.getTotalElements());
		return pb;
	}
}
