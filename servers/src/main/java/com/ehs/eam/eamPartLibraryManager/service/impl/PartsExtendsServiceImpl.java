package com.ehs.eam.eamPartLibraryManager.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService;

@Service
public class PartsExtendsServiceImpl implements PartsExtendsService{
	
	@Resource
	private PartsExtendsDao partsExtendsDao;

	@Override
	public PageInfoBean getExtendsByKey(QueryBean queryBean, String key) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<PartsExtends> parts = partsExtendsDao.getExtendsByKey(key,pageRequest);
		if (parts!=null) {
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(parts.getContent());
			pb.setTotalCount(parts.getTotalElements());
			return pb;
		}
		return null;
	}

}
