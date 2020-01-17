package com.ehs.eam.eamInspectionManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "EAM_INSPECTION_DEVICE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamInspectionDevice extends com.ehs.eam.eamInspectionManager.entity.entitySuper.EamInspectionDevice {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
