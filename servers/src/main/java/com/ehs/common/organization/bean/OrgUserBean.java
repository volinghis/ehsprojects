package com.ehs.common.organization.bean;

import java.util.List;
import com.ehs.common.organization.entity.OrgUser;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserBean.java
* @Description: 封装部门ke和用户实体
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月24日 下午7:36:12 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月24日     zhaol          v1.0.0               修改原因
*/
public class OrgUserBean {

	private String orgKey;
	
	private List<OrgUser> users;

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public List<OrgUser> getUsers() {
		return users;
	}

	public void setUsers(List<OrgUser> users) {
		this.users = users;
	}
	
}
