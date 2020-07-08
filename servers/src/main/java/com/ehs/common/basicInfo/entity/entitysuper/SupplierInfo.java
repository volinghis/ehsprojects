package com.ehs.common.basicInfo.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: SupplierInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年7月8日 上午9:17:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年7月8日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public class SupplierInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String SUPPLIER_CODE = "supplierCode";
	public static final String SUPPLIER_NAME = "supplierName";
	public static final String SUPPLIER_TYPE = "supplierType";
	public static final String SUPPLIER_TYPE_NAME = "supplierTypeName";
	public static final String SORT = "sort";
	public static final String REMARK = "remark";
	public static final String STATE = "state";
	
	
	/**
	 * 供应商编号
	 */
	private String supplierCode;
	
	/**
	 * 供应商名称
	 */
	private String supplierName;
	
	/**
	 * 供应商类型
	 */
	private String supplierType;
	private String supplierTypeName;
	
	/**
	 * 排序
	 */
	private String sort;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 状态
	 */
	private Integer state;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierTypeName() {
		return supplierTypeName;
	}

	public void setSupplierTypeName(String supplierTypeName) {
		this.supplierTypeName = supplierTypeName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
