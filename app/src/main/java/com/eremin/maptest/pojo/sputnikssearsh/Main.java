package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("typo")
    @Expose
    private Typo typo;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Typo getTypo() {
        return typo;
    }

    public void setTypo(Typo typo) {
        this.typo = typo;
    }

}
