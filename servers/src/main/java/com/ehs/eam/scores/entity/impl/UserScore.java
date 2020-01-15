package com.ehs.eam.scores.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.eam.scores.entity.supers.UserScoreSuper;

@Entity
@Table(name = "EAM_SCORE_USER_SCORE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class UserScore extends UserScoreSuper{

	private static final long serialVersionUID = 1L;

}
