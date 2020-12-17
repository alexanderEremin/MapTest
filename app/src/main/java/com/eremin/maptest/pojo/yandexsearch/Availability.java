package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Availability {
    @SerializedName("Intervals")
    @Expose
    private List<Interval> intervals = null;
    @SerializedName("Monday")
    @Expose
    private Boolean monday;
    @SerializedName("Tuesday")
    @Expose
    private Boolean tuesday;
    @SerializedName("Wednesday")
    @Expose
    private Boolean wednesday;
    @SerializedName("Thursday")
    @Expose
    private Boolean thursday;
    @SerializedName("Friday")
    @Expose
    private Boolean friday;
    @SerializedName("Saturday")
    @Expose
    private Boolean saturday;
    @SerializedName("TwentyFourHours")
    @Expose
    private Boolean twentyFourHours;
    @SerializedName("Everyday")
    @Expose
    private Boolean everyday;

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getTwentyFourHours() {
        return twentyFourHours;
    }

    public void setTwentyFourHours(Boolean twentyFourHours) {
        this.twentyFourHours = twentyFourHours;
    }

    public Boolean getEveryday() {
        return everyday;
    }

    public void setEveryday(Boolean everyday) {
        this.everyday = everyday;
    }
}
