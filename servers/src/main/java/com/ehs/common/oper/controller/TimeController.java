package com.ehs.common.oper.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.TimeBean;

@RestController
public class TimeController {

	
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/oper/time/getNow")
	@ResponseBody
	public String getNow(HttpServletRequest request,HttpServletResponse response) {
		TimeBean tb=new TimeBean();
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(BaseUtils.getNow().getTime());
		tb.setYear(c.get(Calendar.YEAR));
		tb.setMonth(c.get(Calendar.MONTH)+1);
		tb.setDay(c.get(Calendar.DATE));
		tb.setTime(c.getTimeInMillis());
		tb.setDate(tb.getYear()+"-"+tb.getMonth()+"-"+tb.getDay());
		tb.setDateTime(tb.getYear()+"-"+tb.getMonth()+"-"+tb.getDay()+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
		return JsonUtils.toJsonString(tb);
	}
}
