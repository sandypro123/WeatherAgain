package com.example.weatheragain1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.weatheragain1.CityManager.CityManagerAdapter;
import com.example.weatheragain1.CityManager.CityManagerType;
import com.example.weatheragain1.sqlite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity {
ListView listView;
List<CityManagerType> list=new ArrayList<>();
ImageView back,delete,more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        list= DatabaseManager.queryCityInfo();
        listView=findViewById(R.id.CityManager_listview);
        CityManagerAdapter adapter=new CityManagerAdapter(CityManagerActivity.this,R.layout.city_manager_model,list);
        listView.setAdapter(adapter);
        back=findViewById(R.id.CityManager_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityManagerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete=findViewById(R.id.CityManager_edit);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityManagerActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
        more=findViewById(R.id.CityManager_more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityManagerActivity.this, SearchCityActivity.class);
                startActivity(intent);
            }
        });
    }
}