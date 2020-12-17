package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("found")
    @Expose
    private Integer found;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("boundedBy")
    @Expose
    private List<List<Double>> boundedBy = null;

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<List<Double>> getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(List<List<Double>> boundedBy) {
        this.boundedBy = boundedBy;
    }
}
