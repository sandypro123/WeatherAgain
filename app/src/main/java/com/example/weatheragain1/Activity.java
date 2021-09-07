package com.example.weatheragain1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity","onRestart");
    }
}
