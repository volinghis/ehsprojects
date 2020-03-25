package com.ehs.common.notifys.service;

import java.util.List;

import com.ehs.common.notifys.bean.NotifyMessageInfoQueryBean;
import com.ehs.common.notifys.entity.impl.NotifyMessageInfo;
import com.ehs.common.oper.bean.PageInfoBean;

public interface NotifyMessageInfoService {

	public List<NotifyMessageInfo> findByUserOfNotRead(String user);
	
	public PageInfoBean findAllByUser(NotifyMessageInfoQueryBean query);
	
	public void updateRead(List<NotifyMessageInfo> list);
}
