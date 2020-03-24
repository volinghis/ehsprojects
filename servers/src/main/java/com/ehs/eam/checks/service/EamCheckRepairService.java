package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.eam.checks.entity.EamCheckRepair;


public interface EamCheckRepairService {
	
	public List<EamCheckRepair> findByTaskKey(String taskKey);

}
