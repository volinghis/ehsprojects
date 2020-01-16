package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
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

	@Override
	public PageInfoBean findAll(QueryBean queryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<EnterWareHouse> parts = ewhDao.findAll(pageRequest);
		if (parts!=null) {
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(parts.getContent());
			pb.setTotalCount(parts.getTotalElements());
			return pb;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveEnterWareHouse(EnterWareHouserBean wareHouserBean) {
		// TODO Auto-generated method stub
		if (wareHouserBean.getEnterWareHouse() != null) {
			EnterWareHouse eHouse = baseCommonService.saveOrUpdate(wareHouserBean.getEnterWareHouse());
			List<PartsExtends> partsExtends =wareHouserBean.getPartsExtends();
			if (!CollectionUtils.isEmpty(partsExtends)) {
				for (PartsExtends partsExtend : partsExtends) {
					List<PartsExtends> partsExtendsAll = (List<PartsExtends>) baseCommonService.findAll(PartsExtends.class)
							.stream().filter(s->StringUtils.equals(((BaseEntity) s).getKey(), partsExtend.getKey())).collect(Collectors.toList());
					if(!CollectionUtils.isEmpty(partsExtendsAll)) {
						for (PartsExtends peAll : partsExtendsAll) {
							//修改
							System.out.println("======进行修改=======");
							PartsExtends ppExtends = baseCommonService.findByKey(PartsExtends.class, peAll.getKey());
							System.out.println("amount========="+ppExtends.getAmount());
							Integer newAmount = ppExtends.getAmount();
							ppExtends.setAmount(partsExtend.getAmount());
							ppExtends.setTotalPrice(partsExtend.getTotalPrice());
							System.out.println("======更新完成，备件扩展表========");
							List<PartsAccount> pAccount = partsAccountDao.findByDeviceCode(peAll.getDeviceCode());
							if(!CollectionUtils.isEmpty(pAccount)){
								for (PartsAccount partsAccount : pAccount) {
									PartsAccount pa = baseCommonService.findByKey(PartsAccount.class, partsAccount.getKey());
									if(pa.getPrice().compareTo(peAll.getPrice()) == 0) {
										pa.setAmount(new Integer(pa.getAmount().intValue() + peAll.getAmount().intValue() - newAmount.intValue()));
										System.out.println("总数量为========="+pa.getAmount());
									}else {
										System.out.println("编码相同，价格不同的时候");
										savePartAccount(eHouse, partsExtend);
									}
								}
							}
						}
					}else {
						System.out.println("=====准备新增=====");
						PartsExtends pExtends = savePartsExtends(partsExtend,eHouse);
						List<PartsAccount> pAccount = partsAccountDao.findByDeviceCode(pExtends.getDeviceCode());
						if(!CollectionUtils.isEmpty(pAccount)){
							for (PartsAccount partsAccount : pAccount) {
								PartsAccount pa = baseCommonService.findByKey(PartsAccount.class, partsAccount.getKey());
								if(pa.getPrice().compareTo(pExtends.getPrice()) == 0) {
									pa.setAmount(pa.getAmount() + pExtends.getAmount());
									System.out.println("总数量为========="+pa.getAmount());
								}else {
									System.out.println("编码相同，价格不同的时候");
									savePartAccount(eHouse, partsExtend);
								}
							}
						}
						else {
							System.out.println("编码不同价格也不相同");
							savePartAccount(eHouse, partsExtend);
						}
					}
				}
			}
		}
	}
	
	public void savePartAccount(EnterWareHouse eHouse, PartsExtends pExtends) {
		try {
			PartsAccount account = new PartsAccount();
			//仓库信息存入备件台账表
			account.setWarehouseCode(eHouse.getWarehouseCode());
			account.setWarehouseName(eHouse.getWarehouseName());
			account.setInboundType(eHouse.getInboundType());
			account.setInboundDate(eHouse.getInboundDate());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PartsExtends savePartsExtends(PartsExtends partsExtend,EnterWareHouse eHouse) {
		try {
			PartsExtends pe = new PartsExtends();
			pe.setWareHouseKey(eHouse.getKey());
			pe.setDeviceCode(partsExtend.getDeviceCode());
			pe.setDeviceName(partsExtend.getDeviceName());
			pe.setNorm(partsExtend.getNorm());
			pe.setLeaveFactoryCode(partsExtend.getLeaveFactoryCode());
			pe.setLeaveFactoryDate(partsExtend.getLeaveFactoryDate());
			pe.setSupplier(partsExtend.getSupplier());
			pe.setAmount(partsExtend.getAmount());
			pe.setPrice(partsExtend.getPrice());
			pe.setUnit(partsExtend.getUnit());
			pe.setTotalPrice(partsExtend.getTotalPrice());
			PartsExtends pExtends = baseCommonService.saveOrUpdate(pe);
			return pExtends;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
