package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: PartsAccount.java
* @Description: 备件台账实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月9日 上午8:48:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月9日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class PartsAccount extends PartsBaseInfo {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

//	public static final String DEVICE_NAME="deviceName";
//	public static final String DEVICE_CODE="deviceCode";
//	public static final String NORM="norm";
//	public static final String WAREHOUSE_KEY="wareHouseKey";
	public static final String WAREHOUSE_CODE="warehouseCode";
	public static final String INBOUND_TYPE="inboundType";
	public static final String WAREHOUSE_NAME="warehouseName";
	public static final String INBOUND_DATE="inboundDate";
	public static final String LEAVE_FACTORY_CODE="leaveFactoryCode";
	public static final String LEAVE_FACTORY_DATE="leaveFactoryDate";
	public static final String UNIT="unit";
	public static final String PRICE="price";
	public static final String AMOUNT="amount";
	public static final String TOTAL_PRICE="totalPrice";
//	public static final String WARNING_VALUE="warningValue";
	public static final String MANUFACTURER="manufacturer";
	public static final String SUPPLIER="supplier";
	public static final String MATERIAL_TYPE="materialType"; 
	public static final String MATERIAL_CODE="materialCode"; 
//	public static final String BUY_TIME="buyTime";
	
	/**
	 * 仓库跟台账关联key
	 */
	private String wareHouseKey;
	
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
	
//	/**
//	 * 购买时间
//	 */
//	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
//	private Timestamp buyTime;
	
	public String getWareHouseKey() {
		return wareHouseKey;
	}

	public void setWareHouseKey(String wareHouseKey) {
		this.wareHouseKey = wareHouseKey;
	}

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
//
//	public Timestamp getBuyTime() {
//		return buyTime;
//	}
//
//	public void setBuyTime(Timestamp buyTime) {
//		this.buyTime = buyTime;
//	}
	
}
