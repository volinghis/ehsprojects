package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsBaseInfo.java
* @Description: 备件基本信息实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午8:48:53 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class PartsBaseInfo extends BaseEntity{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String DEVICE_NAME="deviceName";
	public static final String DEVICE_CODE="deviceCode";
	public static final String NORM="norm";
	public static final String MATERIAL_TYPE="materialType"; 
	public static final String MATERIAL_CODE="materialCode"; 
	public static final String MANUFACTURER="manufacturer";
	public static final String WARNING_VALUE="warningValue";
	public static final String FOUNDER="founder";
	public static final String REMARK="remark";
	public static final String SORT="sort";
	
	/**
	 * 备件名称
	 */
	private String deviceName;
	
	/**
	 * 备件编码
	 */
	private String deviceCode;
	
	/**
	 * 规格型号
	 */
	private String norm;
	
	/**
	 * 物资类别
	 */
	private String materialType;
	
	/**
	 * 物资编码
	 */
	private String materialCode;
	
	/**
	 * 生产厂商
	 */
	private String manufacturer;
	
	/**
	 * 预警值
	 */
	private Integer warningValue;
	
	/**
	 * 创建人
	 */
	private String founder;
	
	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	
	/**
	 * 排序
	 */
	private Integer sort;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Integer getWarningValue() {
		return warningValue;
	}

	public void setWarningValue(Integer warningValue) {
		this.warningValue = warningValue;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
