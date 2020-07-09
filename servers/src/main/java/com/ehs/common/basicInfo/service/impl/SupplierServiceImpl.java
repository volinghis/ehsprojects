package com.ehs.common.basicInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.basicInfo.bean.SupplierBean;
import com.ehs.common.basicInfo.bean.SupplierQueryBean;
import com.ehs.common.basicInfo.dao.ContactInfoDao;
import com.ehs.common.basicInfo.dao.SupplierDao;
import com.ehs.common.basicInfo.entity.ContactInfo;
import com.ehs.common.basicInfo.entity.SupplierInfo;
import com.ehs.common.basicInfo.service.SupplierService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月9日 上午11:09:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月9日     zhaol           v1.0.0               修改原因
*/
@Service
public class SupplierServiceImpl implements SupplierService{

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private SupplierDao supplierDao;
	
	@Resource
	private ContactInfoDao contactInfoDao;

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.SupplierService#saveSupplier(com.ehs.common.basicInfo.bean.SupplierBean)  
	* @Function: SupplierServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:09:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveSupplier(SupplierBean supplierBean) {
		logger.info("============准备开始保存供应商信息=========");
		if(supplierBean.getSupplier() != null) {
			SupplierInfo supplierInfo = supplierBean.getSupplier();
			DataDictionary supplierType = baseCommonService.findByKey(DataDictionary.class,supplierInfo.getSupplierType());
			if (supplierType != null) {
				supplierInfo.setSupplierTypeName(supplierType == null ? "" : supplierType.getText());
			}
			supplierInfo.setState(0);
			baseCommonService.saveOrUpdate(supplierInfo);
			List<ContactInfo> contactInfos = supplierBean.getContactInfos();
			if(!CollectionUtils.isEmpty(contactInfos)) {
				for (ContactInfo contactInfo : contactInfos) {
					contactInfo.setContartCode(supplierInfo.getKey());
					baseCommonService.saveOrUpdate(contactInfo);
				}
			}
		}
		
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.SupplierService#findSuppliers(com.ehs.common.basicInfo.bean.SupplierQueryBean)  
	* @Function: SupplierServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:09:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findSuppliers(SupplierQueryBean supplierQueryBean) {
		PageRequest pageRequest = PageRequest.of(supplierQueryBean.getPage() - 1, supplierQueryBean.getSize());
		Page<SupplierInfo> suppliers = supplierDao.findSuppliers(supplierQueryBean.getQuery(), pageRequest);
		if (suppliers != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(suppliers.getContent());
			pb.setTotalCount(suppliers.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.SupplierService#deleteSupplierByKey(java.lang.String)  
	* @Function: SupplierServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:09:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteSupplierByKey(String key) {
		try {
			if(key != null) {
				baseCommonService.deleteByKey(SupplierInfo.class, key);
				List<ContactInfo> contactInfos = contactInfoDao.findByCode(key);
				if(!CollectionUtils.isEmpty(contactInfos)) {
					for (ContactInfo contactInfo : contactInfos) {
						baseCommonService.findByKey(ContactInfo.class, contactInfo.getKey());
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.SupplierService#changeState(com.ehs.common.basicInfo.entity.SupplierInfo)  
	* @Function: SupplierServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:09:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public SupplierInfo changeState(SupplierInfo supplierInfo) {
		SupplierInfo s= baseCommonService.findByKey(SupplierInfo.class, supplierInfo.getKey());
		s.setState(supplierInfo.getState()== 0 ? 0 : 1);
		return baseCommonService.saveOrUpdate(s);
	}

}
