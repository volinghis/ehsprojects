package com.ehs.eam.checks.service;

import java.util.List;

import com.ehs.eam.checks.entity.EamCheckReserveUsed;

public interface EamCheckReserveUsedSerevice {

	List<EamCheckReserveUsed> findReserveUsedByTaskKey(String taskKey);

}
