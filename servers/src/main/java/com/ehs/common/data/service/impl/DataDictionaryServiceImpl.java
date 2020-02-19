package com.ehs.common.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.data.dao.DataDictionaryDao;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.service.DataDictionaryService;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Resource
	private DataDictionaryDao dataDictDao;
	
	@Override
	public List<DataDictionary> findDataDictByParentKey(String key) {
		return dataDictDao.findDataDictByParentKey(key, new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
	}

}
