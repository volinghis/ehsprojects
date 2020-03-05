package com.ehs.eam.eamPartLibraryManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.EnterWareHouseDao;
import com.ehs.eam.eamPartLibraryManager.dao.OutWareHouseDao;
import com.ehs.eam.eamPartLibraryManager.dao.PartsExtendsDao;
import com.ehs.eam.eamPartLibraryManager.entity.EnterWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;
import com.ehs.eam.eamPartLibraryManager.service.PartsExtendsService;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtendsServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月18日 下午9:14:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月18日     zhaol          v1.0.0               修改原因
*/
@Service
public class PartsExtendsServiceImpl implements PartsExtendsService{
	
	@Resource
	private PartsExtendsDao partsExtendsDao;
	
	@Resource
	private EnterWareHouseDao ewhDao;
	
	@Resource
	private OutWareHouseDao owhDao;
	
	@Resource
	private FlowProcessInfoService flowProcessInfoService;
	
	@Resource
	private BaseCommonService baseCommonService;

	@Override
	public PageInfoBean getExtendsByKey(QueryBean queryBean, String key) {
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

	@Override
	public PageInfoBean getAllEnterWareHouseParts(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		List<EnterWareHouse> enterWareHouses = ewhDao.findAll();
		List<PartsExtends> list = new ArrayList();
		if(enterWareHouses != null) {
			for (EnterWareHouse enterWareHouse : enterWareHouses) {
				List<PartsExtends> partsExtends = partsExtendsDao.getExtendsByKey(enterWareHouse.getKey());
				if (partsExtends != null) {
					for (PartsExtends partsExtends2 : partsExtends) {
						FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(partsExtends2.getWareHouseKey());
						if(fpi!=null && enterWareHouse !=null) {
							partsExtends2.setStatus(fpi.getFlowCurrentStepName());
							partsExtends2.setReviewer(fpi.getFlowPrevPersonName());
							partsExtends2.setWareHouseCode(enterWareHouse.getWarehouseCode());
							partsExtends2.setWareHouseName(enterWareHouse.getWarehouseName());
						}
					}
				}
				list.addAll(partsExtends);
			}
			if(StringUtils.isNotBlank(queryBean.getQuery())) {
				List<PartsExtends> ppExtends =list.stream().filter(s -> StringUtils.contains(s.getDeviceCode(), queryBean.getQuery()) || StringUtils.contains(s.getDeviceName(), queryBean.getQuery())).collect(Collectors.toList());
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(ppExtends);
				pb.setTotalCount(ppExtends.size());
				return pb;
			}else {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(list);
				pb.setTotalCount(list.size());
				return pb;
			}
		}
		return null;
	}
	
	@Override
	public PageInfoBean getAllOutWareHouseParts(QueryBean queryBean) {
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		List<OutWareHouse> outWareHouses = owhDao.findAll();
		List<PartsExtends> list = new ArrayList();
		if(outWareHouses != null) {
			for (OutWareHouse outWareHouse : outWareHouses) {
				List<PartsExtends> partsExtends = partsExtendsDao.getExtendsByKey(outWareHouse.getKey());
				if (partsExtends != null) {
					for (PartsExtends partsExtends2 : partsExtends) {
						FlowProcessInfo fpi=flowProcessInfoService.findProcessInfoByEntityKey(partsExtends2.getWareHouseKey());
						if(fpi!=null && outWareHouse !=null) {
							partsExtends2.setStatus(fpi.getFlowCurrentStepName());
							partsExtends2.setReviewer(fpi.getFlowPrevPersonName());
							partsExtends2.setWareHouseCode(outWareHouse.getOutWarehouseCode());
							partsExtends2.setWareHouseName(outWareHouse.getOutWarehouseName());
						}
					}
				}
				list.addAll(partsExtends);
			}
			if(StringUtils.isNotBlank(queryBean.getQuery())) {
				List<PartsExtends> ppExtends =list.stream().filter(s -> StringUtils.contains(s.getDeviceCode(), queryBean.getQuery()) || StringUtils.contains(s.getDeviceName(), queryBean.getQuery())).collect(Collectors.toList());
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(ppExtends);
				pb.setTotalCount(ppExtends.size());
				return pb;
			}else {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(list);
				pb.setTotalCount(list.size());
				return pb;
			}
		}
		return null;
	}

}
