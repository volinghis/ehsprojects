package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
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
	public PageInfoBean findAll(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<EnterWareHouse> enterWareHouses = ewhDao.findAll(pageRequest);
		if (enterWareHouses!=null) {
			List<EnterWareHouse> enterWareHouseList  = enterWareHouses.getContent();
			for (EnterWareHouse ew : enterWareHouseList) {
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(ew.getKey());
				if(fpi!=null) {
					ew.setStatus(fpi.getFlowCurrentStepName());
				}
			}
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(enterWareHouseList);
			pb.setTotalCount(enterWareHouses.getTotalElements());
			return pb;
		}
		return null;
	}
	
	@Override
	@Transactional
	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean) {
		logger.info("============准备开始入库流程==========");
		if(wareHouserBean.getEnterWareHouse() != null) {
			ProcessInstance pi = flowBaseService.startProcess(wareHouserBean.getEnterWareHouse(), wareHouserBean.getFlowProcessInfo());
			if(!CollectionUtils.isEmpty(wareHouserBean.getPartsExtends())) {
				logger.info("备件信息不为空");
				for (PartsExtends partsExtends : wareHouserBean.getPartsExtends()) {
					partsExtends.setWareHouseKey(pi.getBusinessKey());
					logger.info("准备保存备件信息");
					baseCommonService.saveOrUpdate(partsExtends);
					logger.info("保存完成");
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void updatePartsAccount(FlowProcessInfo flowProcessInfo) {
		logger.info("========流程结束开始回调=======");
		String status="已完成";
		EnterWareHouse ewh = baseCommonService.findByKey(EnterWareHouse.class, flowProcessInfo.getBusinessEntityKey());
		if(ewh != null) {
			logger.info("修改流程状态为‘已完成’");
			ewh.setStatus(status);
			baseCommonService.saveOrUpdate(ewh);
		}
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
							logger.info("总数量为========="+pa.getAmount());
							baseCommonService.saveOrUpdate(pa);
						}else {
							logger.info("编码相同，价格不同的时候");
							savePartAccount(ewh, pExtends);
						}
					}
				}else {
					logger.info("=====台账为空，第一次保存台账数据======");
					savePartAccount(ewh, pExtends);
				}
			}
		}
	}

	public void savePartAccount(EnterWareHouse eHouse, PartsExtends pExtends) {
		try {
			PartsAccount account = new PartsAccount();
			//仓库信息存入备件台账表
			account.setWareHouseCode(eHouse.getWarehouseCode());
			account.setWareHouseName(eHouse.getWarehouseName());
			account.setInboundType(eHouse.getInboundType());
			account.setInboundDate(eHouse.getInboundDate());
			//备件扩展表存入备件台账
			account.setMaintenancesStandard(pExtends.getMaintenancesStandard());
			account.setSynopsis(pExtends.getSynopsis());
			account.setOperationManual(pExtends.getOperationManual());
			account.setDeviceCode(pExtends.getDeviceCode());
			account.setDeviceName(pExtends.getDeviceName());
			account.setNorm(pExtends.getNorm());
			account.setMaterialCode(pExtends.getMaterialCode());
			account.setMaterialType(pExtends.getMaterialType());
			account.setWarningValue(pExtends.getWarningValue());
			account.setManufacturer(pExtends.getManufacturer());
			account.setLeaveFactoryCode(pExtends.getLeaveFactoryCode());
			account.setLeaveFactoryDate(pExtends.getLeaveFactoryDate());
			account.setSupplier(pExtends.getSupplier());
			account.setUnit(pExtends.getUnit());
			account.setPrice(pExtends.getPrice());
			account.setAmount(pExtends.getAmount());
			account.setTotalPrice(pExtends.getTotalPrice());
			baseCommonService.saveOrUpdate(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public EnterWareHouseFlowBean getEnterWareHouseFlowBean(String key) {
		EnterWareHouse ewh = baseCommonService.findByKey(EnterWareHouse.class, key);
		EnterWareHouseFlowBean ewhFlowBean = new EnterWareHouseFlowBean();
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

	@Override
	public EnterWareHouse getEnterWareHouseByKey(String key) {
		EnterWareHouse  enterWareHouse=baseCommonService.findByKey(EnterWareHouse.class, key);
		if (enterWareHouse != null) {
			return enterWareHouse;
		}
		return null;
	}
		
}
