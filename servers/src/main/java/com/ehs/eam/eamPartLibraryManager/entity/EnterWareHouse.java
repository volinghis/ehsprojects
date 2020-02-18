package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "EAM_ENTER_WARE_HOUSE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EnterWareHouse extends com.ehs.eam.eamPartLibraryManager.entity.entitySuper.EnterWareHouse{

	private static final long serialVersionUID = 1L;

}
