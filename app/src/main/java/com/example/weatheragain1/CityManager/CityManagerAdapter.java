package com.example.weatheragain1.CityManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatheragain1.CityManager.CityManagerType;
import com.example.weatheragain1.Gson.WeatherGson;
import com.example.weatheragain1.R;
import com.google.gson.Gson;

import java.util.List;

public class CityManagerAdapter extends ArrayAdapter<CityManagerType> {
    int id;
    public CityManagerAdapter(@NonNull Context context, int resource, @NonNull List<CityManagerType> objects) {
        super(context, resource, objects);
        id=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CityManagerType type=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(id,parent,false);
        TextView textView1=view.findViewById(R.id.model_city);
        TextView textView2=view.findViewById(R.id.model_temp);
        TextView textView3=view.findViewById(R.id.model_weather);
        TextView textView4=view.findViewById(R.id.model_wind);
        WeatherGson weatherGson=new Gson().fromJson(type.getContent(),WeatherGson.class);
        WeatherGson.DataDTO.WeatherDTO weatherDTO=weatherGson.getData().getWeather();
        textView1.setText(weatherDTO.getCity());
        textView2.setText(weatherDTO.getTem());
        textView3.setText(weatherDTO.getWeather());
        textView4.setText(weatherDTO.getAir_level());
        return view;
    }
}
