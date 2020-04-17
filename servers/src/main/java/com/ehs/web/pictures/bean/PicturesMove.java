package com.ehs.web.pictures.bean;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 *
 * @ClassName: PicturesMove
 * @Description: 图片上移和下移对象
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020/4/16 14:44
 * <p>
 * Modification History:
 * Date            Author          Version       Description
 * ---------------------------------------------------------*
 * 2020/4/16         qjj          v1.0.0       修改原因
 */
public class PicturesMove {

    //移动对象的key
    private String srcKey;
    //目标位置对象的key
    private String toKey;
    //当前对象的顺序
    private Integer srcOrder;
    //目标对象的顺序
    private Integer toOrder;

    public String getSrcKey() {
        return srcKey;
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }

    public String getToKey() {
        return toKey;
    }

    public void setToKey(String toKey) {
        this.toKey = toKey;
    }

    public Integer getSrcOrder() {
        return srcOrder;
    }

    public void setSrcOrder(Integer srcOrder) {
        this.srcOrder = srcOrder;
    }

    public Integer getToOrder() {
        return toOrder;
    }

    public void setToOrder(Integer toOrder) {
        this.toOrder = toOrder;
    }
}
