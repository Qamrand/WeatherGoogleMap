package com.example.wheathergooglemap;

import android.app.Application;

import com.example.wheathergooglemap.model.City;
import com.example.wheathergooglemap.util.JsonParcer;

import java.util.List;

/**
 * use for download in memory large .json file
 */
public class MyApp extends Application {
    private static List<City> sCityList;

    @Override
    public void onCreate() {
        super.onCreate();

        sCityList = JsonParcer.loadJSONFromAsset(this);
    }

    public static List<City> getCityList(){
        return sCityList;
    }
}
