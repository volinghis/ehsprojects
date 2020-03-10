package com.ehs.common.oper.bean;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;

import com.ehs.common.base.entity.BaseEntity;

public class PageBody {

	private Integer page=1;
	private Integer size=20;
	private SortBean sort;
	
	
	public SortBean getSort() {
		return sort;
	}
	public void setSort(SortBean sort) {
		this.sort = sort;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Sort getSortForJpaQuery() {
		Sort s = null;
		if(this.sort!=null) {
			if (StringUtils.equals(sort.getOrder(), "descending")) {
				s = new Sort(Sort.Direction.DESC, sort.getProp());
			} else {
				s = new Sort(Sort.Direction.ASC, sort.getProp());
			}
		}else {
			s = new Sort(Sort.Direction.DESC, BaseEntity.CREATION_TIME);
		}
		return s;

	}
}
