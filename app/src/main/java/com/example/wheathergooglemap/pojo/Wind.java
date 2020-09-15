package com.example.wheathergooglemap.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wind implements Serializable
{

    @SerializedName("speed")
    @Expose
    public double speed;
    @SerializedName("deg")
    @Expose
    public int deg;
    private final static long serialVersionUID = -4432120874382586339L;

    public Wind withSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public Wind withDeg(int deg) {
        this.deg = deg;
        return this;
    }

}