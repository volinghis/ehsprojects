package com.ehs.eam.eamInspectionManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamInspectionManager.entity.entitySuper.EamInspectionTask;

@Entity
@Table(name = "EAM_INSPECTION_TASK_HIS")
public class EamInspectionTaskHis extends EamInspectionTask {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;


}
