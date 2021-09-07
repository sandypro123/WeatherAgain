package com.example.weatheragain1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class SearchCityActivity extends AppCompatActivity {
    String[]hotCity={"北京","上海","南京","天津","西安","重庆","成都","哈尔滨","大庆","六安"};
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        GridView gridView=findViewById(R.id.search_hotcity);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.search_city_model,hotCity);
        gridView.setAdapter(adapter);
        Button button=findViewById(R.id.search_button);
        EditText text=findViewById(R.id.search_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=text.getText().toString();
                sendMsg();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city=hotCity[position];
                sendMsg();
            }
        });
    }

    private void sendMsg() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("city",city);
        startActivity(intent);
    }
}