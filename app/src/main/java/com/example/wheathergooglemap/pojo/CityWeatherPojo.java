package com.example.wheathergooglemap.pojo;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CityWeatherPojo implements Serializable {

    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public int visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("timezone")
    @Expose
    public int timezone;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public int cod;
    private final static long serialVersionUID = 5540221109013537886L;

    @Override
    public String toString() {
        return "CityWeatherPojo{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }



    public CityWeatherPojo withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    public CityWeatherPojo withWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public CityWeatherPojo withBase(String base) {
        this.base = base;
        return this;
    }

    public CityWeatherPojo withMain(Main main) {
        this.main = main;
        return this;
    }

    public CityWeatherPojo withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public CityWeatherPojo withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public CityWeatherPojo withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    public CityWeatherPojo withDt(int dt) {
        this.dt = dt;
        return this;
    }

    public CityWeatherPojo withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    public CityWeatherPojo withTimezone(int timezone) {
        this.timezone = timezone;
        return this;
    }

    public CityWeatherPojo withId(int id) {
        this.id = id;
        return this;
    }

    public CityWeatherPojo withName(String name) {
        this.name = name;
        return this;
    }

    public CityWeatherPojo withCod(int cod) {
        this.cod = cod;
        return this;
    }

}







