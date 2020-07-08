package com.ehs.common.basicInfo.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

@MappedSuperclass
public abstract class ContactInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String CONTART_CODE ="contartCode";
	public static final String CONTART_NAME ="contartName";
	public static final String CONTART_TEL ="contartTel";
	public static final String CONTART_POSITION ="contartPosition";
	public static final String CONTART_ADD ="contartAdd";
	
	/**
	 * 联系人编码，和客户关联code
	 */
	public String contartCode;
	
	/**
	 * 联系人名称
	 */
	public String contartName;
	
	/**
	 * 联系人电话
	 */
	public String contartTel;
	
	/**
	 * 联系人职位
	 */
	public String contartPosition;
	
	/**
	 * 联系人地址
	 */
	public String contartAdd;

	public String getContartCode() {
		return contartCode;
	}

	public void setContartCode(String contartCode) {
		this.contartCode = contartCode;
	}

	public String getContartName() {
		return contartName;
	}

	public void setContartName(String contartName) {
		this.contartName = contartName;
	}

	public String getContartTel() {
		return contartTel;
	}

	public void setContartTel(String contartTel) {
		this.contartTel = contartTel;
	}

	public String getContartPosition() {
		return contartPosition;
	}

	public void setContartPosition(String contartPosition) {
		this.contartPosition = contartPosition;
	}

	public String getContartAdd() {
		return contartAdd;
	}

	public void setContartAdd(String contartAdd) {
		this.contartAdd = contartAdd;
	}
	
}
