package com.ehs.eam.checks.schedules;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import com.ehs.eam.checks.entity.EamCheckPlan;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.eam.checks.service.EamCheckPlanService;


@Configuration
@EnableScheduling 
public class CheckTaskSendSchedules implements SchedulingConfigurer{

	
	private static final Logger logger=LoggerFactory.getLogger(CheckTaskSendSchedules.class);
	
	@Value("${eam.schedule.task.check-task-send}")
	private String checkTaskSend;
	@Value("${eam.schedule.task.check-task-send-enable}")
	private int enable;
	
	@Resource
	private EamCheckPlanService eamCheckPlanService;
	
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		   taskRegistrar.addTriggerTask(
	                //1.添加任务内容(Runnable)
	                () -> {
	                	if(enable!=1) {
	                		return ;
	                	}
	                	logger.info(">>开始执行巡检任务下发定时任务:"+BaseUtils.getNow());
	                	
	                	List<EamCheckPlan> list= eamCheckPlanService.getAllPlanOfEnable();
	                	if(list!=null&&!list.isEmpty()) {
	                		Calendar cl=Calendar.getInstance();
	                		for(EamCheckPlan e:list) {
	                			
	                			boolean send=false;
	                			if(StringUtils.equals(e.getRate(), "YEAR")) {
	                				if(cl.get(Calendar.DAY_OF_YEAR)==1) {
	                					send=true;
	                				}
	                			}else if(StringUtils.equals(e.getRate(), "MONTH")){
	                				if(cl.get(Calendar.DAY_OF_MONTH)==1) {
	                					send=true;
	                				}
	                			}else if(StringUtils.equals(e.getRate(), "WEEK")){
	                				if(cl.get(Calendar.DAY_OF_WEEK)==2) {
	                					send=true;
	                				}
	                			}else {
	                				send=true;
	                			}
	                			if(send) {
	                				logger.info("计划："+e.getKey()+"下发成功");
	                				eamCheckPlanService.sendTask(e);
	                			}
	                		}
	                	}
	                	logger.info("<<执行巡检任务下发定时任务结束");
	                },
	                //2.设置执行周期(Trigger)
	                triggerContext -> {
	                    //2.1 从数据库获取执行周期

	                    //2.3 返回执行周期(Date)
	                    return new CronTrigger(checkTaskSend).nextExecutionTime(triggerContext);
	                }
	        );
	}
	public static void main(String[] args) {
		Calendar cl=Calendar.getInstance();
		System.out.println(cl.get(Calendar.DAY_OF_MONTH));
	}

}
