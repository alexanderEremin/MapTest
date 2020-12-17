package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("format")
    @Expose
    private String format;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
