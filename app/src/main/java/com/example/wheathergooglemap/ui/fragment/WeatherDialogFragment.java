package com.example.wheathergooglemap.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.model.City;
import com.example.wheathergooglemap.pojo.CityWeatherPojo;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDialogFragment extends DialogFragment {

    private CityWeatherPojo mCityWeatherPojo;

    @BindView(R.id.text_view_city)
    TextView textViewCity;

    @BindView(R.id.text_view_description)
    TextView textViewDescription;

    @BindView(R.id.text_view_humidity)
    TextView textViewHumidity;

    @BindView(R.id.text_view_min_max_temp)
    TextView textViewMinManTemp;

    @BindView(R.id.text_view_temperature)
    TextView textViewTemp;

    @BindView(R.id.text_view_wind_speed)
    TextView textViewWindSpeed;

    public WeatherDialogFragment() {
    }

    public WeatherDialogFragment(CityWeatherPojo city) {
        mCityWeatherPojo = city;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_frament_weather, null);
        ButterKnife.bind(this, view);
        builder.setView(view)
                .setNegativeButton(R.string.close,
                        (dialogInterface, i) -> Objects.requireNonNull(WeatherDialogFragment.this.getDialog()).cancel());

        System.out.println(mCityWeatherPojo.toString());
        textViewCity.setText(mCityWeatherPojo.name);
        textViewDescription.setText(mCityWeatherPojo.weather.get(0).description);
        textViewHumidity.setText(String.valueOf(mCityWeatherPojo.main.humidity));
        textViewTemp.setText(String.valueOf(mCityWeatherPojo.main.temp));
        textViewWindSpeed.setText(String.valueOf(mCityWeatherPojo.wind.deg));
        StringBuilder minMaxTemp = new StringBuilder();
        minMaxTemp.append("Max: ").append(mCityWeatherPojo.main.tempMax);
        minMaxTemp.append("   Max: ").append(mCityWeatherPojo.main.tempMin);
        textViewMinManTemp.setText(minMaxTemp.toString());

        return builder.create();
    }
}
