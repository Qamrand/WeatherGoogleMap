package com.example.wheathergooglemap.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.wheathergooglemap.MyApp;
import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.api.WeatherApi;
import com.example.wheathergooglemap.api.WeatherApiClient;
import com.example.wheathergooglemap.model.City;
import com.example.wheathergooglemap.pojo.CityWeatherPojo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MapsFragment extends Fragment {

    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    private Marker mMarker;
    private int numOfLoc;
    private City mCity;
    private CityWeatherPojo mCityWeatherPojo;

    public MapsFragment() {

    }

    public MapsFragment(int loc) {
        numOfLoc = loc;
    }

    public MapsFragment(int loc, City city) {
        numOfLoc = loc;
        mCity = city;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(inflater.getContext());

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        WeatherApi weatherApi = WeatherApiClient.getClient().create(WeatherApi.class);
        compositeDisposable.add(weatherApi.getWeatherInCity(mCity.getId(), WeatherApi.WEATHER_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeatherPojo -> {
                            System.out.println("------------------" + cityWeatherPojo.toString());
                            mCityWeatherPojo = cityWeatherPojo;
                        },
                        Throwable::printStackTrace));

        //check permission
        //when permission granted
        //call method
        if (ActivityCompat.checkSelfPermission(inflater.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (numOfLoc == 0) {
                getCurrentLocation(inflater.getContext());
            } else if (numOfLoc == 1) {
                getCitiesLocation();
            } else {
                getCityData();
            }
        } else {
            //when permission denied
            //request permission
            ActivityCompat.requestPermissions((Activity) inflater.getContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        return view;
    }

    private void getCityData() {

        mapFragment.getMapAsync(googleMap -> {
            LatLng sydney = new LatLng(mCity.getCoord().getLat(),
                    mCity.getCoord().getLon());
            googleMap.addMarker(new MarkerOptions().position(sydney).title(mCity.getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
            googleMap.setOnMarkerClickListener(marker -> {
                if (marker.getTitle().equals(mCity.getName())) {
                    WeatherDialogFragment fragment = new WeatherDialogFragment(mCityWeatherPojo);
                    fragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                            "Info");
                }
                return false;
            });
        });
    }

    private void getCitiesLocation() {
        mapFragment.getMapAsync(googleMap -> {
            List<City> list = MyApp.getCityList();
            Observable.just(list)
                    .subscribeOn(Schedulers.newThread())
                    .map(list1 -> {
                        MarkerOptions mo = null;
                        for (City c : list1) {
                            LatLng position = new LatLng(c.getCoord().getLat(),
                                    c.getCoord().getLon());
                            mo = new MarkerOptions().position(position).title(c.getName());
                        }
                        return mo;
                    })
                    .subscribe(markerOptions -> googleMap.addMarker(markerOptions),
                            throwable -> throwable.printStackTrace());
        });
    }

    private void getCurrentLocation(Context context) {

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //init task location
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            //when success
            if (location != null) {
                //sync map
                mapFragment.getMapAsync(googleMap -> {
                    LatLng city = new LatLng(location.getLatitude(), location.getLongitude());

                    mMarker = googleMap.addMarker(
                            new MarkerOptions().position(city).title("Voronizh"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 10));
                    googleMap.setOnMarkerClickListener(marker -> {
                        if (marker.getTitle().equals("Voronizh")) {
                            WeatherDialogFragment fragment = new WeatherDialogFragment();
                            fragment.show(getActivity().getSupportFragmentManager(), "Info");
                        }
                        return false;
                    });
                });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                //call method
                getCurrentLocation(getActivity().getParent());
            }
        }
    }

}
