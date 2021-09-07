package com.example.weatheragain1.Application;

import android.app.Application;

import com.example.weatheragain1.sqlite.DatabaseManager;
import com.example.weatheragain1.sqlite.MyDatabaseHelper;

import org.xutils.x;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        DatabaseManager.init(this);
    }
}
