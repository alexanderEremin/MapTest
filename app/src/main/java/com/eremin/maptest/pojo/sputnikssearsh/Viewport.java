package com.eremin.maptest.pojo.sputnikssearsh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {
    @SerializedName("TopLat")
    @Expose
    private Double topLat;
    @SerializedName("TopLon")
    @Expose
    private Double topLon;
    @SerializedName("BotLat")
    @Expose
    private Double botLat;
    @SerializedName("BotLon")
    @Expose
    private Double botLon;

    public Double getTopLat() {
        return topLat;
    }

    public void setTopLat(Double topLat) {
        this.topLat = topLat;
    }

    public Double getTopLon() {
        return topLon;
    }

    public void setTopLon(Double topLon) {
        this.topLon = topLon;
    }

    public Double getBotLat() {
        return botLat;
    }

    public void setBotLat(Double botLat) {
        this.botLat = botLat;
    }

    public Double getBotLon() {
        return botLon;
    }

    public void setBotLon(Double botLon) {
        this.botLon = botLon;
    }

}
