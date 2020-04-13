package com.ehs.eam.scores.schedules;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.ehs.common.base.utils.BaseUtils;
import com.ehs.eam.scores.service.UserScoreService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ScoreAddSchedules.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:48:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Configuration
@EnableScheduling 
public class ScoreAddSchedules implements SchedulingConfigurer {

	private static final Logger logger=LoggerFactory.getLogger(ScoreAddSchedules.class);
	
	@Value("${eam.schedule.task.score-add-updated}")
	private String scoreAddUpdate;
	@Value("${eam.schedule.task.score-add-updated-enable}")
	private int enable;
	
	@Resource
	private UserScoreService userScoreService;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		   taskRegistrar.addTriggerTask(
	                //1.添加任务内容(Runnable)
	                () -> {
	                	if(enable!=1) {
	                		return ;
	                	}
	                	logger.info(">>开始执行定时任务:"+BaseUtils.getNow());
	                	userScoreService.addScores();
	                	logger.info("<<执行定时任务结束");
	                },
	                //2.设置执行周期(Trigger)
	                triggerContext -> {
	                    //2.1 从数据库获取执行周期

	                    //2.3 返回执行周期(Date)
	                    return new CronTrigger(scoreAddUpdate).nextExecutionTime(triggerContext);
	                }
	        );	
	}

}
