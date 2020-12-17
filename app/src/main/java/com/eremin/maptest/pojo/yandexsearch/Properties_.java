package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Properties_ {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("boundedBy")
    @Expose
    private List<List<Double>> boundedBy = null;
    @SerializedName("CompanyMetaData")
    @Expose
    private CompanyMetaData companyMetaData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<Double>> getBoundedBy() {
        return boundedBy;
    }

    public void setBoundedBy(List<List<Double>> boundedBy) {
        this.boundedBy = boundedBy;
    }

    public CompanyMetaData getCompanyMetaData() {
        return companyMetaData;
    }

    public void setCompanyMetaData(CompanyMetaData companyMetaData) {
        this.companyMetaData = companyMetaData;
    }

}
