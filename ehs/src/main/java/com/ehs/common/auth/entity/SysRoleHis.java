package com.ehs.common.auth.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.auth.entity.entitysuper.SysRole;
import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Role.java
* @Description: 角色实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午3:57:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "HIS_SYS_ROLE")
public class SysRoleHis extends SysRole {

	private static final long serialVersionUID = 1L;
	
	
}
