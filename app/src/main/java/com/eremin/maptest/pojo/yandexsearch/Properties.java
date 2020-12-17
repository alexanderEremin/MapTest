package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {
    @SerializedName("ResponseMetaData")
    @Expose
    private ResponseMetaData responseMetaData;

    public ResponseMetaData getResponseMetaData() {
        return responseMetaData;
    }

    public void setResponseMetaData(ResponseMetaData responseMetaData) {
        this.responseMetaData = responseMetaData;
    }
}
