package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class PartsExtends extends PartsBaseInfo{

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

}
