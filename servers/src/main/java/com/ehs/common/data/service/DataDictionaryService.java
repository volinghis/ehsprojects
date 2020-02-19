package com.ehs.common.data.service;

import java.util.List;

import com.ehs.common.data.entity.DataDictionary;

public interface DataDictionaryService {

	List<DataDictionary> findDataDictByParentKey(String key);

	
}
