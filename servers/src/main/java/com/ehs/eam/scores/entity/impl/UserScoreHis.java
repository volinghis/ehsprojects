package com.ehs.eam.scores.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.scores.entity.supers.UserScoreSuper;

@Entity
@Table(name = "HIS_EAM_SCORE_USER_SCORE")
public class UserScoreHis extends UserScoreSuper{

	private static final long serialVersionUID = 1L;

}
