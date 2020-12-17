package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainSearch {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
