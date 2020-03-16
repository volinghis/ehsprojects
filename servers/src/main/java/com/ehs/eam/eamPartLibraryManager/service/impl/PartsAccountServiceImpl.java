package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;
import com.ehs.eam.eamPartLibraryManager.service.PartsParamsService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午3:58:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/

@Service
public class PartsAccountServiceImpl implements PartsAccountService{

	@Resource
	private PartsAccountDao partsAccountDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private PartsParamsService partsParamsService;

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#saveOrUpdateEamPart(com.ehs.eam.eamPartLibraryManager.entity.PartsAccount)  
	* @Function: EamPartLibraryServiceImpl.java
	* @Description: 保存备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午4:09:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveOrUpdateEamPart(PartsInfoBean partsBean) {
		// TODO Auto-generated method stub
//		if (partsBean.getPartsAccounts() != null) {
//			PartsAccount partsAccount =baseCommonService.saveOrUpdate(partsBean.getPartsAccounts());
//			String key = partsAccount.getKey();
//			List<PartsParam> params =partsBean.getPartsParams();
//			if (params != null && params.size() > 0) {
//				for (PartsParam partsParam : params) {
//					partsParam.setParamKey(key);
//					baseCommonService.saveOrUpdate(partsParam);
//				}
//			}
//		}
	}

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#findEamPartLibraryAll(com.ehs.eam.eamPartLibraryManager.bean.QueryBean)  
	* @Function: EamPartLibraryServiceImpl.java
	* @Description: 查询所有备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午4:09:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findPartsAccountAll(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<PartsAccount> parts = partsAccountDao.findPartsAccountAll(pageRequest,new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
		if (parts!=null) {
			if(StringUtils.isNotBlank(queryBean.getQuery()) ) {
				List<PartsAccount> partsAccounts =parts.getContent().stream().filter(s -> StringUtils.contains(s.getDeviceCode(), queryBean.getQuery()) 
						|| StringUtils.contains(s.getDeviceName(), queryBean.getQuery())).collect(Collectors.toList());
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(partsAccounts);
				pb.setTotalCount(partsAccounts.size());
				return pb;
			}else {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(parts.getContent());
				pb.setTotalCount(parts.getTotalElements());
				return pb;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public void deletePartsByKey(String key) {
		try {
			baseCommonService.deleteByKey(PartsAccount.class, key);
			List<PartsParam> params =partsParamsService.getAllPartsParamByKey(key);
			if (params != null && params.size() > 0) {
				for (PartsParam partsParam : params) {
					baseCommonService.deleteByKey(PartsParam.class, partsParam.getKey());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PartsAccount findPartsAccountByKey(String key) {
		if (StringUtils.isNotBlank(key)) {
			return baseCommonService.findByKey(PartsAccount.class, key);
		}
		return null;
	}

}
