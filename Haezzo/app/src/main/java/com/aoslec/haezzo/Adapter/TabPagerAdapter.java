package com.aoslec.haezzo.Adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aoslec.haezzo.FragmentTab1;
import com.aoslec.haezzo.FragmentTab2;

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int behavior){
        super(fm, behavior);
        this.tabCount = behavior;
        Log.v("Message", "TabPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        Log.v("Message", "getItem");
        switch (position){
            case 0:
                FragmentTab1 tab1Fragment = new FragmentTab1();
                return tab1Fragment;
            case 1:
                FragmentTab2 tab2Fragment = new FragmentTab2();
                return tab2Fragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        Log.v("Message", "getCount");
        return tabCount;
    }

}
