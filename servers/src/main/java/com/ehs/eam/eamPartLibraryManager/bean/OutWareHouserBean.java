package com.ehs.eam.eamPartLibraryManager.bean;

import java.util.List;

import com.ehs.eam.eamPartLibraryManager.entity.OutWareHouse;
import com.ehs.eam.eamPartLibraryManager.entity.PartsExtends;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouserBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午11:16:00 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
public class OutWareHouserBean {

	/**
	 * 入库信息
	 */
	private OutWareHouse outWareHouse;
	
	/**
	 * 备件扩展表
	 */
	private List<PartsExtends> partsExtends;

	public OutWareHouse getOutWareHouse() {
		return outWareHouse;
	}

	public void setOutWareHouse(OutWareHouse outWareHouse) {
		this.outWareHouse = outWareHouse;
	}

	public List<PartsExtends> getPartsExtends() {
		return partsExtends;
	}

	public void setPartsExtends(List<PartsExtends> partsExtends) {
		this.partsExtends = partsExtends;
	}
	
}
