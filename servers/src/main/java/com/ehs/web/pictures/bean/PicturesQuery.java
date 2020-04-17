package com.ehs.web.pictures.bean;

import com.ehs.common.oper.bean.PageBody;

public class PicturesQuery extends PageBody {

    private  String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

