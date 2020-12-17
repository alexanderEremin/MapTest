package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("formatted")
    @Expose
    private String formatted;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
}
