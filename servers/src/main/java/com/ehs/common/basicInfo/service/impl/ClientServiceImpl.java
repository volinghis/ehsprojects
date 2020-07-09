package com.ehs.common.basicInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.basicInfo.bean.ClientBean;
import com.ehs.common.basicInfo.bean.ClientQueryBean;
import com.ehs.common.basicInfo.dao.ClientDao;
import com.ehs.common.basicInfo.dao.ContactInfoDao;
import com.ehs.common.basicInfo.entity.ClientInfo;
import com.ehs.common.basicInfo.entity.ContactInfo;
import com.ehs.common.basicInfo.service.ClientService;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: ClientServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月7日 上午11:15:56 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月7日     zhaol           v1.0.0               修改原因
*/
@Service
public class ClientServiceImpl implements ClientService{

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private ClientDao clientDao;
	
	@Resource
	private ContactInfoDao contactInfoDao;
	
	/**
	 * 
	* @see com.ehs.common.basicInfo.service.ClientService#saveClient(com.ehs.common.basicInfo.bean.ClientBean)  
	* @Function: ClientServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:07:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveClient(ClientBean clientBean) {
		logger.info("============准备开始保存客户信息=========");
		System.out.println("clientBean.getClient()===="+clientBean.getClient());
		System.out.println("clientBean.getContactInfos()===="+clientBean.getContactInfos().size());
		if(clientBean.getClient() != null) {
			ClientInfo clientInfo = clientBean.getClient();
			DataDictionary clientType = baseCommonService.findByKey(DataDictionary.class,clientInfo.getClientType());
			if (clientType != null) {
				clientInfo.setClientTypeName(clientType == null ? "" : clientType.getText());
			}
			DataDictionary level = baseCommonService.findByKey(DataDictionary.class,clientInfo.getClientLevel());
			if (level != null) {
				clientInfo.setClientLevelName(level == null ? "" : level.getText());
			}
			clientInfo.setState(0);
			baseCommonService.saveOrUpdate(clientInfo);
			List<ContactInfo> contactInfos = clientBean.getContactInfos();
			if(!CollectionUtils.isEmpty(contactInfos)) {
				for (ContactInfo contactInfo : contactInfos) {
					contactInfo.setContartCode(clientInfo.getKey());
					baseCommonService.saveOrUpdate(contactInfo);
				}
			}
		}
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.ClientService#findClients(com.ehs.common.basicInfo.bean.ClientQueryBean)  
	* @Function: ClientServiceImpl.java
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:08:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findClients(ClientQueryBean clientQueryBean) {
		PageRequest pageRequest = PageRequest.of(clientQueryBean.getPage() - 1, clientQueryBean.getSize());
		Page<ClientInfo> clients = clientDao.findClients(clientQueryBean.getQuery(), pageRequest);
		if (clients != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(clients.getContent());
			pb.setTotalCount(clients.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.ClientService#deleteClientByKey(java.lang.String)  
	* @Function: ClientServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:08:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteClientByKey(String key) {
		try {
			if(key != null) {
				baseCommonService.deleteByKey(ClientInfo.class, key);
				List<ContactInfo> contactInfos = contactInfoDao.findByCode(key);
				if(!CollectionUtils.isEmpty(contactInfos)) {
					for (ContactInfo contactInfo : contactInfos) {
						baseCommonService.findByKey(ContactInfo.class, contactInfo.getKey());
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	* @see com.ehs.common.basicInfo.service.ClientService#changeState(com.ehs.common.basicInfo.entity.ClientInfo)  
	* @Function: ClientServiceImpl.java
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年7月9日 上午11:08:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年7月9日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public ClientInfo changeState(ClientInfo clientInfo) {
		ClientInfo c= baseCommonService.findByKey(ClientInfo.class, clientInfo.getKey());
		c.setState(clientInfo.getState()== 0 ? 0 : 1);
		return baseCommonService.saveOrUpdate(c);
	}

}
