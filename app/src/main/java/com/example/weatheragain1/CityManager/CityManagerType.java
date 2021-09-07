package com.example.weatheragain1.CityManager;

public class CityManagerType {
    String city;
    String Content;

    public CityManagerType(String city, String content) {

        this.city = city;
        this.Content = content;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
