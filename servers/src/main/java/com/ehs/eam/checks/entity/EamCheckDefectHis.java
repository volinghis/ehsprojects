package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.ehs.eam.checks.entity.entitysuper.EamCheckDefectSuper;

@Entity
@Table(name = "EAM_CHECK_DEFECT_HIS")
public class EamCheckDefectHis extends EamCheckDefectSuper {

	private static final long serialVersionUID = 1L;

}
