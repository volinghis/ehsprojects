package com.ehs.web.news.entity.superEntity;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: News.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月14日 下午4:26:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月14日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class News extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NEWS_TITLE="newsTitle";
	public static final String NEWS_CONTENT="newsContent";

	/**
	 * 编码
	 */
	private String dataCode;
	
	/**
	 * 标题
	 */
	private String newsTitle;
	
	/**
	 * 内容
	 */
	@Lob
	private String newsContent;
	
	/**
	 * 排序
	 */
	private Integer sort;

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
