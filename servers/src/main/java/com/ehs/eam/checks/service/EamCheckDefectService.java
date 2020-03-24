package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.eam.checks.entity.EamCheckDefect;

public interface EamCheckDefectService {

	public List<EamCheckDefect> findByTaskKey(String taskKey);
}
