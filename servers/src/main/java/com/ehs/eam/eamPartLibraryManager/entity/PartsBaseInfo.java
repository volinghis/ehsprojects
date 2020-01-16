package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "EAM_PARTS_BASE_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class PartsBaseInfo extends com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsBaseInfo{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
