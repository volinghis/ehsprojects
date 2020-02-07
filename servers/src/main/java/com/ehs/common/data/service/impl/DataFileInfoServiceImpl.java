package com.ehs.common.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.data.dao.DataFileInfoDao;
import com.ehs.common.data.entity.DataFileInfo;
import com.ehs.common.data.service.DataFileInfoService;

@Service
public class DataFileInfoServiceImpl implements DataFileInfoService {

	@Resource
	private DataFileInfoDao dataFileInfoDao;
	
	@Override
	public List<DataFileInfo> findDataFilesByFileIds(String[] fileIds) {
		return dataFileInfoDao.find(fileIds, new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
	}

}
