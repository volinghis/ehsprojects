package com.ehs.eam.eamLedgerManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerLastDao;
import com.ehs.eam.eamLedgerManager.entity.EamLedgerLast;
import com.ehs.eam.eamLedgerManager.service.EamLedgerLastService;

@Service
public class EamLedgerLastServiceImpl implements EamLedgerLastService {

	@Resource
	private EamLedgerLastDao eamLastDao;

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private FlowProcessInfoService flowService;

	@Override
	public PageInfoBean findEamLedgerLastList(EamLedgerQueryBean querybean) {
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
        List<Predicate> ps=new ArrayList<Predicate>();
        Specification<EamLedgerLast> sf=(Root<EamLedgerLast> root, CriteriaQuery<?> query, CriteriaBuilder cb)->{
        	if(StringUtils.isNotBlank(querybean.getQuery())) {
        		ps.add(cb.like(root.get(EamLedgerLast.DEVICE_NAME), "%"+querybean.getQuery().trim()+"%"));
        	}
        	if (StringUtils.isNotBlank(querybean.getProfession())) {
				ps.add(cb.equal(root.get(EamLedgerLast.PROFESSION), querybean.getProfession()));
			}
        	if (StringUtils.isNotBlank(querybean.getDeviceSystem())) {
        		ps.add(cb.equal(root.get(EamLedgerLast.DEVICE_SYSTEM), querybean.getDeviceSystem()));
        	}
        	ps.add(cb.equal(root.get(BaseEntity.DELETED), 0));
        	return cb.and(ps.toArray(new Predicate[0]));
        };
        Page<EamLedgerLast> eamLedgers= eamLastDao.findAll(sf, pageRequest);
		if (eamLedgers != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(eamLedgers.getContent());
			pb.setTotalCount(eamLedgers.getTotalElements());
			return pb;
		}
		return null;
	}

	@Override
	public PageInfoBean findLeftEamLedgerList(EamLedgerQueryBean querybean) {
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedgerLast> allLedgers = eamLastDao.findEamLedgerList(querybean.getQuery(), pageRequest);
		List<EamLedgerLast> resultList = new ArrayList<EamLedgerLast>();
		EamLedgerLast curLedger = eamLastDao.findEamLedgerLastByRefKey(querybean.getDeviceKey());
		List<EamLedgerLast> currentLedgers = getCurrentList(curLedger);
		if (currentLedgers == null || currentLedgers.isEmpty()) {
			resultList = allLedgers.getContent().stream()
					.filter(s -> (!StringUtils.equals(s.getRefKey(), querybean.getDeviceKey())))
					.collect(Collectors.toList());
		}else {
			resultList = allLedgers.getContent().stream()
					.filter(s -> currentLedgers.stream()
							.allMatch(ss -> (!StringUtils.equals(s.getKey(), ss.getKey()))
									&& (!StringUtils.equals(s.getKey(), querybean.getDeviceKey())))).collect(Collectors.toList());
		}
		PageInfoBean pb = new PageInfoBean();
		pb.setDataList(resultList);
		pb.setTotalCount(allLedgers.getTotalElements());
		return pb;
	}

	/*
	 * 获取当前设备的已有子设备
	 */
	private List<EamLedgerLast> getCurrentList(EamLedgerLast eamLedger) {
		String refKeys = "";
		if (eamLedger != null) {
			refKeys = eamLedger.getRefDeviceKey();
		}
		List<EamLedgerLast> resEamLedgers = new ArrayList<EamLedgerLast>();
		if (StringUtils.isNotBlank(refKeys)) {
			String[] keysArr = refKeys.split(",");
			for (int i = 0; i < keysArr.length; i++) {
				EamLedgerLast el = eamLastDao.findEamLedgerLastByKey(keysArr[i]);
				if (el != null) {
					resEamLedgers.add(el);
				}
			}
		}
		return resEamLedgers;
	}
}