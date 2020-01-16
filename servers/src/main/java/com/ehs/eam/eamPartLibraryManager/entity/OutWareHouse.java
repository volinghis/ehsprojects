package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "EAM_OUT_WARE_HOUSE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class OutWareHouse extends com.ehs.eam.eamPartLibraryManager.entity.entitySuper.OutWareHouse {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
