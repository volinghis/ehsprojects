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
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamInspectorsDao;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerDao;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerLastDao;
import com.ehs.eam.eamLedgerManager.dao.EamParametorsDao;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
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
	private EamLedgerLastDao eamLedgerLastDao;

	@Resource
	private EamInspectorsDao eamInspectorsDao;

	@Resource
	private EamParametorsDao eamParametersDao;

	@Resource
	private FlowBaseService flowBaseService;

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
		EamLedger reqEamLedger = eamRequestBean.getEamLedger();
		String newKeys = "";
		String fileId="";
		String oldKeys = reqEamLedger.getRefDeviceKey();
		if (StringUtils.isNotBlank(oldKeys)) {// 关联子设备的保存
			newKeys = new StringBuffer(oldKeys).append(",").append(eamRequestBean.getDeviceKeys()).toString();
		} else {
			newKeys = eamRequestBean.getDeviceKeys();
		}
		if (StringUtils.isNotBlank(reqEamLedger.getFileId())) {// 关联文件的保存
			fileId = new StringBuffer(reqEamLedger.getFileId()).append(",").append(eamRequestBean.getFileIds()).toString();
		} else {
			fileId = eamRequestBean.getFileIds();
		}
		reqEamLedger.setFileId(fileId);
		reqEamLedger.setRefDeviceKey(newKeys);
		// 设备新建的时候初始化的值
		String deviceNum=BaseUtils.getNumberForAll();
		if (StringUtils.isBlank(reqEamLedger.getKey())) {
			reqEamLedger.setDeviceStatus("正常");
			reqEamLedger.setDeviceNum(deviceNum);
		}
		// 开始流程
		ProcessInstance pi = flowBaseService.startProcess(reqEamLedger, eamRequestBean.getFlowProcessInfo());
		String entityKey = "";
		if (pi != null) {
			entityKey = pi.getBusinessKey();
			List<EamParameters> parameters = eamRequestBean.getParamsList();
			if (!CollectionUtils.isEmpty(parameters)) {
				for (EamParameters ep : parameters) {
					ep.setDeviceKey(entityKey);
					baseCommonService.saveOrUpdate(ep);
				}
			}

			List<EamInspectors> inspectorsList = eamRequestBean.getInspectorsList();
			if (!CollectionUtils.isEmpty(inspectorsList)) {
				for (EamInspectors ei : inspectorsList) {
					ei.setDeviceKey(entityKey);
					baseCommonService.saveOrUpdate(ei);
				}
			}
			// 同步数据eamLedgerLast表中
			EamLedgerLast eLast=eamRequestBean.getEamLedgerLast();
			if(StringUtils.isNotBlank(eLast.getKey())) {
			    EamLedgerLast eLastOld=	eamLedgerLastDao.findEamLedgerLastByRefKey(entityKey, new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
				BeanUtils.copyProperties(eLast,eLastOld,BaseEntity.ID,BaseEntity.KEY,EamLedgerLast.REF_KEY);
			    baseCommonService.saveOrUpdate(eLastOld);
			} else {
				eLast.setDeviceStatus("正常");
				eLast.setDeviceNum(deviceNum);
				eLast.setRefKey(entityKey);
				baseCommonService.saveOrUpdate(eLast);
			}
			
		}

	}

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#getEamParametersByKey(java.lang.String)
	 */
	@Override
	public List<EamParameters> getEamParametersByKey(String key) {
		// TODO Auto-generated method stub
		return eamParametersDao.findEamParametersByDeviceKey(key);
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

	@Override
	public PageInfoBean findEamLedgersNotInFlow(EamLedgerQueryBean querybean) {
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedger> eamLedgers = eamLedgerDao.findEamLedgerList(querybean.getQuery(), pageRequest);
		if (eamLedgers != null) {
			PageInfoBean pb = new PageInfoBean();
			List<EamLedger> resultList=new ArrayList<EamLedger>();
			resultList = eamLedgers.getContent().stream().filter(s -> (!StringUtils.equals(s.getDeviceStatus(),"已报废")&&!StringUtils.contains(s.getDeviceStatus(), "申请中"))).collect(Collectors.toList());
			pb.setDataList(resultList);
			pb.setTotalCount(eamLedgers.getTotalElements());
			return pb;
		}
		return null;
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#deleteEamLedger(java.lang.String)  
	*/
	@Override
	@Transactional
	public void deleteEamLedger(String key) {
		EamLedgerLast eLast=eamLedgerLastDao.findEamLedgerLastByRefKey(key, new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
		if (eLast!=null) {
			baseCommonService.deleteByKey(EamLedgerLast.class, eLast.getKey());
		}
		baseCommonService.deleteByKey(EamLedger.class, key);
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#removeRelatedFile(java.lang.String, java.lang.String)  
	*/
	@Override
	@Transactional
	public void removeRelatedFile(String deviceKey, String key) {
		EamLedger eamLedger=baseCommonService.findByKey(EamLedger.class, deviceKey);
		StringBuffer newKeys=new StringBuffer();
		if (eamLedger!=null) {
			if (StringUtils.isNotBlank(eamLedger.getFileId())) {
				String[] files=StringUtils.split(eamLedger.getFileId(), ",");
				for (int i = 0; i < files.length; i++) {
					if (StringUtils.equals(key, files[i])) {
						continue;
					}
					newKeys.append(files[i]).append(",");
				}
			}
			eamLedger.setFileId((newKeys.deleteCharAt(newKeys.length() - 1)).toString());
			baseCommonService.saveOrUpdate(eamLedger);
		}
	}
}
