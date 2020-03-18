package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.checks.entity.entitysuper.EamCheckReserveUsedSuper;

@Entity
@Table(name = "EAM_CHECK_RESERVE_USED",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamCheckReserveUsed extends EamCheckReserveUsedSuper {

	
	
	private static final long serialVersionUID = 1L;

}
