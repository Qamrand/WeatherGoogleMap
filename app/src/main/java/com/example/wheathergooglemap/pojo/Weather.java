package com.example.wheathergooglemap.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable
{

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;
    private final static long serialVersionUID = 7744324842505139341L;

    public Weather withId(int id) {
        this.id = id;
        return this;
    }

    public Weather withMain(String main) {
        this.main = main;
        return this;
    }

    public Weather withDescription(String description) {
        this.description = description;
        return this;
    }

    public Weather withIcon(String icon) {
        this.icon = icon;
        return this;
    }

}