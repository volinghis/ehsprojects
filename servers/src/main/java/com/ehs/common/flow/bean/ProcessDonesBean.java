package com.ehs.common.flow.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcessDonesBean extends ProcessTasksBean{

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	


	
	
	
}
