package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.eam.eamPartLibraryManager.bean.WareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.dao.EnterWareHouseDao;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.EnterWareHouseService;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;

@Service
public class EnterWareHouseServiceImpl implements EnterWareHouseService {
	private static final Logger logger = LoggerFactory.getLogger(EnterWareHouseServiceImpl.class);
	
	@Resource
	private EnterWareHouseDao ewhDao;
	
	@Resource
	private PartsAccountDao partsAccountDao;
	
	@Resource
	private PartsExtendsDao partsExtendsDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private PartsAccountService accountService;

	@Resource
	private FlowBaseService flowBaseService; 
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Override
	@Transactional
	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean) {
		logger.info("============准备开始入库流程==========");
		if(wareHouserBean.getEnterWareHouse() != null) {
			EnterWareHouse eHouse = wareHouserBean.getEnterWareHouse();
			DataDictionary dataDictionary = baseCommonService.findByKey(DataDictionary.class,eHouse.getWarehouse());
			if (dataDictionary != null) {
				eHouse.setWarehouseName(dataDictionary == null ? "" : dataDictionary.getText());
			}
			DataDictionary dd = baseCommonService.findByKey(DataDictionary.class,eHouse.getInboundType());
			if (dd != null) {
				eHouse.setInboundTypeName(dd == null ? "" : dd.getText());
			}
			ProcessInstance pi = flowBaseService.startProcess(wareHouserBean.getEnterWareHouse(), wareHouserBean.getFlowProcessInfo());
			EnterWareHouse eWareHouse =baseCommonService.findByKey(EnterWareHouse.class, pi.getBusinessKey());
			if(!CollectionUtils.isEmpty(wareHouserBean.getPartsExtends())) {
				for (PartsExtends partsExtends : wareHouserBean.getPartsExtends()) {
					partsExtends.setRefFlowKey(pi.getBusinessKey());
					partsExtends.setRefWareHouseKey(eWareHouse.getWarehouse());
					partsExtends.setWareHouseCode(eWareHouse.getWarehouseCode());
					partsExtends.setWareHouse(eWareHouse.getWarehouse());
					partsExtends.setWareHouseName(eWareHouse.getWarehouseName());
					baseCommonService.saveOrUpdate(partsExtends);
					logger.info("保存完成");
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void updatePartsAccount(FlowProcessInfo flowProcessInfo) {
		logger.info("========流程结束---开始回调=======");
		logger.info("==========开始更新备件台账数据=============");
		List<PartsExtends> partsExtends = partsExtendsDao.getAllByWareHouseKey(flowProcessInfo.getBusinessEntityKey());
		if(!CollectionUtils.isEmpty(partsExtends)) {
			for (PartsExtends pExtends : partsExtends) {
				List<PartsAccount> pAccounts = partsAccountDao.findByDeviceCode(pExtends.getDeviceCode());
				if(!CollectionUtils.isEmpty(pAccounts)){
					for (PartsAccount partsAccount : pAccounts) {
						PartsAccount pa = baseCommonService.findByKey(PartsAccount.class, partsAccount.getKey());
						if(pa.getPrice().compareTo(pExtends.getPrice()) == 0) {
							//相同编号下相同价格
							logger.info("编码相同，价格相同的时候");
							pa.setAmount(new Integer(pa.getAmount().intValue() + pExtends.getAmount().intValue()));
							pa.setDummyAmount(pa.getAmount());
							logger.info("总数量为========="+pa.getAmount());
							logger.info("虚拟总数量为========="+pa.getDummyAmount());
							baseCommonService.saveOrUpdate(pa);
						}else {
							logger.info("编码相同，价格不同的时候");
							savePartAccount(pExtends);
						}
					}
				}else {
					logger.info("=====台账为空，第一次保存台账数据======");
					savePartAccount( pExtends);
				}
			}
		}
		logger.info("========流程结束---完成回调=======");
	}
	public void savePartAccount(PartsExtends parts) {
		logger.info("========保存备件台账--开始=========");
		PartsAccount partsAccount = new PartsAccount();
		BeanUtils.copyProperties(parts, partsAccount);
		partsAccount.setDummyAmount(parts.getAmount());
		baseCommonService.saveOrUpdate(partsAccount);
		logger.info("========保存备件台账---完成=========");
	}

	@Override
	public WareHouseFlowBean getEnterWareHouseFlowBean(String key) {
		EnterWareHouse ewh = baseCommonService.findByKey(EnterWareHouse.class, key);
		WareHouseFlowBean ewhFlowBean = new WareHouseFlowBean();
		if (ewhFlowBean != null) {
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(ewh.getKey());
			if (fpi != null) {
				ewhFlowBean.setCurrentStep(fpi.getFlowCurrentStep());
				ewhFlowBean.setCurrentUser(ewh.getOwnerName());
				ewhFlowBean.setEditPage(fpi.getFlowEditPage());
				ewhFlowBean.setViewPage(fpi.getFlowViewPage());
				ewhFlowBean.setInstanceId(fpi.getFlowProcessInstanceId());
				ewhFlowBean.setStartActivityId(fpi.getFlowStartActivityId());
				String currentStep=fpi.getFlowCurrentStep();
				if(StringUtils.equals(currentStep, fpi.getFlowStartActivityId())) {
					ewhFlowBean.setProcessPage(fpi.getFlowEditPage());
				}else {
					ewhFlowBean.setProcessPage(fpi.getFlowViewPage());
				}
			}
		}
		return ewhFlowBean;
	}

}
