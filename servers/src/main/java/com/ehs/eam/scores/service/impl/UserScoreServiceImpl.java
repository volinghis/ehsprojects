package com.ehs.eam.scores.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.AccessUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.scores.bean.UserScoreBean;
import com.ehs.eam.scores.dao.UserScoreDao;
import com.ehs.eam.scores.entity.impl.UserScore;
import com.ehs.eam.scores.service.UserScoreService;

@Service
public class UserScoreServiceImpl implements UserScoreService {

	
	private static final Logger logger=LoggerFactory.getLogger(UserScoreServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	
	@Resource
	private UserScoreDao userScoreDao;
	
	@Resource
	private HistoryService historyService;
	
	@Transactional
	@Override
	public void addScores() {
		List<FlowProcessInfo> list= flowProcessInfoService.findProcessInfoNotScore();
		if(list!=null&&!list.isEmpty()) {
			Calendar cl=Calendar.getInstance();
			for(FlowProcessInfo f:list) {
				try {
					logger.info("更新流程:"+f.getFlowProcessName()+"评分，流程实例ID"+f.getFlowProcessInstanceId());
				
					HistoricProcessInstance hpi= historyService.createHistoricProcessInstanceQuery().processInstanceId(f.getFlowProcessInstanceId()).singleResult();
					if(hpi!=null) {
						String startUser=hpi.getStartUserId();
						List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().processInstanceId(f.getFlowProcessInstanceId()).list();
	
						if(tasks!=null&&!tasks.isEmpty()) {
							List<String> comuted=new ArrayList<String>();
							for(HistoricTaskInstance hti:tasks) {
								if(StringUtils.isNotBlank(hti.getAssignee())&&!comuted.contains(hti.getAssignee())) {
									
									long startTime=hti.getCreateTime().getTime();
									long endTime=hti.getEndTime().getTime();
									
									long rateTime=endTime-startTime;
									int rate=0;
									if(rateTime<12*60*60*1000) {
										rate=7;
									}else if(rateTime>=12*60*60*1000&&rateTime<24*60*60*1000) {
										rate=6;
									}else if(rateTime>=24*60*60*1000&&rateTime<36*60*60*1000) {
										rate=5;
									}else if(rateTime>=36*60*60*1000&&rateTime<48*60*60*1000) {
										rate=4;
									}else if(rateTime>=48*60*60*1000&&rateTime<60*60*60*1000) {
										rate=3;
									}else if(rateTime>=60*60*60*1000&&rateTime<72*60*60*1000) {
										rate=2;
									}else {
										rate=1;
									}
									cl.setTimeInMillis(endTime);
									UserScore us=new UserScore();
									us.setRate(rate);
									us.setUserKey(hti.getAssignee());
									us.setUserName(AccessUtils.getUserNameByUserKey(hti.getAssignee()));
									us.setReCompletePoint(false);
									us.setItemName(f.getFlowProcessName());
									us.setItemCreateDate(new Timestamp(endTime));
									us.setYears(cl.get(Calendar.YEAR));
									us.setMonths(cl.get(Calendar.MONTH)+1);
									if(StringUtils.equalsIgnoreCase(hti.getAssignee(), startUser)) {
										us.setScore(5);
									}else {
										us.setScore(1);
									}
									baseCommonService.saveOrUpdate(us);
									comuted.add(hti.getAssignee());
								}
	
							}
	
						}
						
	
					}
					f.setFlowScore(true);
					baseCommonService.saveOrUpdate(f);
				}catch(Exception ex) {
					ex.printStackTrace();
					logger.error("更新流程："+f.getFlowProcessName()+"评分时发生异常,流程实例ID"+f.getFlowProcessInstanceId());
					logger.error(ex.getMessage());
				}

			}
		}
	}

	@Override
	public UserScoreBean getUserScoresTotal(String userKey) {
		return userScoreDao.findUserScores(userKey);
	}


	@Override
	public UserScoreBean getUserScoresByYearAndMonth(String userKey,int[] years, int[] months) {
		return userScoreDao.findUserScores(years,months,userKey);
	}

	@Override
	public PageInfoBean getAllUserScores(int[] years, int[] months,PageBody pageBody) {
		PageRequest pageRequest =PageRequest.of(pageBody.getPage()-1, pageBody.getSize());
		Page<UserScoreBean> infos=userScoreDao.findUserScores(years,months,pageRequest);
		if(infos!=null) {
			PageInfoBean pi=new PageInfoBean();
			pi.setDataList(infos.getContent());
			pi.setTotalCount(infos.getTotalElements());
			return pi;
		}
		return null;
	}


}
