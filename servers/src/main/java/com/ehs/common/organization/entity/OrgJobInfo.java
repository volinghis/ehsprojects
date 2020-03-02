package com.ehs.common.organization.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name = "ORG_JOB_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class OrgJobInfo extends com.ehs.common.organization.entity.entitysuper.OrgJobInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
