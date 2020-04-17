package com.ehs.web.pictures.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.web.pictures.bean.PicturesMove;
import com.ehs.web.pictures.bean.PicturesQuery;

public interface PicturesService {

    PageInfoBean findPicturesList(PicturesQuery picturesQuery);

    void savePictureInfo(String fileId,String fileName,int order);

    public void saveSortChangedEntity(PicturesMove picturesMove);

    void deletePictureInfo(String key);
}
