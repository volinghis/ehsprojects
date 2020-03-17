package com.ehs.eam.checks.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.checks.entity.entitysuper.EamCheckTaskSuper;

@Entity
@Table(name = "EAM_CHECK_TASK_HIS")
public class EamCheckTaskHis extends EamCheckTaskSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	@Override
	public String getFlow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEditPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getViewPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
