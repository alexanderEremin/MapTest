package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Typo {
    @SerializedName("OriginalQuery")
    @Expose
    private String originalQuery;
    @SerializedName("FixedQuery")
    @Expose
    private String fixedQuery;
    @SerializedName("Rank")
    @Expose
    private Integer rank;

    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public String getFixedQuery() {
        return fixedQuery;
    }

    public void setFixedQuery(String fixedQuery) {
        this.fixedQuery = fixedQuery;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
