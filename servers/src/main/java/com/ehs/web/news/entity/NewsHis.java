package com.ehs.web.news.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.web.news.entity.superEntity.News;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: NewsHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月15日 上午9:13:56 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月15日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "NEWS_HIS")
public class NewsHis extends News{

	private static final long serialVersionUID = 1L;

}
