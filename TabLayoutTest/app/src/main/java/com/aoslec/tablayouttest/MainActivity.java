package com.aoslec.tablayouttest;

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

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        //tab의 모양과 글씨를 쓰거임
        tabLayout.addTab(tabLayout.newTab().setText("tab1 Item<<<<").setIcon(android.R.drawable.ic_dialog_email));
        tabLayout.addTab(tabLayout.newTab().setText("tab2 Item<<<<").setIcon(android.R.drawable.ic_dialog_dialer));
        tabLayout.addTab(tabLayout.newTab().setText("tab3 Item<<<<").setIcon(android.R.drawable.ic_dialog_map));
        tabLayout.addTab(tabLayout.newTab().setText("tab4 Item<<<<").setIcon(android.R.drawable.ic_dialog_info));

        //view페이저
        ViewPager viewPager = findViewById(R.id.pager);

        //pager adapter 사용
        PagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //adapter를 뷰페이저에게 줘야지
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tablayout에 기능을 추가하는 것 (adapter는 기능, inflater는 뷰다)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition()); //몇번째다.

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}