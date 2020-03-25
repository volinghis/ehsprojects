package com.ehs.common.notifys.controller;

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
import com.ehs.common.notifys.bean.NotifyMessageInfoQueryBean;
import com.ehs.common.notifys.entity.impl.NotifyMessageInfo;
import com.ehs.common.notifys.service.NotifyMessageInfoService;
import com.ehs.common.oper.bean.PageInfoBean;

@RestController
public class NotifyMessageInfoController {
	
	@Resource
	private NotifyMessageInfoService notifyMessageInfoService;
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/notify/message/findAllByUser")
	public String findAllByUser(@RequestBody NotifyMessageInfoQueryBean query, HttpServletRequest request) {

		query.setUserKey(SysAccessUser.get().getUserKey());
		PageInfoBean pb = notifyMessageInfoService.findAllByUser(query);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/notify/message/findByUserOfNotRead")
	public String findByUserOfNotRead(HttpServletRequest request) {
		List<NotifyMessageInfo> ln = notifyMessageInfoService.findByUserOfNotRead(SysAccessUser.get().getUserKey());
		notifyMessageInfoService.updateRead(ln);
		return (ln==null?"[]":JsonUtils.toJsonString(ln));
	}
	
}
