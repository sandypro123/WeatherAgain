package com.example.weatheragain1.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatheragain1.Gson.WeatherGson;
import com.example.weatheragain1.Gson.WeekGson;
import com.example.weatheragain1.R;
import com.example.weatheragain1.RecycleType;
import com.example.weatheragain1.RecycleViewAdapter;
import com.example.weatheragain1.sqlite.DatabaseManager;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends Fragment{
    LinearLayout layout;
    TextView textView1,textView2,textView3;
    TextView wash,cloth,sun,sport,cold;
    TextView temp,condition;
    RecyclerView recyclerView;
    private List<RecycleType> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_pager, container, false);
        layout= view.findViewById(R.id.main_week);
        Bundle bundle=this.getArguments();
        String city=bundle.getString("city");
        String url1="http://hn216.api.yesapi.cn/?s=App.Common_Weather.LiveWeather&return_data=0&app_key=32F8B086952047925E77F7F09F120FDC&city="+city;
        String url2="http://hn216.api.yesapi.cn/?s=App.Common_Weather.WeekWeather&return_data=0&app_key=32F8B086952047925E77F7F09F120FDC&city="+city;
        RequestParams params1 = new RequestParams(url1);
        RequestParams params2 = new RequestParams(url2);
        recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        init();
        RecycleViewAdapter adapter=new RecycleViewAdapter(list);
        recyclerView.setAdapter(adapter);
        x.http().get(params2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                WeekGson weekGson=new Gson().fromJson(result,WeekGson.class);
                List<WeekGson.DataDTO.WeatherDTO> weatherDTO= weekGson.getData().getWeather();
                for (int i = 0; i < 4; i++) {
                    View view=inflater.inflate(R.layout.main_condition,null);
                    layout.addView(view);
                    textView1=view.findViewById(R.id.condition_day);
                    textView2=view.findViewById(R.id.condition_weather);
                    textView3=view.findViewById(R.id.condition_temp);
                    String weekDate=weatherDTO.get(i).getWeek().substring(weatherDTO.get(i).getWeek().length()-1,weatherDTO.get(i).getWeek().length());
                    textView1.setText("周"+weekDate);
                    textView2.setText(weatherDTO.get(i).getWea());
                    textView3.setText(weatherDTO.get(i).getTem2()+"~"+weatherDTO.get(1).getTem1());
                }
                wash=view.findViewById(R.id.frag_wash);
                cloth=view.findViewById(R.id.frag_cloth);
                sun=view.findViewById(R.id.frag_sun);
                sport=view.findViewById(R.id.frag_sport);
                cold=view.findViewById(R.id.frag_cold);
                wash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setMessage(weatherDTO.get(0).getIndex().get(4).getDesc());
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    }
                });
                cloth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setMessage(weatherDTO.get(0).getIndex().get(3).getDesc());
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    }
                });
                sun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setMessage(weatherDTO.get(0).getIndex().get(0).getDesc());
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    }
                });
                sport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setMessage(weatherDTO.get(0).getIndex().get(1).getDesc());
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    }
                });
                cold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setMessage(weatherDTO.get(0).getIndex().get(2).getDesc());
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        x.http().get(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DatabaseManager.addCity(city,result);
                WeatherGson weatherGson=new Gson().fromJson(result,WeatherGson.class);
                WeatherGson.DataDTO.WeatherDTO weather = weatherGson.getData().getWeather();
                temp=view.findViewById(R.id.main_temp);
                condition=view.findViewById(R.id.main_condition);
                temp.setText(weather.getTem()+"℃");
                condition.setText(weather.getWeather());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return view;
    }

    private void init() {
     for (int i=0;i<20;i++){
         RecycleType apple=new RecycleType("123",R.mipmap.test);
         list.add(apple);
     }
    }
}