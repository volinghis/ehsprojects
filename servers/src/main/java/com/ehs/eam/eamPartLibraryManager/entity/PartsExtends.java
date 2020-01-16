package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "EAM_PARTS_EXTENDS",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class PartsExtends extends com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsExtends {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
