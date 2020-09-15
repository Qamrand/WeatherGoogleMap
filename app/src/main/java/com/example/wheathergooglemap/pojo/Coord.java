package com.example.wheathergooglemap.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coord implements Serializable
{

    @SerializedName("lon")
    @Expose
    public double lon;
    @SerializedName("lat")
    @Expose
    public double lat;
    private final static long serialVersionUID = 2203300050378011383L;

    public Coord withLon(double lon) {
        this.lon = lon;
        return this;
    }

    public Coord withLat(double lat) {
        this.lat = lat;
        return this;
    }

}