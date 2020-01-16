package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class OutWareHouse extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	

	/**
	 * 出库编码
	 */
	private String outWarehouseCode;
	
	/**
	 * 出库类型
	 */
	private String outBoundType;
	
	/**
	 * 所在仓库
	 */
	private String outWarehouseName;
	
	/**
	 * 出库日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp outBoundDate;
	
	/**
	 * 领用人
	 */
	private String receivEmp;
	
	/**
	 * 领用部门
	 */
	private String receivDepart;
	
	/**
	 * 创建人
	 */
	private String founder;
	
	@Column(length = 3000)
	private String remark;

	public String getOutWarehouseCode() {
		return outWarehouseCode;
	}

	public void setOutWarehouseCode(String outWarehouseCode) {
		this.outWarehouseCode = outWarehouseCode;
	}

	public String getOutBoundType() {
		return outBoundType;
	}

	public void setOutBoundType(String outBoundType) {
		this.outBoundType = outBoundType;
	}

	public String getOutWarehouseName() {
		return outWarehouseName;
	}

	public void setOutWarehouseName(String outWarehouseName) {
		this.outWarehouseName = outWarehouseName;
	}

	public Timestamp getOutBoundDate() {
		return outBoundDate;
	}

	public void setOutBoundDate(Timestamp outBoundDate) {
		this.outBoundDate = outBoundDate;
	}

	public String getReceivEmp() {
		return receivEmp;
	}

	public void setReceivEmp(String receivEmp) {
		this.receivEmp = receivEmp;
	}

	public String getReceivDepart() {
		return receivDepart;
	}

	public void setReceivDepart(String receivDepart) {
		this.receivDepart = receivDepart;
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
