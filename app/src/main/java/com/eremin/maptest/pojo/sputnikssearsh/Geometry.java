package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geometry {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("geometries")
    @Expose
    private List<Geometry_> geometries = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Geometry_> getGeometries() {
        return geometries;
    }

    public void setGeometries(List<Geometry_> geometries) {
        this.geometries = geometries;
    }
}
