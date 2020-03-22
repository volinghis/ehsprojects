package com.ehs.eam.eamPartLibraryManager.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.eam.eamPartLibraryManager.bean.WareHouseFlowBean;
import com.ehs.eam.eamPartLibraryManager.bean.OutWareHouserBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWareHouseService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年3月22日 下午11:18:59 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年3月22日     zhaol          v1.0.0               修改原因
*/
public interface OutWareHouseService {

//	public PageInfoBean findAll(QueryBean queryBean);

//	public int validAmount(String amount, String deviceCode, String price);
	
	public void saveOutWareHouse(OutWareHouserBean wareHouserBean);

	public void updatePartsAccount(FlowProcessInfo flowProcessInfo);

	public WareHouseFlowBean getOutWareHouseFlowBean(String key);

}
