package com.ehs.eam.eamPartLibraryManager.service;

import java.util.List;

import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;

public interface PartsParamsService {
	
	public List<PartsParam> getAllPartsParamByKey(String key);
}
