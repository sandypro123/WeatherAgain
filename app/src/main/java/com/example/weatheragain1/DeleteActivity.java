package com.example.weatheragain1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.weatheragain1.DeleteCity.DeleteCityAdapter;
import com.example.weatheragain1.DeleteCity.DeleteCityType;
import com.example.weatheragain1.sqlite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
ListView listView;
List<DeleteCityType> types;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        listView=findViewById(R.id.delete_listview);
        types= DatabaseManager.queryCityList();
        DeleteCityAdapter adapter=new DeleteCityAdapter(DeleteActivity.this,R.layout.city_delete_model,types);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

}