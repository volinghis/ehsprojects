package com.ehs.eam.eamDataBase.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.data.dao.DataFileInfoDao;
import com.ehs.common.data.entity.DataDictionary;
import com.ehs.common.data.entity.DataFileInfo;
import com.ehs.common.data.service.DataDictionaryService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamDataBase.bean.EamDataBaseQuery;
import com.ehs.eam.eamDataBase.dao.DataFileInfoCopyDao;
import com.ehs.eam.eamDataBase.entity.DataFileInfoCopy;
import com.ehs.eam.eamDataBase.service.EamDataBaseServie;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerLastDao;

@Service
public class EamDataBaseServieImpl implements EamDataBaseServie {

	@Resource
	private DataFileInfoDao  dataFileInfoDao;
	
	@Resource
	private DataFileInfoCopyDao  dataFileCopyDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EamLedgerLastDao eamLastDao;
	
	@Override
	public PageInfoBean findEamDataBaseList(EamDataBaseQuery querybean) {
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
        List<Predicate> ps=new ArrayList<Predicate>();
        Specification<DataFileInfoCopy> sf=(Root<DataFileInfoCopy> root, CriteriaQuery<?> query, CriteriaBuilder cb)->{
        	if(StringUtils.isNotBlank(querybean.getQuery())) {
        		ps.add(cb.like(root.get(DataFileInfoCopy.NAME), "%"+querybean.getQuery()+"%"));
        	}
        	if (StringUtils.isNotBlank(querybean.getNodeKey())) {
        		
				ps.add(cb.equal(root.get(DataFileInfoCopy.CATEGORIES), querybean.getNodeKey()));
			}
        	ps.add(cb.or(cb.equal(root.get(BaseEntity.DATA_MODEL),DataModel.UPDATE), cb.equal(root.get(BaseEntity.DATA_MODEL), DataModel.CREATE)));
        	return cb.and(ps.toArray(new Predicate[0]));
        };
        Page<DataFileInfoCopy> fileInfos= dataFileCopyDao.findAll(sf, pageRequest);
		if (fileInfos != null) {
			PageInfoBean pb = new PageInfoBean();
			List<DataFileInfo> resultList=new ArrayList<DataFileInfo>();
			List<DataFileInfoCopy> tempList=fileInfos.getContent();
			if(!CollectionUtils.isEmpty(tempList)) {
				for (DataFileInfoCopy df : tempList) {
					DataFileInfo di=dataFileInfoDao.findDataFileInfoById(df.getFileId(), new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
					DataDictionary dd=baseCommonService.findByKey(DataDictionary.class, di.getCategories());
					di.setCategories(dd.getText());
					resultList.add(di);
				}
			}
			pb.setDataList(resultList);
			pb.setTotalCount(fileInfos.getTotalElements());
			return pb;
		}
		return null;
	}

	/** 
	* @see com.ehs.eam.eamDataBase.service.EamDataBaseServie#saveDataFileInfo(com.ehs.eam.eamDataBase.bean.EamDataReqBean)  
	*/
	@Override
	@Transactional
	public void saveDataFileInfo(String  fileId) {
		Assert.notNull(fileId, "fileId is required");
		String[] fileIds=StringUtils.split(fileId, ",");
	    List<DataFileInfo> fileInfos=dataFileInfoDao.find(fileIds,  new DataModel[] {DataModel.CREATE,DataModel.UPDATE});
		for (DataFileInfo fileInfo : fileInfos) {
			DataFileInfoCopy dc=new DataFileInfoCopy();
			dc.setFileId(fileInfo.getFileId());
			dc.setName(fileInfo.getName());
			dc.setCategories(fileInfo.getCategories());
			baseCommonService.saveOrUpdate(dc);
		}  
		
	}

}
 