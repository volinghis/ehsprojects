package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.checks.entity.entitysuper.EamCheckRepairSuper;

@Entity
@Table(name = "EAM_CHECK_REPAIR_HIS")
public class EamCheckRepairHis extends EamCheckRepairSuper {

	private static final long serialVersionUID = 1L;

}
