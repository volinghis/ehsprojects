package com.ehs.web.pictures.entity.entitysuper;


import com.ehs.common.base.entity.BaseEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pictures extends BaseEntity {

    private static final long serialVersionUID = -8859813904443778795L;

    private String picName;

    private String fileId;

    /**
     * 是否显示
     */
    private Boolean isShow;

    /**
     * 图片顺序
     */
    private Integer picOrder;

    public Boolean getShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public Integer getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(Integer picOrder) {
        this.picOrder = picOrder;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


}
