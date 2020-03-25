package com.ehs.common.notifys.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.notifys.entity.NotifyMessageInfoSuper;

@Entity
@Table(name="NOTIFY_MESSAGE_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class NotifyMessageInfo extends NotifyMessageInfoSuper {

	private static final long serialVersionUID = 1L;
	
	

}
