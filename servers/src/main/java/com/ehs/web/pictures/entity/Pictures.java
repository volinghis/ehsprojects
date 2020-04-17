package com.ehs.web.pictures.entity;

import com.ehs.common.base.config.DataConfig;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PICTURES",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class Pictures extends com.ehs.web.pictures.entity.entitysuper.Pictures {
	
    private static final long serialVersionUID = 657029093032310961L;
}
