package com.ehs.eam.checks.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.eam.checks.dao.EamCheckRepairDao;
import com.ehs.eam.checks.entity.EamCheckRepair;
import com.ehs.eam.checks.service.EamCheckRepairService;

@Service
public class EamCheckRepairServiceImpl implements EamCheckRepairService {

	@Resource
	private EamCheckRepairDao eamCheckRepairDao;
	
	@Override
	public List<EamCheckRepair> findByTaskKey(String taskKey) {
		return eamCheckRepairDao.findByTaskKey(taskKey);
	}

}
