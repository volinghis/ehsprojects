package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouse.java
* @Description: 入库实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午9:09:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class EnterWareHouse extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	/**
	 * 入库编码
	 */
	private String warehouseCode;
	
	/**
	 * 入库类型
	 */
	private String inboundType;
	
	/**
	 * 所在仓库
	 */
	private String warehouseName;
	
	/**
	 * 入库日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp inboundDate;
	
	/**
	 * 创建人
	 */
	private String founder;
	
	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getInboundType() {
		return inboundType;
	}

	public void setInboundType(String inboundType) {
		this.inboundType = inboundType;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Timestamp getInboundDate() {
		return inboundDate;
	}

	public void setInboundDate(Timestamp inboundDate) {
		this.inboundDate = inboundDate;
	}
	
	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
