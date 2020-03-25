package com.ehs.common.notifys.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.notifys.bean.NotifyMessageInfoQueryBean;
import com.ehs.common.notifys.dao.NotifyMessageInfoDao;
import com.ehs.common.notifys.entity.impl.NotifyMessageInfo;
import com.ehs.common.notifys.service.NotifyMessageInfoService;
import com.ehs.common.oper.bean.PageInfoBean;

@Service
public class NotifyMessageInfoServiceImpl implements NotifyMessageInfoService {

	@Resource
	private NotifyMessageInfoDao notifyMessageInfoDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Override
	public List<NotifyMessageInfo> findByUserOfNotRead(String user) {
		return notifyMessageInfoDao.findByUserOfNotRead(user);
	}

	@Override
	public PageInfoBean findAllByUser(NotifyMessageInfoQueryBean query) {
		PageRequest pageRequest =PageRequest.of(query.getPage()-1, query.getSize(),query.getSortForJpaQuery());
		Page<NotifyMessageInfo> infos=notifyMessageInfoDao.findAllByUser(query.getUserKey(), pageRequest);
		if(infos!=null) {
			PageInfoBean pi=new PageInfoBean();
			pi.setDataList(infos.getContent());
			pi.setTotalCount(infos.getTotalElements());
			return pi;
		}
		return null;
	}

	@Transactional
	@Override
	public void updateRead(List<NotifyMessageInfo> list) {
		if(list!=null&&!list.isEmpty()) {
			for(NotifyMessageInfo n:list) {
				n.setReaded(true);
				baseCommonService.saveOrUpdate(n);
			}
		}
	}

}
