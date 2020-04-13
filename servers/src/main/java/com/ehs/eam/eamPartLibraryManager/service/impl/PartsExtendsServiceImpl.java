package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.EnterWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWarehouseQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtendsServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月18日 下午9:14:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月18日     zhaol          v1.0.0               修改原因
*/
@Service
public class PartsExtendsServiceImpl implements PartsExtendsService{
	
	private static final Logger logger = LoggerFactory.getLogger(PartsExtendsServiceImpl.class);
	
	@Resource
	private PartsExtendsDao partsExtendsDao;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService#getExtendsByKey(com.ehs.eam.eamPartLibraryManager.bean.QueryBean, java.lang.String)  
	* @Function: PartsExtendsServiceImpl.java
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getExtendsByKey(QueryBean queryBean, String key) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<PartsExtends> parts = partsExtendsDao.getExtendsByKey(key,pageRequest);
		if (parts!=null) {
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(parts.getContent());
			pb.setTotalCount(parts.getTotalElements());
			return pb;
		}
		return null;
	}
	
	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService#getAllEnterWareHouseParts(com.ehs.eam.eamPartLibraryManager.bean.EnterWarehouseQueryBean)  
	* @Function: PartsExtendsServiceImpl.java
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllEnterWareHouseParts(EnterWarehouseQueryBean queryBean) {
		logger.info("====查询所有入库备件------开始====");
		try {
			Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
			Page<PartsExtends> partsExtends = partsExtendsDao.getAllEnterWareHouseParts(queryBean.getQuery(),
					queryBean.getWareHouseNames(),
					queryBean.getInBoundTypes(),
					queryBean.getFlowstatus(),
					pb);
			if(partsExtends != null) {
				List<PartsExtends> list = new ArrayList();
				List<PartsExtends> parts =partsExtends.getContent();
				if(parts != null) {
					for (PartsExtends partsExtends2 : parts) {
						if(partsExtends2 != null) {
							FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(partsExtends2.getRefFlowKey());
							EnterWareHouse enterWareHouse = baseCommonService.findByKey(EnterWareHouse.class, partsExtends2.getRefFlowKey());
							if(fpi!=null) {
								switch (fpi.getFlowCurrentStep()) {
								case "END":
									partsExtends2.setReviewer(fpi.getFlowPrevPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(enterWareHouse.getWarehouseCode());
									partsExtends2.setWareHouseName(enterWareHouse.getWarehouseName());
									partsExtends2.setEnterOutType(enterWareHouse.getInboundType());
									partsExtends2.setEnterOutTypeName(enterWareHouse.getInboundTypeName());
									break;
								case "usertask2":
									partsExtends2.setReviewer(fpi.getFlowCurrentPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(enterWareHouse.getWarehouseCode());
									partsExtends2.setWareHouseName(enterWareHouse.getWarehouseName());
									partsExtends2.setEnterOutType(enterWareHouse.getInboundType());
									partsExtends2.setEnterOutTypeName(enterWareHouse.getInboundTypeName());
									break;
								case "usertask1":
									partsExtends2.setReviewer(fpi.getFlowCurrentPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(enterWareHouse.getWarehouseCode());
									partsExtends2.setWareHouseName(enterWareHouse.getWarehouseName());
									partsExtends2.setEnterOutType(enterWareHouse.getInboundType());
									partsExtends2.setEnterOutTypeName(enterWareHouse.getInboundTypeName());
									break;
								}
							}
						}
					}
				}
				list.addAll(parts);
				PageInfoBean pib=new PageInfoBean();
				pib.setDataList(list);
				pib.setTotalCount(partsExtends.getTotalElements());
				logger.info("====查询所有入库备件-------结束====");
				return pib;
			}
		} catch (Exception e) {
			logger.error("错误信息---------------------"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService#getAllOutWareHouseParts(com.ehs.eam.eamPartLibraryManager.bean.OutWarehouseQueryBean)  
	* @Function: PartsExtendsServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllOutWareHouseParts(OutWarehouseQueryBean queryBean) {
		logger.info("查询所有出库备件-----开始");
		try {
			Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
			Page<PartsExtends> partsExtends = partsExtendsDao.getAllOutWareHouseParts(
					queryBean.getQuery(),
					queryBean.getWarehouseNames(),
					queryBean.getOutBoundTypes(),
					queryBean.getFlowstatus(),
					pb);
			if(partsExtends != null) {
				List<PartsExtends> list = new ArrayList();
				List<PartsExtends> parts =partsExtends.getContent();
				if(parts != null) {
					for (PartsExtends partsExtends2 : parts) {
						if(partsExtends2 != null) {
							FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(partsExtends2.getRefFlowKey());
							OutWareHouse outWareHouse = baseCommonService.findByKey(OutWareHouse.class, partsExtends2.getRefFlowKey());
							if(fpi!=null) {
								switch (fpi.getFlowCurrentStep()) {
								case "END":
									partsExtends2.setReviewer(fpi.getFlowPrevPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(outWareHouse.getOutWarehouseCode());
									partsExtends2.setWareHouseName(outWareHouse.getOutWarehouseName());
									partsExtends2.setEnterOutType(outWareHouse.getOutBoundType());
									partsExtends2.setEnterOutTypeName(outWareHouse.getOutBoundTypeName());
									break;
								case "usertask2":
									partsExtends2.setReviewer(fpi.getFlowCurrentPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(outWareHouse.getOutWarehouseCode());
									partsExtends2.setWareHouseName(outWareHouse.getOutWarehouseName());
									partsExtends2.setEnterOutType(outWareHouse.getOutBoundType());
									partsExtends2.setEnterOutTypeName(outWareHouse.getOutBoundTypeName());
									break;
								case "usertask1":
									partsExtends2.setReviewer(fpi.getFlowCurrentPersonName());
									partsExtends2.setStatus(fpi.getFlowCurrentStep());
									partsExtends2.setStatusName(fpi.getFlowCurrentStepName());
									partsExtends2.setWareHouseCode(outWareHouse.getOutWarehouseCode());
									partsExtends2.setWareHouseName(outWareHouse.getOutWarehouseName());
									partsExtends2.setEnterOutType(outWareHouse.getOutBoundType());
									partsExtends2.setEnterOutTypeName(outWareHouse.getOutBoundTypeName());
									break;
								}
							}
							list.add(partsExtends2);
						}
					}
				}
				PageInfoBean pib=new PageInfoBean();
				pib.setDataList(list);
				pib.setTotalCount(list.size());
				logger.info("查询所有出库备件-----结束");
				return pib;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<PartsExtends> findByWareHouseKey(String wareHoseKey) {
		List<PartsExtends> partsExtends = partsExtendsDao.getAllByWareHouseKey(wareHoseKey);
		if(!CollectionUtils.isEmpty(partsExtends)) {
			return partsExtends;
		}
		return null;
	}

}
