package com.ehs.common.basicInfo.service;

import com.ehs.common.basicInfo.bean.CommodityQueryBean;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: CommodityService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 下午2:43:25 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
public interface CommodityService {

	public void saveCommodity(PartsAccount partsAccount);

	public PageInfoBean findCommodities(CommodityQueryBean commodityQueryBean);

	public void deleteCommodity(String key);

}
