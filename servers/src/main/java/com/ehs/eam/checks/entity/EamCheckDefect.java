package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.checks.entity.entitysuper.EamCheckDefectSuper;

@Entity
@Table(name = "EAM_CHECK_DEFECT",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckDefect extends EamCheckDefectSuper {

	private static final long serialVersionUID = 1L;

}
