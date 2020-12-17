package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;
    @SerializedName("address")
    @Expose
    private List<Address> address = null;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
