package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.eam.eamPartLibraryManager.dao.PartsParamsDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;
import com.ehs.eam.eamPartLibraryManager.service.PartsParamsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsParamsServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月3日 上午11:39:49 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月3日     zhaol           v1.0.0               修改原因
*/
@Service
public class PartsParamsServiceImpl implements PartsParamsService {
	
	@Resource
	private PartsParamsDao partsParamsDao;

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsParamsService#getAllPartsParamByKey(java.lang.String)  
	* @Function: PartsParamsServiceImpl.java
	* @Description: 根据paramKey找所有数据
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年1月3日 上午11:39:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年1月3日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<PartsParam> getAllPartsParamByKey(String key) {
		// TODO Auto-generated method stub
		try {
			List<PartsParam> params =partsParamsDao.getAllPartsParamByKey(key);
			return params;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
