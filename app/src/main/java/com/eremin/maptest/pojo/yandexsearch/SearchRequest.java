package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchRequest {
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("skip")
    @Expose
    private Integer skip;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("boundedBy")
    @Expose
    private List<List<Double>> boundedBy = null;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public List<List<Double>> getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(List<List<Double>> boundedBy) {
        this.boundedBy = boundedBy;
    }
}
