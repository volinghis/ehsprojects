package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsBaseInfo;

@Entity
@Table(name = "EAM_PARTS_BASE_INFO_HIS")
public class PartsBaseInfoHis extends PartsBaseInfo {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
