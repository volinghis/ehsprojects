package com.ehs.web.pictures.dao;

import com.ehs.web.pictures.entity.Pictures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PicturesDao extends JpaRepository<Pictures,String> {

    @Query(value = "select * from pictures where deleted=0",nativeQuery = true)
    public Page<Pictures> findPicturesList(String query, PageRequest pageRequest);
}
