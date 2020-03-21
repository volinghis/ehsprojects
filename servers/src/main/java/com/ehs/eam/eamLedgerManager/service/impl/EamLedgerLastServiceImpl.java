package com.ehs.eam.eamLedgerManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.dao.DataDictionaryDao;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.flow.service.FlowProcessInfoService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.TreeDataBean;
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

	@Resource
	private DataDictionaryDao dataDictionaryDao;

	@Override
	public PageInfoBean findEamLedgerLastList(EamLedgerQueryBean querybean) {
		PageRequest pr = PageRequest.of(querybean.getPage() - 1, querybean.getSize(),querybean.getSortForJpaQuery());
		Page<EamLedgerLast> eamLedgers = eamLastDao.findEamLedgerLastList(querybean.getName(), querybean.getAddress(),
				querybean.getProfession(), querybean.getDeviceSystem(), querybean.getStatus(), querybean.getComplete(),
				querybean.getTime(), pr);
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
		Page<EamLedgerLast> allLedgers = eamLastDao.findListOnlyNameQuery(querybean.getName(), pageRequest);
		List<EamLedgerLast> resultList = new ArrayList<EamLedgerLast>();
		EamLedgerLast curLedger = eamLastDao.findEamLedgerLastByRefKey(querybean.getDeviceKey());
		List<EamLedgerLast> currentLedgers = getCurrentList(curLedger);
		if (currentLedgers == null || currentLedgers.isEmpty()) {
			resultList = allLedgers.getContent().stream()
					.filter(s -> (!StringUtils.equals(s.getRefKey(), querybean.getDeviceKey())))
					.collect(Collectors.toList());
		} else {
			resultList = allLedgers.getContent().stream()
					.filter(s -> currentLedgers.stream()
							.allMatch(ss -> (!StringUtils.equals(s.getKey(), ss.getKey()))
									&& (!StringUtils.equals(s.getKey(), querybean.getDeviceKey()))))
					.collect(Collectors.toList());
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

	/**
	 * @see com.ehs.eam.eamLedgerManager.service.EamLedgerLastService#findTreeForDevice(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<TreeDataBean> findTreeForDevice(String parentKey, String subKey) {
		List<TreeDataBean> resulList = new ArrayList<TreeDataBean>();// 最终返回的数据
		List<DataDictionary> addressList = dataDictionaryDao.findDataDictByParentKey(parentKey);// 机组地址信息
		List<DataDictionary> subList = dataDictionaryDao.findDataDictByParentKey(subKey);// 专业信息
		if (!CollectionUtils.isEmpty(addressList)) {
			for (DataDictionary address : addressList) {
				resulList.add(new TreeDataBean().setId(address.getKey()).setLabel(address.getText()));
			}

			for (TreeDataBean result : resulList) {
				List<TreeDataBean> children = new ArrayList<TreeDataBean>();
				for (DataDictionary pro : subList) {
					children.add(new TreeDataBean().setId(pro.getKey()).setLabel(pro.getText()).setPid(result.getId()));
				}
				result.setChildren(children);
			}
		}
		return resulList;
	}
}