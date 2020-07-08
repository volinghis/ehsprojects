package com.ehs.common.basicInfo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.basicInfo.entity.SupplierInfo;

@Repository
public interface SupplierDao extends JpaRepository<SupplierInfo, String>{

	@Query(" select s from SupplierInfo s where s."+SupplierInfo.DELETED+"=0 and (s."+SupplierInfo.SUPPLIER_CODE+" like %?1% or s."+SupplierInfo.SUPPLIER_NAME+" like %?1% ) order by  "+BaseEntity.BASE_SORT_NUM+" desc")
	public Page<SupplierInfo> findSuppliers(String query, PageRequest pageRequest);
}
