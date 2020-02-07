package com.ehs.common.data.service;

import java.util.List;

import com.ehs.common.data.entity.DataFileInfo;

public interface DataFileInfoService {

	public List<DataFileInfo> findDataFilesByFileIds(String[] fileIds);
}
