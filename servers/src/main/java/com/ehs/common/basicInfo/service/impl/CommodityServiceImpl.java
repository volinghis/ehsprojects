package com.ehs.common.basicInfo.service.impl;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.basicInfo.bean.CommodityQueryBean;
import com.ehs.common.basicInfo.dao.CommodityDao;
import com.ehs.common.basicInfo.service.CommodityService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: CommodityServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 下午3:41:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@Service
public class CommodityServiceImpl implements CommodityService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private CommodityDao commodityDao;	
	
	@Override
	@Transactional
	public void saveCommodity(PartsAccount partsAccount) {
		try {
			if(partsAccount != null) {
				DataDictionary dataDictionary = baseCommonService.findByKey(DataDictionary.class,partsAccount.getWareHouse());
				if (dataDictionary != null) {
					partsAccount.setWareHouseName(dataDictionary == null ? "" : dataDictionary.getText());
				}
				baseCommonService.saveOrUpdate(partsAccount);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public PageInfoBean findCommodities(CommodityQueryBean commodityQueryBean) {
		PageRequest pageRequest = PageRequest.of(commodityQueryBean.getPage() - 1, commodityQueryBean.getSize());
		Page<PartsAccount> clients = commodityDao.findCommodities(commodityQueryBean.getQuery(), pageRequest);
		if (clients != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(clients.getContent());
			pb.setTotalCount(clients.getTotalElements());
			return pb;
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteCommodity(String key) {
		try {
			if(key != null) {
				baseCommonService.deleteByKey(PartsAccount.class, key);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
