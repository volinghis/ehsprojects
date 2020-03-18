package com.ehs.eam.scores.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.scores.bean.UserScoreBean;
import com.ehs.eam.scores.service.UserScoreService;

@RestController
public class UserScoreController {

	@Resource
	private UserScoreService userScoreService;
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/scores/userscore/getAllUserScoresByMonth")
	public String getAllUserScoresByMonth(HttpServletRequest request, @RequestBody PageBody pageBody) {
		Calendar prevMonthCl=Calendar.getInstance();
		prevMonthCl.add(Calendar.MONTH,-1);
		int[] prevMonth=new int[] {prevMonthCl.get(Calendar.MONTH)+1};
		int[] prevMonthYear=new int[] {prevMonthCl.get(Calendar.YEAR)};
		PageInfoBean ubPage=userScoreService.getAllUserScores(prevMonthYear, prevMonth,pageBody);
		return ubPage==null?"[]":JsonUtils.toJsonString(ubPage);
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/scores/userscore/getUserScores")
	public String getUserScores(HttpServletRequest request) {
		
		List<UserScoreBean> usbList=new ArrayList<UserScoreBean>();
		Calendar now=Calendar.getInstance();
		int[] nowMonth=new int[] {now.get(Calendar.MONTH)+1};
		int[] nowYear=new int[] {now.get(Calendar.YEAR)};
		UserScoreBean usb=userScoreService.getUserScoresByYearAndMonth(SysAccessUser.get().getUserKey(), nowYear, nowMonth);
		if(usb==null) {
			usb=new UserScoreBean();
		}else {

		}
		usb.setLabel("当月");
		usb.setTime("now");
		usbList.add(usb);
		
		Calendar prevMonthCl=Calendar.getInstance();
		prevMonthCl.add(Calendar.MONTH,-1);
		int[] prevMonth=new int[] {prevMonthCl.get(Calendar.MONTH)+1};
		int[] prevMonthYear=new int[] {prevMonthCl.get(Calendar.YEAR)};
		UserScoreBean usb1=userScoreService.getUserScoresByYearAndMonth(SysAccessUser.get().getUserKey(), prevMonthYear, prevMonth);
		if(usb1==null) {
			usb1=new UserScoreBean();
		}else {

		}
		usb1.setLabel("上月");
		usb1.setTime("prevMonth");
		usbList.add(usb1);
		
		
		
		Calendar prevQuarter=Calendar.getInstance();
		Integer m=prevQuarter.get(Calendar.MONTH)+1;
		if(m>=1&&m<=3) {
			m=1;
		}else if(m>=4&&m<=6){
			m=4;
		}else if(m>=7&&m<=9){
			m=7;
		}else {
			m=10;
		}
		prevQuarter.set(Calendar.MONTH, m-1);
		prevQuarter.add(Calendar.MONTH, -3);
		int[] prevQuarterMonth=new int[] {prevQuarter.get(Calendar.MONTH)+1,prevQuarter.get(Calendar.MONTH)+2,prevQuarter.get(Calendar.MONTH)+3};
		int[] prevQuarterYear=new int[] {prevQuarter.get(Calendar.YEAR)};
		UserScoreBean usb2=userScoreService.getUserScoresByYearAndMonth(SysAccessUser.get().getUserKey(), prevQuarterYear, prevQuarterMonth);
		if(usb2==null) {
			usb2=new UserScoreBean();
		}else {

		}
		usb2.setLabel("上季度");
		usb2.setTime("prevQuarter");
		usbList.add(usb2);
		
		
		Calendar prevYearCl=Calendar.getInstance();
		prevYearCl.add(Calendar.YEAR,-1);
		int[] prevYear=new int[] {prevYearCl.get(Calendar.YEAR)};
		int[] prevYearMonth=new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
		UserScoreBean usb3=userScoreService.getUserScoresByYearAndMonth(SysAccessUser.get().getUserKey(), prevYear, prevYearMonth);
		if(usb3==null) {
			usb3=new UserScoreBean();
		}else {
		
		}
		usb3.setLabel("上一年");
		usb3.setTime("prevYear");
		usbList.add(usb3);
		
		UserScoreBean usb4= userScoreService.getUserScoresTotal(SysAccessUser.get().getUserKey());
		if(usb4==null) {
			usb4=new UserScoreBean();
		}else {

		}
		usb4.setLabel("总计");
		usb4.setTime("total");
		usbList.add(usb4);
		
		return JsonUtils.toJsonString(usbList);
		
	}

}
