package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.flow.entity.FlowBaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OutWareHouse.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Administrator
* @date: 2020年2月11日 下午9:11:28 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月11日     Administrator           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class OutWareHouse extends FlowBaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String OUT_WAREHOUSE_CODE = "outWarehouseCode";
	public static final String OUT_WAREHOUSE = "outWarehouse";
	public static final String OUT_WAREHOUSE_NAME = "outWarehouseName";
	public static final String OUTBOUND_TYPE = "outBoundType";
	public static final String OUTBOUND_TYPE_NAME = "outBoundTypeName";
	public static final String OUTBOUND_DATE = "outBoundDate";
	public static final String RECEIVE_EMP_CODE = "receiveEmpCode";
	public static final String RECEIVE_EMP = "receiveEmp";
	public static final String RECEIVE_DEPART_CODE = "receiveDepartCode";
	public static final String RECEIVE_DEPART = "receiveDepart";
	public static final String STATUS = "status";
	public static final String FOUNDER = "founder";

	/**
	 * 出库编码
	 */
	private String outWarehouseCode;
	
	/**
	 * 出库类型
	 */
	private String outBoundType;
	private String outBoundTypeName;
	
	/**
	 * 所在仓库
	 */
	private String outWarehouse;
	private String outWarehouseName;
	
	/**
	 * 出库日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp outBoundDate;
	
	/**
	 * 领用人编码
	 */
	private String receiveEmpCode;
	
	/**
	 * 领用人
	 */
	private String receiveEmp;
	
	/**
	 * 部门编码
	 */
	private String receiveDepartCode;
	
	/**
	 * 领用部门
	 */
	private String receiveDepart;
	
	/**
	 *申请状态
	 */
	private String status;
	
	/**
	 * 创建人
	 */
	private String founder;
	
	/**
	 * 备注
	 */
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
	
	public String getOutBoundTypeName() {
		return outBoundTypeName;
	}

	public void setOutBoundTypeName(String outBoundTypeName) {
		this.outBoundTypeName = outBoundTypeName;
	}
	
	public String getOutWarehouse() {
		return outWarehouse;
	}

	public void setOutWarehouse(String outWarehouse) {
		this.outWarehouse = outWarehouse;
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

	public String getReceiveEmpCode() {
		return receiveEmpCode;
	}

	public void setReceiveEmpCode(String receiveEmpCode) {
		this.receiveEmpCode = receiveEmpCode;
	}

	public String getReceiveEmp() {
		return receiveEmp;
	}

	public void setReceiveEmp(String receiveEmp) {
		this.receiveEmp = receiveEmp;
	}

	public String getReceiveDepartCode() {
		return receiveDepartCode;
	}

	public void setReceiveDepartCode(String receiveDepartCode) {
		this.receiveDepartCode = receiveDepartCode;
	}

	public String getReceiveDepart() {
		return receiveDepart;
	}

	public void setReceiveDepart(String receiveDepart) {
		this.receiveDepart = receiveDepart;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String getFlow() {
		return "EamOutWareHouseFlow";
	}

	@Override
	public String getEditPage() {
		return "outWarehouseEdit";
	}

	@Override
	public String getViewPage() {
		return "outWarehouseEdit";
	}
	
}
