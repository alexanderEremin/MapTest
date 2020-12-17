package com.eremin.maptest.pojo.yandexsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMetaData {
    @SerializedName("SearchResponse")
    @Expose
    private SearchResponse searchResponse;
    @SerializedName("SearchRequest")
    @Expose
    private SearchRequest searchRequest;

    public SearchResponse getSearchResponse() {
        return searchResponse;
    }

    public void setSearchResponse(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }

}
