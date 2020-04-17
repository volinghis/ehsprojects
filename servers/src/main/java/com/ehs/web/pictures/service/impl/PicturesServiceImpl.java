package com.ehs.web.pictures.service.impl;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.web.pictures.bean.PicturesMove;
import com.ehs.web.pictures.bean.PicturesQuery;
import com.ehs.web.pictures.dao.PicturesDao;
import com.ehs.web.pictures.entity.Pictures;
import com.ehs.web.pictures.service.PicturesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class PicturesServiceImpl implements PicturesService {

    @Resource
    private PicturesDao picturesDao;

    @Resource
    private BaseCommonService baseCommonService;

    @Override
    public PageInfoBean findPicturesList(PicturesQuery picQuery) {
        PageRequest pr = PageRequest.of(picQuery.getPage() - 1, picQuery.getSize(), Sort.Direction.ASC,"pic_order");
        Page<Pictures> pictures = picturesDao.findPicturesList(picQuery.getQuery(), pr);
        PageInfoBean pb = new PageInfoBean();
        if (pictures != null) {
            pb.setDataList(pictures.getContent());
            pb.setTotalCount(pictures.getTotalElements());
            return pb;
        }
        return null;
    }

    @Override
    @Transactional
    public void savePictureInfo(String fileId, String fileName,int order) {
        Pictures pic = new Pictures();
        pic.setFileId(fileId);
        pic.setIsShow(false);
        pic.setPicName(fileName);
        pic.setPicOrder(order);
        baseCommonService.saveOrUpdate(pic);
    }

    @Override
    @Transactional
    public void saveSortChangedEntity(PicturesMove picturesMove) {
        Pictures srcPic=baseCommonService.findByKey(Pictures.class,picturesMove.getSrcKey());
        Pictures toPic=baseCommonService.findByKey(Pictures.class,picturesMove.getToKey());
        srcPic.setPicOrder(picturesMove.getToOrder());
        toPic.setPicOrder(picturesMove.getSrcOrder());
        baseCommonService.saveOrUpdate(srcPic);
        baseCommonService.saveOrUpdate(toPic);
    }

    @Override
    @Transactional
    public void deletePictureInfo(String key) {
        baseCommonService.deleteByKey(Pictures.class, key);
    }
}
