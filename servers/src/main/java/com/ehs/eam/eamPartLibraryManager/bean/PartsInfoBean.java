package com.ehs.eam.eamPartLibraryManager.bean;

import java.util.List;

import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;
import com.ehs.eam.eamPartLibraryManager.entity.PartsBaseInfo;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月2日 下午6:16:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月2日     zhaol           v1.0.0               修改原因
*/
public class PartsInfoBean {

	/**
	 * 备件信息
	 */
	private PartsBaseInfo baseInfo;
	
	/**
	 * 备件参数信息
	 */
	private List<PartsParam> partsParams;
	
	public PartsBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(PartsBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public List<PartsParam> getPartsParams() {
		return partsParams;
	}

	public void setPartsParams(List<PartsParam> partsParams) {
		this.partsParams = partsParams;
	}
	
}
