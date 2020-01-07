package com.ehs.common.flow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;

@Repository
public interface FlowProcessInfoDao extends JpaRepository<FlowProcessInfo, String>{

	public FlowProcessInfo findByFlowProcessInstanceId(String flowProcessInstanceId);
}
