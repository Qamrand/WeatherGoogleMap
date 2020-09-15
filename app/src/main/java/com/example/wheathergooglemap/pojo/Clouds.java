package com.example.wheathergooglemap.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clouds implements Serializable
{

    @SerializedName("all")
    @Expose
    public int all;
    private final static long serialVersionUID = 8359519730546645249L;

    public Clouds withAll(int all) {
        this.all = all;
        return this;
    }

}