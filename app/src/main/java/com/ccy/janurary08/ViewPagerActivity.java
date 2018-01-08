package com.ccy.janurary08;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chanchaoyue on 2018/1/8.
 */

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<String> mTitles = Arrays.asList("Fragment-->微信", "Fragment-->通讯录", "Fragment-->发现", "Fragment-->我");
    private List<SimpleFragment> mFergment = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
    private BottomTabView bottomTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main_viewpager);
        initViews();
        initDatas();
        initEvent();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        bottomTabView = findViewById(R.id.id_bottom_tab);
    }

    private void initDatas() {
        for (String title : mTitles) {
            mFergment.add(SimpleFragment.newInstance(title));
        }
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this, mFergment);
        mViewPager.setAdapter(mPagerAdapter);
        bottomTabView.setViewPager(mViewPager);
    }

    private void initEvent() {

    }
}
