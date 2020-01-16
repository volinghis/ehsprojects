/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service.impl 
 * @author: qjj   
 * @date: 2020年1月7日 下午7:46:16 
 */
package com.ehs.eam.eamLedgerManager.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.entity.impl.FlowSample;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapFlowBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamScrapDao;
import com.ehs.eam.eamLedgerManager.entity.EamScrap;
import com.ehs.eam.eamLedgerManager.service.EamScrapService;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamScrapServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月7日 下午7:46:16
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2020年1月7日
 * qjj v1.0.0 修改原因
 */
@Service
public class EamScrapServiceImpl implements EamScrapService {

	@Resource
	private EamScrapDao eamScrapDao;

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService; 

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamScrapService#saveEamScrap(com.ehs.eam.eamLedgerManager.bean.EamScrapRequestBean)
	 */
	@Transactional
	@Override
	public void saveEamScrap(EamScrapRequestBean reqBean) {
		EamScrap reqScrap = reqBean.getEamScrap();
		reqScrap.setScrapNum(getScrapNum());
		//开始流程
		flowBaseService.startProcess(reqScrap, reqBean.getFlowProcessInfo());
		//保存设备报废申请信息
//		String scrapKey = "";
//		if (eamScrap != null) {
//			scrapKey=eamScrap.getKey();
//		}
		
//		//处理准备报废的设备（流程申请成功后）
//		List<EamLedger> ledgers=reqBean.getScrapDatas();
//		if (!ledgers.isEmpty()&&ledgers!=null) {
//			for (EamLedger el : ledgers) {
//				el.setScrapKey(scrapKey);
//				baseCommonService.saveOrUpdate(el);
//			}
//		}
		
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamScrapService#findEamScrapList(com.ehs.eam.eamLedgerManager.bean.EamScrapQueryBean)  
	*/
	@Override
	public PageInfoBean findEamScrapList(EamScrapQueryBean scrapQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(scrapQueryBean.getPage() - 1, scrapQueryBean.getSize());
		Page<EamScrap> eamScraPage = eamScrapDao.findEamScrapList(scrapQueryBean.getQuery(), pageRequest);
		List<EamScrap> resList=eamScraPage.getContent();
		for (EamScrap ep : resList) {
			FlowProcessInfo fpi=	eamScrapDao.findInfoByKey(ep.getKey());
			if(fpi!=null) {
				ep.setStatus(fpi.getFlowCurrentStepName());
			}
		}
		if (eamScraPage != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(resList);
			pb.setTotalCount(eamScraPage.getTotalElements());
			return pb;
		}
		return null;
	}


	/**
	 * 
	* @Function:getScrapNum 
	* @Description:时间戳+（自增数字）
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2020年1月9日 下午3:44:55 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2020年1月9日     qjj        v1.0.0            修改原因
	 */
	private String getScrapNum() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String prefixStr=sdf.format(new Date());
		return prefixStr;
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamScrapService#deleteEamScraps(java.lang.String)  
	*/
	@Override
	@Transactional
	public void deleteEamScraps(String keys) {
		// TODO Auto-generated method stub
		String [] keyArr=keys.split(",");
		if (keyArr.length>0) {
			for (int i = 0; i < keyArr.length; i++) {
				baseCommonService.deleteByKey(EamScrap.class, keyArr[i]);
			}
		}
	}


	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamScrapService#findScrapFlowBean(java.lang.String)  
	*/
	@Override
	public EamScrapFlowBean findScrapFlowBean(String key) {
		// TODO Auto-generated method stub
		EamScrap eamScrap= baseCommonService.findByKey(EamScrap.class, key);
		EamScrapFlowBean eFlowBean=new EamScrapFlowBean();
		if (eamScrap!=null) {
			FlowProcessInfo fpi=eamScrapDao.findInfoByKey(eamScrap.getKey());
			if (fpi!=null) {
				eFlowBean.setCurrentStep(fpi.getFlowCurrentStepName());
				eFlowBean.setCurrentUser(eamScrap.getOwnerName());
				eFlowBean.setEditPage(fpi.getFlowEditPage());
				eFlowBean.setViewPage(fpi.getFlowViewPage());
				eFlowBean.setInstanceId(fpi.getFlowProcessInstanceId());
				eFlowBean.setStartActivityId(fpi.getFlowStartActivityId());
			}
		}
		return eFlowBean;
	}
}
