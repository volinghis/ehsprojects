package com.ehs.web.news.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: News.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月14日 下午4:26:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月14日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "NEWS",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class News extends com.ehs.web.news.entity.superEntity.News{

	private static final long serialVersionUID = 1L;

}
