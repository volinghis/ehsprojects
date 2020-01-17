package com.ehs.eam.eamInspectionManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamInspectionManager.entity.entitySuper.EamInspectionDevice;

@Entity
@Table(name = "EAM_INSPECTION_DEVICE_HIS")
public class EamInspectionDeviceHis extends EamInspectionDevice{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
