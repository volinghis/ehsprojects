package com.ehs.eam.scores.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.scores.entity.supers.UserScoreSuper;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: UserScoreHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午11:16:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "HIS_EAM_SCORE_USER_SCORE")
public class UserScoreHis extends UserScoreSuper{

	private static final long serialVersionUID = 1L;

}
