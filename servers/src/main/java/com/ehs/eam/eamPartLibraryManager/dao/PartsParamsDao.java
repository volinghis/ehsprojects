//package com.ehs.eam.eamPartLibraryManager.dao;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.ehs.eam.eamPartLibraryManager.entity.PartsParam;
//
///**   
//* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
//* @ClassName: PartsParamsDao.java
//* @Description: 该类的功能描述
//*
//* @version: v1.0.0
//* @author: zhaol
//* @date: 2020年1月2日 下午7:35:05 
//*
//* Modification History:
//* Date         Author          Version            Description
//*---------------------------------------------------------*
//* 2020年1月2日     zhaol           v1.0.0               修改原因
//*/
//@Repository
//public interface PartsParamsDao extends JpaRepository<PartsParam, String>{
//
//	@Query(" select p from PartsParam p where p."+PartsParam.PARAM_KEY+"=?1 and p."+PartsParam.DELETED+" = 0" )
//	public List<PartsParam> getAllPartsParamByKey(String key);
//}
