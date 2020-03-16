package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.checks.entity.entitysuper.EamCheckPlanSuper;


@Entity
@Table(name = "EAM_CHECK_PLAN_HIS")
public class EamCheckPlanHis extends EamCheckPlanSuper{

	private static final long serialVersionUID = 1L;

}
