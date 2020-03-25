package com.ehs.eam.checks.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.eam.checks.dao.EamCheckReserveUsedDao;
import com.ehs.eam.checks.entity.EamCheckReserveUsed;
import com.ehs.eam.checks.service.EamCheckReserveUsedSerevice;

@Service
public class EamCheckReserveUsedSereviceImpl implements EamCheckReserveUsedSerevice{

	@Resource
	private EamCheckReserveUsedDao reserveUsedDao;
	
	@Override
	public List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey) {
		return reserveUsedDao.findReserveUsedByTaskKey(taskKey);
	}

}
