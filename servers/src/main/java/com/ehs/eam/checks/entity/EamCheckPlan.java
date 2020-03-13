package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.checks.entity.entitysuper.EamCheckPlanSuper;

@Entity
@Table(name = "EAM_CHECK_PLAN",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckPlan extends EamCheckPlanSuper {

	private static final long serialVersionUID = 1L;

}
