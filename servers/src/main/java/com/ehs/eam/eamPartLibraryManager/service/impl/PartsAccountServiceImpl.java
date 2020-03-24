package com.ehs.eam.eamPartLibraryManager.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.PartsAccountQueryBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsAccountServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月22日 下午11:06:24 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月22日     zhaol          v1.0.0               修改原因
*/
@Service
public class PartsAccountServiceImpl implements PartsAccountService{

	@Resource
	private PartsAccountDao partsAccountDao;
	
	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#findEamPartLibraryAll(com.ehs.eam.eamPartLibraryManager.bean.QueryBean)  
	* @Function: EamPartLibraryServiceImpl.java
	* @Description: 查询所有备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午4:09:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findPartsAccountAll(PartsAccountQueryBean queryBean) {
		try {
			Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
			Page<PartsAccount> account = partsAccountDao.findAllAccount(
					queryBean.getQuery(),
					queryBean.getWarehouseNames(),
					queryBean.getReserve(),
					pb);
			System.out.println("account====="+JsonUtils.toJsonString(account.getContent()));
			if(account != null) {
				PageInfoBean pib=new PageInfoBean();
				pib.setDataList(account.getContent());
				pib.setTotalCount(account.getTotalElements());
				System.out.println("pib.getTotalCount()====="+pib.getTotalCount());
				return pib;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#getAllPartsAccount(com.ehs.eam.eamPartLibraryManager.bean.QueryBean)  
	* @Function: PartsAccountServiceImpl.java
	*
	 */
	@Override
	public PageInfoBean getAllPartsAccount(QueryBean queryBean) {
		try {
			Pageable pb = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize(), queryBean.getSortForJpaQuery());
			Page<PartsAccount> account = partsAccountDao.getAllPartsAccount( queryBean.getQuery(), queryBean.getFlag(),pb);
			if(account != null) {
				PageInfoBean pib=new PageInfoBean();
				pib.setDataList(account.getContent());
				pib.setTotalCount(account.getTotalElements());
				return pib;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
