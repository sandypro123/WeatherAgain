package com.example.weatheragain1.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weatheragain1.CityManager.CityManagerType;
import com.example.weatheragain1.DeleteCity.DeleteCityType;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static SQLiteDatabase database;

    public static void init(Context context) {
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(context, "sql", null, 1);//数据库名称
        database = databaseHelper.getWritableDatabase();
    }

    public static void addCity(String city, String content) {
        ContentValues values = new ContentValues();
        values.put("city", city);
        values.put("content", content);
        database.insert("CityContent", null, values);
    }

    public static List queryCityInfo() {
        Cursor cursor = database.query("CityContent", null, null, null, null, null, null);
        List<CityManagerType> typeList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String city = cursor.getString(cursor.getColumnIndex("city"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                CityManagerType type = new CityManagerType(city, content);
                typeList.add(type);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return typeList;
    }

    public static List queryCityList() {
        Cursor cursor = database.query("CityContent", null, null, null, null, null, null);
        List<DeleteCityType> cityTypes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String city = cursor.getString(cursor.getColumnIndex("city"));
                DeleteCityType type = new DeleteCityType(city);
                cityTypes.add(type);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cityTypes;
    }
    public static List queryCityName() {
        Cursor cursor = database.query("CityContent", null, null, null, null, null, null);
        List<String> cityList=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String city = cursor.getString(cursor.getColumnIndex("city"));
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cityList;
    }

    public static int deleteCity(String city) {
        return database.delete("CityContent", "city=?", new String[]{city});
    }
}