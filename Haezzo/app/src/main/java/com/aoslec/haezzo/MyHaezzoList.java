package com.aoslec.haezzo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aoslec.haezzo.Adapter.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyHaezzoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "onCreate_MyHaezzoList");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_haezzo_list);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab1 Item ,,,,").setIcon(android.R.drawable.ic_dialog_email));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab2 Item ,,,,").setIcon(android.R.drawable.ic_dialog_dialer));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab3 Item ,,,,").setIcon(android.R.drawable.ic_dialog_map));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab4 Item ,,,,").setIcon(android.R.drawable.ic_dialog_info));
        tabLayout.addTab(tabLayout.newTab().setText("진행중"));
        tabLayout.addTab(tabLayout.newTab().setText("거래완료"));


        ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.v("Message", "onTabSelected");
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.v("Message", "onTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.v("Message", "onTabReselected");
            }
        });

    }
}