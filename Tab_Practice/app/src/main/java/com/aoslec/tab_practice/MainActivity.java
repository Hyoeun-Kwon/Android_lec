package com.aoslec.tab_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.maintab_layout);
        //tab layout에 글씨 쓰기
        tabLayout.addTab(tabLayout.newTab().setText("Email").setIcon(android.R.drawable.ic_dialog_email));
        tabLayout.addTab(tabLayout.newTab().setText("Info").setIcon(android.R.drawable.ic_dialog_info));
        tabLayout.addTab(tabLayout.newTab().setText("Dialer").setIcon(android.R.drawable.ic_dialog_dialer));

        //viewPage
        ViewPager viewPager = findViewById(R.id.main_pager);

        //pager Adapter 사용
        PagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //adapter를 뷰페이저에
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //기능 추가
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition()); //몇번째
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }//
}//