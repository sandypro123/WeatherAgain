package com.example.weatheragain1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.weatheragain1.Gson.WeatherGson;
import com.example.weatheragain1.ViewPager.ViewPagerAdapter;
import com.example.weatheragain1.ViewPager.ViewPagerFragment;
import com.example.weatheragain1.sqlite.DatabaseManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ViewPager viewPager;
    List<Fragment> fragmentList;
    ViewPagerFragment fragment;
    List<String> cityList;
    LinearLayout layout;
    Toolbar toolbar;
    List<ImageView> imageViewList;
    ImageView imageView;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.main_viewpager);
        collapsingToolbarLayout=findViewById(R.id.main_CollapsingToolbarLayout);
        viewPager.setOffscreenPageLimit(2);
        fragmentList = new ArrayList<>();
        cityList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        toolbar=findViewById(R.id.toolBar);
        imageView=findViewById(R.id.test);
        Glide.with(MainActivity.this).load("https://i.loli.net/2021/09/01/S1KamefJL2ODtcT.png").into(imageView);
        layout = findViewById(R.id.main_LinearLayout);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        cityList = DatabaseManager.queryCityName();
        if (cityList.size() == 0) {
            cityList.add("上海");
            cityList.add("北京");
        }
        if (!cityList.contains(city) && !TextUtils.isEmpty(city)) {
            cityList.add(city);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            initBar();
//        }
        initFragment();
        initPoint();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        pageListener();
        String firstCity=cityList.get(0);
        toolbar.setTitle(firstCity);
        setSupportActionBar(toolbar);
    }

    /*将状态栏颜色与App颜色保持一致*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        View view = getWindow().getDecorView();
        int vis = view.getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(vis);
    }

    private void pageListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < fragmentList.size(); i++) {
                    imageViewList.get(i).setImageResource(R.mipmap.point);
                }
                imageViewList.get(position).setImageResource(R.mipmap.p);
                String te=cityList.get(position);
                Log.d("position",te);
                Toast.makeText(MainActivity.this,te,Toast.LENGTH_SHORT).show();
                collapsingToolbarLayout.setTitle(te);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.point);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(40, 40));
            layout.addView(imageView);
            imageViewList.add(imageView);
        }
    }

    private void initFragment() {
        for (int i = 0; i < cityList.size(); i++) {
            fragment = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", cityList.get(i).toString());
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(MainActivity.this, CityManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_more:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}