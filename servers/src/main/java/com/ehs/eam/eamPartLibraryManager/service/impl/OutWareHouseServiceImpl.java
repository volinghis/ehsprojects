package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.math.BigDecimal;
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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.OutWareHouseDao;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.OutWareHouseService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWareHouseServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月18日 下午9:13:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月18日     zhaol          v1.0.0               修改原因
*/
@Service
public class OutWareHouseServiceImpl implements OutWareHouseService {
	
	public static final Logger logger = LoggerFactory.getLogger(OutWareHouseServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private PartsAccountDao partsAccountDao;
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private OutWareHouseDao owhDao;
	
	@Resource
	private PartsExtendsDao partsExtendsDao;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Override
	public PageInfoBean findAll(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<OutWareHouse> outWareHouses = owhDao.findAll(pageRequest);
		if (outWareHouses!=null) {
			List<OutWareHouse> outWareHouseList  = outWareHouses.getContent();
			for (OutWareHouse owh : outWareHouseList) {
				FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(owh.getKey());
				if(fpi!=null) {
					owh.setStatus(fpi.getFlowCurrentStepName());
				}
			}
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(outWareHouseList);
			pb.setTotalCount(outWareHouses.getTotalElements());
			return pb;
		}
		return null;
	}
	
	@Override
	@Transactional
	public void saveOutWareHouse(OutWareHouserBean wareHouserBean) {
		logger.info("=======准备开始出库流程========");
		if (wareHouserBean.getOutWareHouse() != null) {
			OrgUser user = baseCommonService.findByKey(OrgUser.class, wareHouserBean.getOutWareHouse().getReceiveEmpCode());
			OrganizationInfo org = baseCommonService.findByKey(OrganizationInfo.class, wareHouserBean.getOutWareHouse().getReceiveDepartCode());
			wareHouserBean.getOutWareHouse().setReceiveDepart(org.getName());
			wareHouserBean.getOutWareHouse().setReceiveEmp(user.getName());
			System.out.println("wareHouserBean.getOutWareHouse()========="+wareHouserBean.getOutWareHouse().getKey());
			System.out.println("wareHouserBean.getFlowProcessInfo()======="+wareHouserBean.getFlowProcessInfo().getBusinessEntityKey());
			System.out.println("bussinessKey========"+wareHouserBean.getFlowProcessInfo().getBusinessEntityKey());
			//流程驳回后再次提交
			if(StringUtils.isNotBlank(wareHouserBean.getOutWareHouse().getKey()) && StringUtils.isNotBlank(wareHouserBean.getFlowProcessInfo().getBusinessEntityKey())
					&& StringUtils.equals(wareHouserBean.getOutWareHouse().getKey(), wareHouserBean.getFlowProcessInfo().getBusinessEntityKey())){
				logger.info("============驳回以后===========");
				ProcessInstance pi = flowBaseService.startProcess(wareHouserBean.getOutWareHouse(), wareHouserBean.getFlowProcessInfo());
				if(!CollectionUtils.isEmpty(wareHouserBean.getPartsExtends())) {
					for (PartsExtends partsExtends : wareHouserBean.getPartsExtends()) {
						partsExtends.setWareHouseKey(pi.getBusinessKey());
						logger.info("旧的=====partsExtends.getKey()=========="+partsExtends.getKey());
						PartsExtends oldExtends = baseCommonService.findByKey(partsExtends.getClass(), partsExtends.getKey());
						PartsExtends newExtends = baseCommonService.saveOrUpdate(partsExtends);
						logger.info("新的=====partsExtends.getKey()=========="+partsExtends.getKey());
						List<PartsAccount> pAccounts = partsAccountDao.findByDeviceCode(newExtends.getDeviceCode());
						if (!CollectionUtils.isEmpty(pAccounts)) {
							for (PartsAccount partsAccount : pAccounts) {
								if (partsAccount != null) {
									if(partsAccount.getPrice().compareTo(newExtends.getPrice()) == 0) {
										logger.info("编码相同，价格相同的时候");
										partsAccount.setDummyAmount(partsAccount.getDummyAmount().intValue() + oldExtends.getAmount().intValue());
										logger.info("驳回后虚拟数量为============="+partsAccount.getDummyAmount());
										if(partsAccount.getDummyAmount().intValue() == 0) {
											partsAccount.setDummyAmount(partsAccount.getAmount().intValue() - newExtends.getAmount().intValue());
										}else {
											partsAccount.setDummyAmount(partsAccount.getDummyAmount().intValue() - newExtends.getAmount().intValue());
										}
										logger.info("真实数量为================"+partsAccount.getAmount());
										logger.info("出库以后的虚拟数量为================"+partsAccount.getDummyAmount());
										baseCommonService.saveOrUpdate(partsAccount);
									}
								}
							}
						}
					}
				}
			}else {
				logger.info("===============新增=========================");
				ProcessInstance pi = flowBaseService.startProcess(wareHouserBean.getOutWareHouse(), wareHouserBean.getFlowProcessInfo());
				if (!CollectionUtils.isEmpty(wareHouserBean.getPartsExtends())) {
					for (PartsExtends partsExtends : wareHouserBean.getPartsExtends()) {
						partsExtends.setWareHouseKey(pi.getBusinessKey());
						logger.info("准备保存备件信息");
						PartsExtends pp = baseCommonService.saveOrUpdate(partsExtends);
						List<PartsAccount> pAccounts = partsAccountDao.findByDeviceCode(pp.getDeviceCode());
						if (!CollectionUtils.isEmpty(pAccounts)) {
							for (PartsAccount partsAccount : pAccounts) {
								if (partsAccount != null) {
									if(partsAccount.getPrice().compareTo(pp.getPrice()) == 0) {
										logger.info("编码相同，价格相同的时候");
										logger.info("partsAccount.getDummyAmount()====="+partsAccount.getDummyAmount());
										logger.info("lkdsjfls==="+StringUtils.isNotBlank(String.valueOf(partsAccount.getDummyAmount())));
										if(StringUtils.isNotBlank(String.valueOf(partsAccount.getDummyAmount()))) {
											logger.info("aksjdajldjalkjldjaklldajdlajd");
											partsAccount.setDummyAmount(partsAccount.getAmount().intValue() - pp.getAmount().intValue());
											logger.info("hhhh==="+partsAccount.getDummyAmount());
										}else {
											logger.info("jjjjjjjj======"+String.valueOf(partsAccount.getDummyAmount()));
											partsAccount.setDummyAmount(partsAccount.getDummyAmount().intValue() - pp.getAmount().intValue());
										}
										logger.info("真实数量为================"+partsAccount.getAmount());
										logger.info("出库以后的虚拟数量为================"+partsAccount.getDummyAmount());
										baseCommonService.saveOrUpdate(partsAccount);
									}
								}
							}
						}
						logger.info("保存完成");
					}
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void updatePartsAccount(FlowProcessInfo flowProcessInfo) {
		logger.info("========出库流程结束开始回调=======");
		String status="已完成";
		OutWareHouse owh = baseCommonService.findByKey(OutWareHouse.class, flowProcessInfo.getBusinessEntityKey());
		if(owh != null) {
			logger.info("修改流程状态为‘已完成’");
			owh.setStatus(status);
			baseCommonService.saveOrUpdate(owh);
		}
		logger.info("==========开始更新备件台账数据=============");
		List<PartsExtends> partsExtends = partsExtendsDao.getAllByWareHouseKey(flowProcessInfo.getBusinessEntityKey());
		if(!CollectionUtils.isEmpty(partsExtends)) {
			for (PartsExtends pExtends : partsExtends) {
				List<PartsAccount> pAccounts = partsAccountDao.findByDeviceCode(pExtends.getDeviceCode());
				if (CollectionUtils.isEmpty(pAccounts)) {
					for (PartsAccount partsAccount : pAccounts) {
						PartsAccount pa = baseCommonService.findByKey(PartsAccount.class, partsAccount.getKey());
						if(pa.getPrice().compareTo(pExtends.getPrice()) == 0) {
							logger.info("编码相同，价格相同的时候");
							pa.setAmount(new Integer(pa.getAmount().intValue() - pExtends.getAmount().intValue()));
							logger.info("出库以后的数量为========="+pa.getAmount());
							baseCommonService.saveOrUpdate(pa);
							logger.info("====出库流程回调结束======");
						}
					}
				}
			}
		}
	}
	
	public void savePartAccount(OutWareHouse eHouse, PartsExtends pExtends) {
		try {
			PartsAccount account = new PartsAccount();
			//仓库信息存入备件台账表
			account.setWareHouseCode(eHouse.getOutWarehouseCode());
			account.setWareHouseName(eHouse.getOutWarehouseName());
			account.setInboundType(eHouse.getOutBoundType());
			account.setInboundDate(eHouse.getOutBoundDate());
			//备件扩展表存入备件台账
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
	public int validAmount(String amount, String deviceCode, String price) {
		Assert.notNull(amount, "数量不能为空");
		Assert.notNull(deviceCode, "备件编码不能为空");
		Assert.notNull(price, "价格不能为空");
		BigDecimal newPrice = new BigDecimal(price);
		PartsAccount partsAccount = partsAccountDao.findPartsAccount(deviceCode,newPrice);
		int totalAmount = partsAccount.getAmount().intValue() + Integer.valueOf(amount).intValue();
		System.out.println("totalAmount====="+totalAmount);
		return totalAmount;
	}

	@Override
	public EnterWareHouseFlowBean getOutWareHouseFlowBean(String key) {
		OutWareHouse outWareHouse = baseCommonService.findByKey(OutWareHouse.class, key);
		EnterWareHouseFlowBean ewhFlowBean = new EnterWareHouseFlowBean();
		if (ewhFlowBean != null) {
			FlowProcessInfo fpi = flowProcessInfoService.findProcessInfoByEntityKey(outWareHouse.getKey());
			if (fpi != null) {
				ewhFlowBean.setCurrentStep(fpi.getFlowCurrentStepName());
				ewhFlowBean.setCurrentUser(outWareHouse.getOwnerName());
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
	public OutWareHouse getOutWareHouseByKey(String key) {
		OutWareHouse outWareHouse = baseCommonService.findByKey(OutWareHouse.class, key);
		if (outWareHouse != null) {
			return outWareHouse;
		}
		return null;
	}

}
