package com.ehs.eam.checks.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.eam.checks.dao.EamCheckDefectDao;
import com.ehs.eam.checks.entity.EamCheckDefect;
import com.ehs.eam.checks.service.EamCheckDefectService;

@Service
public class EamCheckDefectServiceImpl implements EamCheckDefectService {

	
	@Resource
	private EamCheckDefectDao eamCheckDefectDao;
	
	@Override
	public List<EamCheckDefect> findByTaskKey(String taskKey) {
		return eamCheckDefectDao.findByTaskKey(taskKey);
	}

}
