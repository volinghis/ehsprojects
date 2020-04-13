package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsExtends.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月16日 下午2:22:12 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月16日     zhaol          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class PartsExtends extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String FILE_ID="fileId";
	public static final String PARTS_IMG="partsImg";
	public static final String REF_WAREHOUSE_KEY="refWareHouseKey";
	public static final String REF_FLOW_KEY="refFlowKey";
	public static final String WAREHOUSE_CODE="wareHouseCode";
	public static final String WAREHOUSE="wareHouse";
	public static final String WAREHOUSE_NAME="wareHouseName";
	public static final String ENTER_OUT_TYPE="enterOutType";
	public static final String ENTER_OUT_TYPE_NAME="enterOutTypeName";
	public static final String REVIEWER="reviewer";
	public static final String STATUS="status";
	public static final String STATUS_NAME="statusName";
	public static final String SUPPLIER="supplier";
	public static final String LEAVE_FACTORY_CODE="leaveFactoryCode";
	public static final String LEAVE_FACTORY_DATE="leaveFactoryDate";
	public static final String AMOUNT="amount";
	public static final String PRICE="price";
	public static final String UNIT="unit";
	public static final String TOTAL_PRICE="totalPrice";
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
	 * 仓库跟备件表关联key
	 */
	private String refWareHouseKey;
	
	/**
	 * 流程跟备件表关联key
	 */
	private String refFlowKey;
	
	/**
	 * 入库编码
	 */
	private String wareHouseCode;
	
	/**
	 * 入库名称
	 */
	private String wareHouse;
	private String wareHouseName;
	
	/**
	 * 入库类型
	 */
	private String enterOutType;
	private String enterOutTypeName;
	
	/**
	 * 审核人
	 */
	private String reviewer;
	
	/**
	 * 审核状态
	 */
	private String status;
	private String statusName;
	
	/**
	 * 设备关联文件
	 */
	@Column(length = 3000)
	private String fileId;
	
	/**
	 * 备件图片关联id
	 */
	private String partsImg;
	
	/**
	 * 供应商
	 */
	private String supplier;
	
	/**
	 * 出厂编号
	 */
	private String leaveFactoryCode;
	
	/**
	 * 出厂日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp leaveFactoryDate;
	
	/**
	 * 数量
	 */
	private Integer amount;
	
	/**
	 * 单价
	 */
	private BigDecimal price;
	
	/**
	 * 单位
	 */
	private Character unit;
	
	/**
	 * 总价
	 */
	private BigDecimal totalPrice;
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
	@Column(length = 2000)
	private String remark;
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getPartsImg() {
		return partsImg;
	}

	public void setPartsImg(String partsImg) {
		this.partsImg = partsImg;
	}
	
	public String getRefWareHouseKey() {
		return refWareHouseKey;
	}

	public void setRefWareHouseKey(String refWareHouseKey) {
		this.refWareHouseKey = refWareHouseKey;
	}
	
	public String getRefFlowKey() {
		return refFlowKey;
	}

	public void setRefFlowKey(String refFlowKey) {
		this.refFlowKey = refFlowKey;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLeaveFactoryCode() {
		return leaveFactoryCode;
	}

	public void setLeaveFactoryCode(String leaveFactoryCode) {
		this.leaveFactoryCode = leaveFactoryCode;
	}

	public Timestamp getLeaveFactoryDate() {
		return leaveFactoryDate;
	}

	public void setLeaveFactoryDate(Timestamp leaveFactoryDate) {
		this.leaveFactoryDate = leaveFactoryDate;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Character getUnit() {
		return unit;
	}

	public void setUnit(Character unit) {
		this.unit = unit;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

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

	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}
	
	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getEnterOutType() {
		return enterOutType;
	}

	public void setEnterOutType(String enterOutType) {
		this.enterOutType = enterOutType;
	}

	public String getEnterOutTypeName() {
		return enterOutTypeName;
	}

	public void setEnterOutTypeName(String enterOutTypeName) {
		this.enterOutTypeName = enterOutTypeName;
	}
	
//	public Timestamp getInboundDate() {
//		return inboundDate;
//	}
//
//	public void setInboundDate(Timestamp inboundDate) {
//		this.inboundDate = inboundDate;
//	}
}
