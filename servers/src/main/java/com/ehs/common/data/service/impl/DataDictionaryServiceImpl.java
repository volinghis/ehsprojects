package com.ehs.common.data.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.dao.DataDictionaryDao;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.service.DataDictionaryService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.OrgQueryBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月6日 下午3:34:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月6日     zhaol           v1.0.0               修改原因
*/
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService{
	
	@Resource
	private DataDictionaryDao dataDictionaryDao;
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.common.data.service.DataDictionaryService#getFirstNode()  
	* @Function: DataDictionaryServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午3:34:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<DataDictionary> getFirstNode(String parentCode) {
		try {
			return dataDictionaryDao.getFirstNode(parentCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.data.service.DataDictionaryService#getAllDatasTable(java.lang.String, com.ehs.common.organization.bean.OrgQueryBean)  
	* @Function: DataDictionaryServiceImpl.java
	* @Description: 该函数的功能描述
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午3:34:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllDatasTable(String parentCode, OrgQueryBean queryBean) {
		PageRequest pageRequest =PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		PageInfoBean pb=new PageInfoBean();
		if(StringUtils.isNotBlank(parentCode)) {
			Page<DataDictionary> datas=dataDictionaryDao.findDatasByParentCode(parentCode,queryBean.getQuery(), pageRequest);
			if (datas!=null) {
				pb.setDataList(datas.getContent());
				pb.setTotalCount(datas.getTotalElements());
				return pb;
			}
			return null;
		}
		Page<DataDictionary> datas=dataDictionaryDao.findAllDatas(queryBean.getQuery(), pageRequest);
		List<DataDictionary> dataDicts = datas.stream().filter(s -> !StringUtils.equals(s.getParentKey(), "dataDict")).collect(Collectors.toList());
		if (dataDicts!=null) {
			pb.setDataList(dataDicts);
			pb.setTotalCount(dataDicts.size());
			return pb;
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.data.service.DataDictionaryService#saveDataDictionary(com.ehs.common.data.entity.DataDictionary)  
	* @Function: DataDictionaryServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年3月6日 下午4:27:10 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年3月6日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveDataDictionary(DataDictionary data) {
		try {
			baseCommonService.saveOrUpdate(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteOrgByKey(String key) {
		try {
			baseCommonService.deleteByKey(DataDictionary.class, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
