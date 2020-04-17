package com.ehs.web.pictures.entity;

import com.ehs.web.pictures.entity.entitysuper.Pictures;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PICTURES_HIS")
public class PicturesHis extends Pictures {
	
    private static final long serialVersionUID = 7183181011697488154L;
}
