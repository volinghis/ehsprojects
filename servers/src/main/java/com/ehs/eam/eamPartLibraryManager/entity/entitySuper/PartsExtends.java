package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class PartsExtends extends BaseEntity{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String WAREHOUSE_KEY="wareHouseKey";
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
	 * 仓库跟备件扩展信息表关联key
	 */
	private String wareHouseKey;
	
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
	@Column(length = 3000)
	private String remark;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	public String getWareHouseKey() {
		return wareHouseKey;
	}

	public void setWareHouseKey(String wareHouseKey) {
		this.wareHouseKey = wareHouseKey;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
