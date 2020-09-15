package com.example.wheathergooglemap.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sys implements Serializable
{

    @SerializedName("type")
    @Expose
    public int type;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("sunrise")
    @Expose
    public int sunrise;
    @SerializedName("sunset")
    @Expose
    public int sunset;
    private final static long serialVersionUID = 5322559409160120782L;

    public Sys withType(int type) {
        this.type = type;
        return this;
    }

    public Sys withId(int id) {
        this.id = id;
        return this;
    }

    public Sys withMessage(double message) {
        this.message = message;
        return this;
    }

    public Sys withCountry(String country) {
        this.country = country;
        return this;
    }

    public Sys withSunrise(int sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public Sys withSunset(int sunset) {
        this.sunset = sunset;
        return this;
    }

}