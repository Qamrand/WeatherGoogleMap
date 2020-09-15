package com.example.wheathergooglemap.util;

import android.content.Context;

import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;


public class JsonParcer {

    public static List<City> loadJSONFromAsset(Context context) {
        List<City> cityList = new LinkedList<>();
        try {
            InputStream is = context.getResources().openRawResource(R.raw.city_list);
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));

            reader.beginArray();

            Gson gson = new GsonBuilder().create();

            while (reader.hasNext()) {
                City cityJson = gson.fromJson(reader, City.class);
                /*City city = new City();
                city.setId((int) cityJson.getId());
                city.setName(cityJson.getName());
                city.setState(cityJson.getState());
                city.setCountry(cityJson.getCountry());
                city.setCoord(new Coord(cityJson.getCoord().getLon(),
                        cityJson.getCoord().getLon()));*/
                cityList.add(cityJson);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityList;
    }

}
